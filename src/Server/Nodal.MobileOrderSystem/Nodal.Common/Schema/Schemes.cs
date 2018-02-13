using System;

namespace Nodal.Common.Schema
{
    public class Schemes
    {
        public int SchemeId { get; set; }
        public string SchemeName { get; set; }
        public string SchemeCode { get; set; }
        public bool IsActive { get; set; }
        public int CreatedById { get; set; }
        public DateTime CreatedDate { get; set; }
        public int LastUpdatedById { get; set; }
        public DateTime LastUpdatedDate { get; set; }

        public override string ToString()
        {
            return SchemeName + " " + SchemeCode;
        }
    }
}
