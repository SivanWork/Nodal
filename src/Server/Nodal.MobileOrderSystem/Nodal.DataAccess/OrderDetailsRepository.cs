using Dapper;
using Nodal.Common.Helper;
using Nodal.Common.Schema;
using System.Collections.Generic;
using System.Linq;

namespace Nodal.DataAccess
{
    public class OrderDetailsRepository
    {
        private DBHelper db;

        public OrderDetailsRepository()
        {
            db = DBHelper.GetInstance();
        }

        public int InsertOrderDetail(OrderDetails orderDetail)
        {
            string sql = @"INSERT INTO [OrderDetails] ([OrderId],[ProductId],[Quantity],[CGST],[SGST],[IGST],[Discount],[NetPrice], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate])
VALUES (@OrderId, @ProductId, @Quantity, @CGST, @SGST, @IGST, @Discount, @NetPrice, @CreatedById, @CreatedDate, @LastUpdatedById, @LastUpdatedDate) ; SELECT CAST(SCOPE_IDENTITY() as int)";

            var id = db.Connection.Query<int>(sql, orderDetail).FirstOrDefault();
            return id;
        }

        public int UpdateOrderDetail(OrderDetails orderDetail)
        {
            string sql = @"UPDATE [dbo].[OrderDetails]
   SET [OrderId] = @OrderId, [ProductId] = @ProductId, [Quantity] = @Quantity, [CGST] = @CGST,  
       [SGST] = @SGST, [IGST] = @IGST, [Discount] = @Discount, [NetPrice] = @NetPrice, [LastUpdatedById] = @LastUpdatedById, [LastUpdatedDate] = @LastUpdatedDate WHERE [Id] = @Id";

            var id = db.Connection.Query<int>(sql, orderDetail).FirstOrDefault();
            return id;
        }

        public int DeleteOrderDetail(int orderDetailId)
        {
            var result = db.Connection.Execute("DELETE FROM [OrderDetails] WHERE [Id] = @Id", new { orderDetailId });
            return result;
        }

        public OrderDetails GetOrderDetail(int orderDetailId)
        {
            string query = "SELECT * FROM [OrderDetails] WHERE [Id] = @Id";
            var result = db.Connection.Query<OrderDetails>(query, new { orderDetailId }).FirstOrDefault();
            return result;
        }

        public IEnumerable<OrderDetails> GetOrderDetails()
        {
            var result = db.Connection.Query<OrderDetails>("SELECT * FROM [OrderDetails]");
            return result;
        }
    }
}
