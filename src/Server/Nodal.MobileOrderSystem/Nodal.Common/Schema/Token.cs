using System;

namespace Nodal.Common.Schema
{
    public class Token
    {
        public int UserId { get; set; }
        public Guid AuthToken { get; set; }
        public DateTime IssuedOn { get; set; }
    }
}
