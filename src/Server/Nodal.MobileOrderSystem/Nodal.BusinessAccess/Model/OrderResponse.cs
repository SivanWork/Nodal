using Nodal.Common.Schema;
using System.Collections.Generic;

namespace Nodal.BusinessAccess.Model
{
    public class OrderResponse : BaseResponse
    {
        public Order order { get; set; }

        public List<Order> orderList { get; set; }

        public OrderDetails orderDetail { get; set; }

        public List<OrderDetails> OrderDetailList { get; set; }
    }

    public class BulkOrderResponse : BaseResponse
    {
        public int orderId { get; set; }
    }
}
