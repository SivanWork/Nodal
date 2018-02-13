using System;

namespace Nodal.Common.Schema
{
    public class GroupType
    {
        public int Id { get; set; }
        public string GroupCode { get; set; }
        public string GroupName { get; set; }
        public bool IsActive { get; set; }
        public int CreatedById { get; set; }
        public DateTime CreatedDate { get; set; }
        public int LastUpdatedById { get; set; }
        public DateTime LastUpdatedDate { get; set; }

        public override string ToString()
        {
            return GroupCode + " " + GroupName;
        }
    }
}
