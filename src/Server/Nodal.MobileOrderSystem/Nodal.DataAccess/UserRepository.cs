using Dapper;
using Nodal.Common.Helper;
using Nodal.Common.Schema;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Nodal.DataAccess
{
    public class UserRepository
    {
        private DBHelper db;

        public UserRepository()
        {
            db = DBHelper.GetInstance();
        }

        public User Authenticate(string userName, string password)
        {
            var query = string.Format("SELECT [UserId] FROM {0} WHERE (Username = @userName OR Mobile = @userName ) AND Password = @password", SQLTables.Users);
            return db.Connection.Query<User>(query, new { userName, password }).FirstOrDefault();
        }

        public UserProfile GetUserProfile(int UserID)
        {
            var query = string.Format("SELECT [UserId],[FirstName],[MiddleName],[LastName],[Mobile],[Email],[UserTypeCode],[UserTypeName],[UserTypeActive],[Address1],[Address2],[City],[State],[Country],[Pin],[ActiveFrom],[ActiveTo],[IsActive] FROM {0} WHERE UserId = @UserID", SQLTables.UserProfileView);
            return db.Connection.Query<UserProfile>(query, new { UserID }).FirstOrDefault();
        }
    }
}
