using Dapper;
using Nodal.Common.Helper;
using Nodal.Common.Schema;
using System.Collections.Generic;
using System.Linq;

namespace Nodal.DataAccess
{
    public class TokenRepository
    {
        private DBHelper db;

        public TokenRepository()
        {
            db = DBHelper.GetInstance();
        }

        public void InsertToken(Token accessToken)
        {
            string query = string.Format("INSERT INTO {0} VALUES(@UserId,@AuthToken,@IssuedOn)", SQLTables.Tokens);
            db.Connection.Execute(query, new { accessToken.UserId, accessToken.AuthToken, accessToken.IssuedOn });
        }

        public Token GetTokenByID(string tokenID)
        {
            var query = string.Format("SELECT [UserId],[AuthToken],[IssuedOn] FROM {0} WHERE [AuthToken] = @ID", SQLTables.Tokens);
            return db.Connection.Query<Token>(query, new { ID = tokenID }).FirstOrDefault();
        }

        public bool DeleteTokenByUser(int userID)
        {
            string query = string.Format("DELETE FROM {0} WHERE UserId= @userID", SQLTables.Tokens);
            var result = db.Connection.Execute(query, new { userID });

            return result > 0;
        }
    }
}
