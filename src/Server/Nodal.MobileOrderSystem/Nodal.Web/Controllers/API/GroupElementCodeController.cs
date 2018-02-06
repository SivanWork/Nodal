using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using System.Web.Http;

namespace Nodal.Web.Controllers.API
{
    public class GroupElementCodeController : BaseApiController
    {
        private readonly GroupElementCodeService service = new GroupElementCodeService();

        [HttpPost]
        public BaseResponse CreateGroupElementCode(GroupElementCodeRequest request)
        {
            return service.InsertGroupElementCode(request);
        }

        [HttpPost]
        public BaseResponse UpdateGroupElementCode(GroupElementCodeRequest request)
        {
            return service.UpdateGroupElementCode(request);
        }

        [HttpDelete]
        public BaseResponse DeleteGroupElementCode(int groupElementCodeId)
        {
            return service.DeleteGroupElementCode(groupElementCodeId);
        }

        [HttpGet]
        public GroupElementCodeResponse GetGroupElementCode(int groupElementCodeId)
        {
            return service.GetGroupElementCode(groupElementCodeId);
        }

        [HttpGet]
        public GroupElementCodeResponse GetAllGroupElementCodes()
        {
            return service.GetAllGroupElementCodes();
        }
    }
}