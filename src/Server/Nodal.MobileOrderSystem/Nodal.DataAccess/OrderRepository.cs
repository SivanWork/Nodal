using Dapper;
using Nodal.Common.Helper;
using Nodal.Common.Schema;
using System.Collections.Generic;
using System.Linq;

namespace Nodal.DataAccess
{
    public class OrderRepository
    {
        private DBHelper db;

        public OrderRepository()
        {
            db = DBHelper.GetInstance();
        }

        public int InsertOrder(Order order)
        {
            string sql = @"
                            INSERT INTO [Orders] ([CustomerId],[TotalOrderAmount],[OrderStatusGroup],[OrderStatusElementCode], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate]) VALUES (@CustomerId,@TotalOrderAmount,@OrderStatusGroup,@OrderStatusElementCode, @CreatedById, @CreatedDate, @LastUpdatedById, @LastUpdatedDate);
                            SELECT CAST(SCOPE_IDENTITY() as int)";

            var id = db.Connection.Query<int>(sql, order).Single();
            return id;
        }

        public bool InsertOrderDetails(List<OrderDetails> orderDetails)
        {
            string processQuery = "INSERT INTO OrderDetails ([OrderId],[ProductId],[Quantity],[CGST],[SGST],[IGST],[Discount],[NetPrice], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate]) VALUES (@OrderId,@ProductId,@Quantity,@CGST,@SGST,@IGST,@Discount,@NetPrice, @CreatedById, @CreatedDate, @LastUpdatedById, @LastUpdatedDate)";
            return db.Connection.Execute(processQuery, orderDetails) > 0;
        }

        public int UpdateOrder(Order order)
        {
            string sql = @"UPDATE [Orders] SET [TotalOrderAmount] = @TotalOrderAmount, 
                            [OrderStatusGroup] = @OrderStatusGroup, [OrderStatusElementCode] = @OrderStatusElementCode, [LastUpdatedById] = @LastUpdatedById, [LastUpdatedDate] = @LastUpdatedDate 
                             WHERE OrderId = @OrderId";

            var id = db.Connection.Query<int>(sql, order).Single();
            return id;
        }

        public bool UpdateOrderDetails(List<OrderDetails> orderDetails)
        {
            string processQuery = @"UPDATE [OrderDetails] SET [Quantity] = @Quantity,
                                    [CGST] = @CGST, [SGST] = @SGST, [IGST] = @IGST, [Discount] = @Discount,
                                    [NetPrice] = @NetPrice, [LastUpdatedById] = @LastUpdatedById, [LastUpdatedDate] = @LastUpdatedDate WHERE [OrderId] = @OrderId";
            return db.Connection.Execute(processQuery, orderDetails) > 0;
        }

        public int DeleteOrder(int orderId)
        {
            var result = db.Connection.Execute("DELETE FROM [Orders] WHERE [OrderId] = @OrderId", new { orderId });
            return result;
        }

        public bool DeleteOrderDetails(int orderId)
        {
            var result = db.Connection.Execute("DELETE FROM [OrderDetails] WHERE [OrderId] = @OrderId", new { orderId });
            return result > 0;
        }

        public Order GetOrder(int orderId)
        {
            string query = "SELECT * FROM [dbo].[Orders] WHERE [OrderId] = @OrderId";
            var result = db.Connection.Query(query, new { orderId }).FirstOrDefault();
            return result;
        }

        public OrderDetails GetOrderDetail(int orderId)
        {
            string query = "SELECT * FROM [dbo].[OrderDetails] WHERE [OrderId] = @OrderId";
            var result = db.Connection.Query(query, new { orderId }).FirstOrDefault();
            return result;
        }

        public IEnumerable<Order> GetAllOrder()
        {
            var result = db.Connection.Query<Order>("SELECT * FROM [Orders]");
            return result;
        }

        public IEnumerable<OrderDetails> GetAllOrderDetails()
        {
            var result = db.Connection.Query<OrderDetails>("SELECT * FROM [dbo].[OrderDetails]");
            return result;
        }
    }
}
