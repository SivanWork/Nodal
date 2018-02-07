using Nodal.Common.Schema;
using System;

namespace Nodal.BusinessAccess.Model
{
    public class UserProfileResponse : BaseResponse
    {
        public UserProfile User { get; set; }

        public Guid AuthToken { get; set; }
    }
}
