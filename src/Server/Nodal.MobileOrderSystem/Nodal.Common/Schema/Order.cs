using System;
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
        public int CreatedById { get; set; }
        public DateTime CreatedDate { get; set; }
        public int LastUpdatedById { get; set; }
        public DateTime LastUpdatedDate { get; set; }
    }
}
