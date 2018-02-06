using Nodal.Common.Schema;
using System.Collections.Generic;

namespace Nodal.BusinessAccess.Model
{
    public class SchemesResponse : BaseResponse
    {
        public Schemes scheme { get; set; }

        public List<Schemes> schemeList { get; set; }
    }
}
