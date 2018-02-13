using System;

namespace Nodal.Common.Schema
{
    public class OrderDetails
    {
        public int Id { get; set; }
        public int OrderId { get; set; }
        public int ProductId { get; set; }
        public int Quantity { get; set; }
        public float CGST { get; set; }
        public float SGST { get; set; }
        public float IGST { get; set; }
        public float Discount { get; set; }
        public float NetPrice { get; set; }
        public int CreatedById { get; set; }
        public DateTime CreatedDate { get; set; }
        public int LastUpdatedById { get; set; }
        public DateTime LastUpdatedDate { get; set; }

        public override string ToString()
        {
            return Id.ToString();
        }
    }
}
