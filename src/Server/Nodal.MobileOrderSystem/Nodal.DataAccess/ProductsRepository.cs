using Dapper;
using Nodal.Common.Helper;
using Nodal.Common.Schema;
using System.Collections.Generic;
using System.Linq;

namespace Nodal.DataAccess
{
    public class ProductsRepository
    {
        private DBHelper db;
        public ProductsRepository()
        {
            db = DBHelper.GetInstance();
        }

        public int InsertProduct(Products product)
        {
            string sql = @"INSERT INTO [Products] ([ProductName],[MRP],[DealerPrice],[WholesalePrice],[CGST],[SGST],[IGST],[IsActive], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate])
     VALUES (@ProductName, @MRP, @DealerPrice, @WholesalePrice, @CGST, @SGST, @IGST, @IsActive, @CreatedById, @CreatedDate, @LastUpdatedById, @LastUpdatedDate); SELECT CAST(SCOPE_IDENTITY() as int)";

            var id = db.Connection.Query<int>(sql, product).Single();
            return id;
        }

        public int UpdateProduct(Products product)
        {
            string sql = @"UPDATE [dbo].[Products]
   SET [ProductName] = @ProductName,[MRP] = @MRP,[DealerPrice] = @DealerPrice,
   [WholesalePrice] = @WholesalePrice,[CGST] = @CGST,[SGST] = @SGST,[IGST] = @IGST,[IsActive] = @IsActive, [LastUpdatedById] = @LastUpdatedById, [LastUpdatedDate] = @LastUpdatedDate  
   WHERE [ProductId] = @ProductId";

            var id = db.Connection.Query<int>(sql, product).Single();
            return id;
        }

        public int DeleteProduct(int productId)
        {
            var result = db.Connection.Execute("DELETE FROM [Products] WHERE ProductId = @ProductId", new { productId });
            return result;
        }

        public Products GetProduct(int productId)
        {
            string query = "SELECT * FROM [Products] WHERE ProductId = @ProductId";
            var result = db.Connection.Query(query, new { productId }).FirstOrDefault();
            return result;
        }

        public IEnumerable<Products> GetProducts()
        {
            var result = db.Connection.Query<Products>("SELECT * FROM [Products]");
            return result;
        }
    }
}
