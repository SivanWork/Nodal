using Dapper;
using Nodal.Common.Helper;
using Nodal.Common.Schema;
using System.Collections.Generic;
using System.Linq;

namespace Nodal.DataAccess
{
    public class SchemeProductRepository
    {
        private DBHelper db;
        public SchemeProductRepository()
        {
            db = DBHelper.GetInstance();
        }

        public int InsertSchemeProduct(SchemeProduct schemeProduct)
        {
            string sql = @"INSERT INTO [SchemeProduct] ([SchemeId],[ProductId],[IsActive], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate])
     VALUES (@SchemeId, @ProductId, @IsActive, @CreatedById, @CreatedDate, @LastUpdatedById, @LastUpdatedDate); SELECT CAST(SCOPE_IDENTITY() as int)";

            var id = db.Connection.Query<int>(sql, schemeProduct).Single();
            return id;
        }

        public int UpdateSchemeProduct(SchemeProduct schemeProduct)
        {
            string sql = @"UPDATE [SchemeProduct] SET [SchemeId] = @SchemeId, [ProductId] = @ProductId, [IsActive] = @IsActive, [LastUpdatedById] = @LastUpdatedById, [LastUpdatedDate] = @LastUpdatedDate WHERE [Id] = @Id";

            var id = db.Connection.Query<int>(sql, schemeProduct).Single();
            return id;
        }

        public int DeleteSchemeProduct(int schemeProductId)
        {
            var result = db.Connection.Execute("DELETE FROM [SchemeProduct] WHERE [Id] = @Id", new { schemeProductId });
            return result;
        }

        public SchemeProduct GetSchemeProduct(int schemeProductId)
        {
            string query = "SELECT * FROM [SchemeProduct] WHERE [Id] = @Id";
            var result = db.Connection.Query(query, new { schemeProductId }).FirstOrDefault();
            return result;
        }

        public IEnumerable<SchemeProduct> GetSchemeProducts()
        {
            var result = db.Connection.Query<SchemeProduct>("SELECT * FROM [SchemeProduct]");
            return result;
        }
    }
}
