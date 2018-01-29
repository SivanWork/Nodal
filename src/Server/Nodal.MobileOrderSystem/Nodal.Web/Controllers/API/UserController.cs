using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using Nodal.Web.Filters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Nodal.Web.Controllers.API
{
    public class UserController : BaseApiController
    {
        private readonly UserService _userService = new UserService();

        [HttpGet]
        [AllowAnonymous]
        [ApiAuthenticationFilter]
        public UserProfileResponse Login()
        {
            if (System.Threading.Thread.CurrentPrincipal != null && System.Threading.Thread.CurrentPrincipal.Identity.IsAuthenticated)
            {
                if (System.Threading.Thread.CurrentPrincipal.Identity is BasicAuthenticationIdentity basicAuthenticationIdentity)
                    return _userService.GetUserProfile(basicAuthenticationIdentity.UserId);
            }
            return new UserProfileResponse();
        }
    }
}
