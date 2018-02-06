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
    }
}
