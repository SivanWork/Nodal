using System;

namespace Nodal.Common.Schema
{
    public class Discounts
    {
        public int Id { get; set; }
        public int ProductId { get; set; }
        public string DiscountName { get; set; }
        public string DiscountType { get; set; }
        public float Discount { get; set; }
        public bool IsActive { get; set; }
        public int CreatedById { get; set; }
        public DateTime CreatedDate { get; set; }
        public int LastUpdatedById { get; set; }
        public DateTime LastUpdatedDate { get; set; }
    }
}
