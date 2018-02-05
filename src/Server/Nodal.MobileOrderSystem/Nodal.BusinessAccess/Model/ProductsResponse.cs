using Nodal.Common.Schema;
using System.Collections.Generic;

namespace Nodal.BusinessAccess.Model
{
    public class ProductsResponse : BaseResponse
    {
        public Products product { get; set; }

        public List<Products> productList { get; set; }
    }
}
