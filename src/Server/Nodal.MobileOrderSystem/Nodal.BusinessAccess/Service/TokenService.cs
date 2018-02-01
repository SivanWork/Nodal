using Nodal.Common.Schema;
using Nodal.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Nodal.BusinessAccess.Service
{
    public class TokenService
    {
        private TokenRepository repository;

        public TokenService()
        {
            repository = new TokenRepository();
        }

        public Token GenerateToken(int userID)
        {
            var token = Guid.NewGuid();
            DateTime issuedOn = DateTime.UtcNow;
            var tokendomain = new Token
            {
                UserId = userID,
                AuthToken = token,
                IssuedOn = issuedOn
            };

            repository.InsertToken(tokendomain);

            return tokendomain;
        }

        public int ValidateToken(string tokenId)
        {
            var token = repository.GetTokenByID(tokenId);
            if (token != null)
                return token.UserId;
            else
                return 0;
        }

        public bool DeleteTokenByUser(int userID)
        {
            return repository.DeleteTokenByUser(userID);
        }
    }
}
