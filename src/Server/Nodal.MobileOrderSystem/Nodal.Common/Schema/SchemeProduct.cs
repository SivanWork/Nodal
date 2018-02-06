namespace Nodal.Common.Schema
{
    public class SchemeProduct
    {
        public int Id { get; set; }
        public int SchemeId { get; set; }
        public int ProductId { get; set; }
        public bool IsActive { get; set; }
    }
}
