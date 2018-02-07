using Dapper;
using Nodal.Common.Helper;
using Nodal.Common.Schema;
using System.Collections.Generic;
using System.Linq;

namespace Nodal.DataAccess
{
    public class SchemesRepository
    {
        private DBHelper db;
        public SchemesRepository()
        {
            db = DBHelper.GetInstance();
        }

        public int InsertScheme(Schemes scheme)
        {
            string sql = @"INSERT INTO [Schemes] ([SchemeName],[SchemeCode],[IsActive], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate]) VALUES (@SchemeName, @SchemeCode, @IsActive, @CreatedById, @CreatedDate, @LastUpdatedById, @LastUpdatedDate) ; SELECT CAST(SCOPE_IDENTITY() as int)";

            var id = db.Connection.Query<int>(sql, scheme).Single();
            return id;
        }

        public int UpdateScheme(Schemes scheme)
        {
            string sql = @"UPDATE [Schemes] SET [SchemeName] = @SchemeName, 
[SchemeCode] = @SchemeCode, [IsActive] = @IsActive, [LastUpdatedById] = @LastUpdatedById, [LastUpdatedDate] = @LastUpdatedDate WHERE [SchemeId] = @SchemeId";

            var id = db.Connection.Query<int>(sql, scheme).Single();
            return id;
        }

        public int DeleteScheme(int schemeId)
        {
            var result = db.Connection.Execute("DELETE FROM [dbo].[Schemes] WHERE [SchemeId] = @SchemeId", new { schemeId });
            return result;
        }

        public Schemes GetScheme(int schemeId)
        {
            string query = "SELECT * FROM [dbo].[Schemes] WHERE [SchemeId] = @SchemeId";
            var result = db.Connection.Query(query, new { schemeId }).FirstOrDefault();
            return result;
        }

        public IEnumerable<Schemes> GetSchemes()
        {
            var result = db.Connection.Query<Schemes>("SELECT * FROM [dbo].[Schemes]");
            return result;
        }
    }
}
