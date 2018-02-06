using Nodal.Common.Schema;
using System.Collections.Generic;

namespace Nodal.BusinessAccess.Model
{
    public class SchemeProductResponse : BaseResponse
    {
        public SchemeProduct schemeProduct { get; set; }

        public List<SchemeProduct> schemeProductList { get; set; }
    }
}
