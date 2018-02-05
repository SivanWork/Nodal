using Nodal.Common.Schema;
using System.Collections.Generic;

namespace Nodal.BusinessAccess.Model
{
    public class DiscountResponse : BaseResponse
    {
        public Discounts discount { get; set; }

        public List<Discounts> discountList { get; set; }
    }
}
