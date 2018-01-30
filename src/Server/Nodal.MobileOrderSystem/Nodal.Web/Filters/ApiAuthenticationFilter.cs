using Nodal.BusinessAccess.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Web;
using System.Web.Http.Controllers;

namespace Nodal.Web.Filters
{
    public class ApiAuthenticationFilter : GenericAuthenticationFilter
    {
        public ApiAuthenticationFilter()
        {
        }

        public ApiAuthenticationFilter(bool isActive)
            : base(isActive)
        {
        }

        protected override bool OnAuthorizeUser(string username, string password, HttpActionContext actionContext)
        {
            var provider = new UserService();
            if (provider != null)
            {
                var user = provider.Authenticate(username, password);
                if (user != null)
                {
                    var basicAuthenticationIdentity = Thread.CurrentPrincipal.Identity as BasicAuthenticationIdentity;
                    if (basicAuthenticationIdentity != null)
                        basicAuthenticationIdentity.UserId = user.UserId;
                    return true;
                }
            }
            return false;
        }
    }
}