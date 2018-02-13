using System;

namespace Nodal.Common.Schema
{
    public class SchemeProduct
    {
        public int Id { get; set; }
        public int SchemeId { get; set; }
        public int ProductId { get; set; }
        public bool IsActive { get; set; }
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
