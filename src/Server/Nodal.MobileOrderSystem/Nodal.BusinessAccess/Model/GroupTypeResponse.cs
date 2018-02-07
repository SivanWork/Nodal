using Nodal.Common.Schema;
using System.Collections.Generic;

namespace Nodal.BusinessAccess.Model
{
    public class GroupTypeResponse : BaseResponse
    {
        public GroupType groupType { get; set; }

        public List<GroupType> groupTypeList { get; set; }
    }
}
