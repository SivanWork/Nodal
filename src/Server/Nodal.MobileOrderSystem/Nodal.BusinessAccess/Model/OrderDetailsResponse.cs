using Nodal.Common.Schema;
using System.Collections.Generic;

namespace Nodal.BusinessAccess.Model
{
    public class OrderDetailsResponse : BaseResponse
    {
        public OrderDetails orderDetail { get; set; }

        public List<OrderDetails> orderDetailList { get; set; }
    }
}
