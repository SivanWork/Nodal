using Nodal.Common.Schema;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Nodal.BusinessAccess.Model
{
    public class UserProfileResponse : BaseResponse
    {
        public UserProfile User { get; set; }

        public string AuthToken { get; set; }
    }
}
