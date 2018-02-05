using Nodal.Common.Schema;
using System.Collections.Generic;

namespace Nodal.BusinessAccess.Model
{
    public class GroupElementCodeResponse : BaseResponse
    {
        public GroupElementCode groupElementCode { get; set; }

        public List<GroupElementCode> groupElementCodeList { get; set; }
    }
}
