using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using System.Web.Http;

namespace Nodal.Web.Controllers.API
{
    public class OrderReturnDetailController : BaseApiController
    {
        private readonly OrderReturnDetailService service = new OrderReturnDetailService();

        [HttpPost]
        public BaseResponse CreateOrderReturnDetail(OrderReturnDetailsRequest request)
        {
            return service.InsertOrderReturnDetail(request);
        }

        [HttpPost]
        public BaseResponse UpdateOrderReturnDetail(OrderReturnDetailsRequest request)
        {
            return service.UpdateOrderReturnDetail(request);
        }

        [HttpDelete]
        public BaseResponse DeleteOrderReturnDetail(int orderReturnDetailId)
        {
            return service.DeleteOrderReturnDetail(orderReturnDetailId);
        }

        [HttpGet]
        public OrderReturnDetailsResponse GetOrderReturnDetail(int orderReturnDetailId)
        {
            return service.GetOrderReturnDetail(orderReturnDetailId);
        }

        [HttpGet]
        public OrderReturnDetailsResponse GetAllOrderReturnDetails()
        {
            return service.GetAllOrderReturnDetails();
        }
    }
}