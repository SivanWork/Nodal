using Nodal.BusinessAccess.Model;
using Nodal.Common.Schema;
using Nodal.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Nodal.BusinessAccess.Service
{
    public class UserService
    {
        private UserRepository repository;

        public UserService()
        {
            repository = new UserRepository();
        }

        public User Authenticate(string userName, string password)
        {
            return repository.Authenticate(userName, password);
        }

        public UserProfileResponse GetUserProfile(int UserId)
        {
            UserProfileResponse usersResponse = new UserProfileResponse();
            try
            {
                usersResponse.User = repository.GetUserProfile(UserId);
                if (usersResponse.User != null)
                {
                    if (!usersResponse.User.IsActive)
                    {
                        usersResponse.IsWarning = true;
                        usersResponse.Message = "User is not active..!";
                    }
                    else if (usersResponse.User.ActiveTo < DateTime.Now)
                    {
                        usersResponse.IsWarning = true;
                        usersResponse.Message = "Your Account has been Expired..!";
                    }
                    else
                    {
                        var token = new TokenService().GenerateToken(usersResponse.User.UserId);
                        if (token != null)
                            usersResponse.AuthToken = token.AuthToken;
                    }
                }
                usersResponse.Success = true;
            }
            catch (Exception ex)
            {
                usersResponse.Success = false;
                usersResponse.Message = ex.Message;
                usersResponse.StackTrace = ex.StackTrace;
            }

            return usersResponse;
        }
    }
}
