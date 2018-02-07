using System;

namespace Nodal.Common.Schema
{
    public class GroupElementCode
    {
        public int Id { get; set; }
        public string ElementCode { get; set; }
        public string ElementName { get; set; }
        public int GroupType { get; set; }
        public bool IsActive { get; set; }
        public int CreatedById { get; set; }
        public DateTime CreatedDate { get; set; }
        public int LastUpdatedById { get; set; }
        public DateTime LastUpdatedDate { get; set; }
    }
}
