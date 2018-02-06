using Nodal.Common.Schema;
using System.Collections.Generic;

namespace Nodal.BusinessAccess.Model
{
    public class BillResponse : BaseResponse
    {
        public Bills bill { get; set; }

        public List<Bills> billList { get; set; }
    }
}
