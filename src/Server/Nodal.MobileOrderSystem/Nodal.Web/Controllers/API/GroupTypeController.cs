using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using System.Web.Http;

namespace Nodal.Web.Controllers.API
{
    public class GroupTypeController : BaseApiController
    {
        private readonly GroupTypeService service = new GroupTypeService();

        [HttpPost]
        public BaseResponse CreateGroupType(GroupTypeRequest request)
        {
            return service.InsertGroupType(request);
        }

        [HttpPost]
        public BaseResponse UpdateGroupType(GroupTypeRequest request)
        {
            return service.UpdateGroupType(request);
        }

        [HttpDelete]
        public BaseResponse DeleteGroupType(int groupTypeId)
        {
            return service.DeleteGroupType(groupTypeId);
        }

        [HttpGet]
        public GroupTypeResponse GetGroupType(int groupTypeId)
        {
            return service.GetGroupType(groupTypeId);
        }

        [HttpGet]
        public GroupTypeResponse GetAllGroupTypes()
        {
            return service.GetAllGroupTypes();
        }
    }
}