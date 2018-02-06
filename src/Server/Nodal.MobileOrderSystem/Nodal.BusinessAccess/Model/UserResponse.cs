using Nodal.Common.Schema;
using System.Collections.Generic;

namespace Nodal.BusinessAccess.Model
{
    public class UserResponse : BaseResponse
    {
        public User user { get; set; }
        public List<User> userList { get; set; }
    }
}
