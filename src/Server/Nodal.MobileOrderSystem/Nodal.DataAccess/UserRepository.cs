using Dapper;
using Nodal.Common.Helper;
using Nodal.Common.Schema;
using System;
using System.Collections.Generic;
using System.Linq;

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

        public int InsertUser(User user)
        {
            string sql = @"INSERT INTO [Users] ([Username],[Password],[FirstName],[MiddleName],[LastName],[Mobile]
		   ,[Email],[Address1],[Address2],[City],[State],[Country],[Pin],[UserGroupType]
		   ,[UserElementCode],[ActiveFrom],[ActiveTo],[IsActive], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate])
     VALUES(@Username, @Password, @FirstName, @MiddleName, @LastName, @Mobile, 
            @Email, @Address1, @Address2, @City, @State, @Country, 
            @Pin, @UserGroupType, @UserElementCode, @ActiveFrom, @ActiveTo, @IsActive, @CreatedById, @CreatedDate, @LastUpdatedById, @LastUpdatedDate); SELECT CAST(SCOPE_IDENTITY() as int)";

            var id = db.Connection.Query<int>(sql, user).FirstOrDefault();
            return id;
        }

        public int UpdateUser(User user)
        {
            string sql = @"UPDATE [Users] SET [Username] = @Username, [FirstName] = @FirstName, 
       [MiddleName] = @MiddleName, [LastName] = @LastName, [Mobile] = @Mobile, [Email] = @Email, 
       [Address1] = @Address1, [Address2] = @Address2, [City] = @City, [State] = @State, 
       [Country] = @Country, [Pin] = @Pin, [UserGroupType] = @UserGroupType, [UserElementCode] = @UserElementCode, 
       [ActiveFrom] = @ActiveFrom, [ActiveTo] = @ActiveTo, [IsActive] = @IsActive, [LastUpdatedById] = @LastUpdatedById, [LastUpdatedDate] = @LastUpdatedDate WHERE [UserId] = @UserId";

            var id = db.Connection.Query<int>(sql, user).FirstOrDefault();
            return id;
        }

        public int DeleteUser(int userId)
        {
            var result = db.Connection.Execute("DELETE FROM [Users] WHERE [UserId] = @UserId", new { userId });
            return result;
        }

        public User GetUser(int userId)
        {
            string query = "SELECT * FROM [Users] WHERE [UserId] = @UserId";
            var result = db.Connection.Query<User>(query, new { userId }).FirstOrDefault();
            return result;
        }

        public IEnumerable<User> GetUsers()
        {
            var result = db.Connection.Query<User>("SELECT * FROM [Users]");
            return result;
        }

        public User GetUserByEmail(string email)
        {
            var result = db.Connection.Query<User>("SELECT * FROM [Users] WHERE [Email] = @Email", new { email }).FirstOrDefault();
            return result;
        }

        public int ChangePassword(int userId, string password)
        {
            string sql = @"UPDATE [Users] SET [Password] = @Password, [LastUpdatedById] = @LastUpdatedById, [LastUpdatedDate] = @LastUpdatedDate WHERE [UserId] = @UserId";

            var id = db.Connection.Query<int>(sql, new { Password = password, LastUpdatedById = userId, LastUpdatedDate = DateTime.Now, UserId = userId }).FirstOrDefault();
            return id;
        }
    }
}
