using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using System.Web.Http;

namespace Nodal.Web.Controllers.API
{
    public class ProductController : BaseApiController
    {
        private readonly ProductService service = new ProductService();

        [HttpPost]
        public BaseResponse CreateProduct(ProductsRequest request)
        {
            return service.InsertProduct(request);
        }

        [HttpPost]
        public BaseResponse UpdateProduct(ProductsRequest request)
        {
            return service.UpdateProduct(request);
        }

        [HttpDelete]
        public BaseResponse DeleteProduct(int productId)
        {
            return service.DeleteProduct(productId);
        }

        [HttpGet]
        public ProductsResponse GetProduct(int productId)
        {
            return service.GetProduct(productId);
        }

        [HttpGet]
        public ProductsResponse GetAllProducts()
        {
            return service.GetAllProducts();
        }
    }
}