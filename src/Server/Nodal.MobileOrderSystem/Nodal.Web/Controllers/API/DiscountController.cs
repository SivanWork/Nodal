using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using System.Web.Http;

namespace Nodal.Web.Controllers.API
{
    public class DiscountController : BaseApiController
    {
        private readonly DiscountService service = new DiscountService();

        [HttpPost]
        public BaseResponse CreateDiscount(DiscountRequest request)
        {
            return service.InsertDiscount(request);
        }

        [HttpPost]
        public BaseResponse UpdateDiscount(DiscountRequest request)
        {
            return service.UpdateDiscount(request);
        }

        [HttpDelete]
        public BaseResponse DeleteDiscount(int discountId)
        {
            return service.DeleteDiscount(discountId);
        }

        [HttpGet]
        public DiscountResponse GetDiscount(int discountId)
        {
            return service.GetDiscount(discountId);
        }

        [HttpGet]
        public DiscountResponse GetAllDiscounts()
        {
            return service.GetAllDiscounts();
        }
    }
}