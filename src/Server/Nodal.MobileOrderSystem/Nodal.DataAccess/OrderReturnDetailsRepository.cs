using Dapper;
using Nodal.Common.Helper;
using Nodal.Common.Schema;
using System.Collections.Generic;
using System.Linq;

namespace Nodal.DataAccess
{
    public class OrderReturnDetailsRepository
    {
        private DBHelper db;
        public OrderReturnDetailsRepository()
        {
            db = DBHelper.GetInstance();
        }

        public int InsertOrderReturnDetail(OrderReturnDetails orderReturnDetail)
        {
            string sql = @"INSERT INTO [OrderReturnDetails]
           ([OrderId],[ProductId],[Quantity],[CGST],[SGST],[IGST],[Discount],[NetPrice],
		    [OrderReturnGroup],[OrderReturnElementCode],[Comments], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate])
           VALUES 
           (@OrderId, @ProductId, @Quantity, @CGST, @SGST, @IGST, @Discount, @NetPrice, 
		    @OrderReturnGroup, @OrderReturnElementCode, @Comments, @CreatedById, @CreatedDate, @LastUpdatedById, @LastUpdatedDate) ; SELECT CAST(SCOPE_IDENTITY() as int)";

            var id = db.Connection.Query<int>(sql, orderReturnDetail).Single();
            return id;
        }

        public int UpdateOrderReturnDetail(OrderReturnDetails orderReturnDetail)
        {
            string sql = @"UPDATE [OrderReturnDetails] SET [OrderId] = @OrderId, 
       [ProductId] = @ProductId, [Quantity] = @Quantity, [CGST] = @CGST, [SGST] = @SGST, 
       [IGST] = @IGST, [Discount] = @Discount, [NetPrice] = @NetPrice, [OrderReturnGroup] = @OrderReturnGroup, 
       [OrderReturnElementCode] = @OrderReturnElementCode, [Comments] = @Comments, [LastUpdatedById] = @LastUpdatedById, [LastUpdatedDate] = @LastUpdatedDate WHERE [Id] = @Id";

            var id = db.Connection.Query<int>(sql, orderReturnDetail).Single();
            return id;
        }

        public int DeleteOrderReturnDetail(int orderReturnDetailId)
        {
            var result = db.Connection.Execute("DELETE FROM [OrderReturnDetails] WHERE [Id] = @Id", new { orderReturnDetailId });
            return result;
        }

        public OrderReturnDetails GetOrderReturnDetail(int orderReturnDetailId)
        {
            string query = "SELECT * FROM [OrderReturnDetails] WHERE [Id] = @Id";
            var result = db.Connection.Query<OrderReturnDetails>(query, new { orderReturnDetailId }).FirstOrDefault();
            return result;
        }

        public IEnumerable<OrderReturnDetails> GetOrderReturnDetails()
        {
            var result = db.Connection.Query<OrderReturnDetails>("SELECT * FROM [OrderReturnDetails]");
            return result;
        }
    }
}
