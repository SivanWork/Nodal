using System.Collections.Generic;

namespace Nodal.Common.Schema
{
    public class Order
    {
        public int OrderId { get; set; }
        public int CustomerId { get; set; }
        public float TotalOrderAmount { get; set; }
        public int OrderStatusGroup { get; set; }
        public int OrderStatusElementCode { get; set; }
        public List<OrderDetails> OrderDetails { get; set; }

    }
}
