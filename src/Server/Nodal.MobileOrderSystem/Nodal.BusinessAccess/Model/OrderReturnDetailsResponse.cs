using Nodal.Common.Schema;
using System.Collections.Generic;

namespace Nodal.BusinessAccess.Model
{
    public class OrderReturnDetailsResponse : BaseResponse
    {
        public OrderReturnDetails orderReturnDetails { get; set; }

        public List<OrderReturnDetails> orderReturnDetailList { get; set; }
    }
}
