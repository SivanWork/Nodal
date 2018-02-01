using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;
using System.Web.Http.Controllers;
using System.Web.Http.Filters;

namespace Nodal.Web.ActionFilters
{
    public class AuthorizationRequiredAttribute : ActionFilterAttribute
    {
        private const string Token = "Authorization";
        public override void OnActionExecuting(HttpActionContext filterContext)
        {
            if (SkipAuthorization(filterContext))
                return;

            var tokenService = new TokenService();

            if (filterContext.Request.Headers.Contains(Token))
            {
                var tokenValue = filterContext.Request.Headers.GetValues(Token).First();
                var userValue = tokenService.ValidateToken(tokenValue);

                if (userValue > 0)
                {
                    BaseRequest _requestConfig = new BaseRequest() { UserID = userValue };
                    filterContext.Request.Properties.Add("requestConfig", _requestConfig);
                }
                else
                {
                    var responseMessage = new HttpResponseMessage(HttpStatusCode.Unauthorized) { ReasonPhrase = "Invalid Request" };
                    filterContext.Response = responseMessage;
                }
            }
            else
                filterContext.Response = new HttpResponseMessage(HttpStatusCode.Unauthorized);

            base.OnActionExecuting(filterContext);

        }

        private static bool SkipAuthorization(HttpActionContext actionContext)
        {
            if (!Enumerable.Any(actionContext.ActionDescriptor.GetCustomAttributes<AllowAnonymousAttribute>()))
                return Enumerable.Any(actionContext.ControllerContext.ControllerDescriptor.GetCustomAttributes<AllowAnonymousAttribute>());
            else
                return true;
        }
    }
}