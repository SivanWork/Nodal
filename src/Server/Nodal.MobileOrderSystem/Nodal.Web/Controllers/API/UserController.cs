using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using Nodal.Common.Helper;
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

        [HttpPost]
        public BaseResponse CreateUser(UserRequest request)
        {
            return _userService.InsertUser(request);
        }

        [HttpPost]
        public BaseResponse UpdateUser(UserRequest request)
        {
            return _userService.UpdateUser(request);
        }

        [HttpDelete]
        public BaseResponse DeleteUser(int userId)
        {
            return _userService.DeleteUser(userId);
        }

        [HttpGet]
        [AllowAnonymous]
        public UserResponse GetUser(int userId)
        {
            return _userService.GetUser(userId);
        }

        [HttpGet]
        public UserResponse GetAllUsers()
        {
            return _userService.GetAllUsers();
        }

        [HttpPost]
        [AllowAnonymous]
        public BaseResponse ForgotPassword(string recipientEmail)
        {
            var res = _userService.GetUserByEMail(recipientEmail);
            BaseResponse response = new BaseResponse();

            if (res != null && res.user != null)
            {
                EmailHelper.Send(
                    ConfigurationHelper.GetInstance().GetConfig("Sender", typeof(string), "nodalmos@gmail.com"),
                    ConfigurationHelper.GetInstance().GetConfig("SenderKey", typeof(string), "Test@1234"),
                    res.user.Email,
                    ConfigurationHelper.GetInstance().GetConfig("Subject", typeof(string), "We received your forgot password request!"),
                    ConfigurationHelper.GetInstance().GetConfig("MessageContent", typeof(string),
                    string.Format("Here you go! - User Name: {0}, Password: {1}", res.user.Username, res.user.Password)));

                response.Message = "You will receive email shortly with your username and password!";
                response.Success = true;
            }
            else
            {
                response.Message = "Given email doesn't match our records!";
                response.Success = false;
            }

            return response;
        }

        [HttpPost]
        public BaseResponse ChangePassword()
        {
            throw new NotImplementedException();
        }
    }
}
