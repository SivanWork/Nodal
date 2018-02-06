using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using System.Web.Http;

namespace Nodal.Web.Controllers.API
{
    public class SchemeProductController : BaseApiController
    {
        private readonly SchemeProductService service = new SchemeProductService();

        [HttpPost]
        public BaseResponse CreateSchemeProduct(SchemeProductRequest request)
        {
            return service.InsertSchemeProduct(request);
        }

        [HttpPost]
        public BaseResponse UpdateSchemeProduct(SchemeProductRequest request)
        {
            return service.UpdateSchemeProduct(request);
        }

        [HttpDelete]
        public BaseResponse DeleteSchemeProduct(int schemeProductId)
        {
            return service.DeleteSchemeProduct(schemeProductId);
        }

        [HttpGet]
        public SchemeProductResponse GetSchemeProduct(int schemeProductId)
        {
            return service.GetSchemeProduct(schemeProductId);
        }

        [HttpGet]
        public SchemeProductResponse GetAllSchemeProducts()
        {
            return service.GetAllSchemeProducts();
        }
    }
}