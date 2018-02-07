namespace Nodal.Common.Schema
{
    public class Products
    {
        public int ProductId { get; set; }
        public string ProductName { get; set; }
        public float MRP { get; set; }
        public float DealerPrice { get; set; }
        public float WholesalePrice { get; set; }
        public float CGST { get; set; }
        public float SGST { get; set; }
        public float IGST { get; set; }
        public bool IsActive { get; set; }
    }
}
