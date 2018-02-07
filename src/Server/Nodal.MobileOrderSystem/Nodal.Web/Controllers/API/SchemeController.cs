using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using System.Web.Http;

namespace Nodal.Web.Controllers.API
{
    public class SchemeController : BaseApiController
    {
        private readonly SchemeService service = new SchemeService();

        [HttpPost]
        public BaseResponse CreateScheme(SchemesRequest request)
        {
            return service.InsertScheme(request);
        }

        [HttpPost]
        public BaseResponse UpdateScheme(SchemesRequest request)
        {
            return service.UpdateScheme(request);
        }

        [HttpDelete]
        public BaseResponse DeleteScheme(int schemeId)
        {
            return service.DeleteScheme(schemeId);
        }

        [HttpGet]
        public SchemesResponse GetScheme(int schemeId)
        {
            return service.GetScheme(schemeId);
        }

        [HttpGet]
        public SchemesResponse GetAllSchemes()
        {
            return service.GetAllSchemes();
        }
    }
}