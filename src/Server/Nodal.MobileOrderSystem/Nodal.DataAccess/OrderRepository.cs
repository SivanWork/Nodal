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
                            INSERT INTO [Orders] ([CustomerId],[TotalOrderAmount],[OrderStatusGroup],[OrderStatusElementCode]) VALUES (@CustomerId,@TotalOrderAmount,@OrderStatusGroup,@OrderStatusElementCode);
                            SELECT CAST(SCOPE_IDENTITY() as int)";

            var id = db.Connection.Query<int>(sql, order).Single();
            return id;
        }

        public bool InsertOrderDetails(List<OrderDetails> orderDetails)
        {
            string processQuery = "INSERT INTO OrderDetails ([OrderId],[ProductId],[Quantity],[CGST],[SGST],[IGST],[Discount],[NetPrice]) VALUES (@OrderId,@ProductId,@Quantity,@CGST,@SGST,@IGST,@Discount,@NetPrice)";
            return db.Connection.Execute(processQuery, orderDetails) > 0;
        }
    }
}
