using Nodal.Common.Schema;

namespace Nodal.BusinessAccess.Model
{
    public class UserRequest
    {
        public User user { get; set; }
    }

    public class ChangePasswordRequest
    {
        public int userId { get; set; }
        public string password { get; set; }
    }
}
