using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using System.Web.Http;

namespace Nodal.Web.Controllers.API
{
    public class OrderDetailController : BaseApiController
    {
        private readonly OrderDetailService service = new OrderDetailService();

        [HttpPost]
        public BaseResponse CreateOrderDetail(OrderDetailsRequest request)
        {
            return service.InsertOrderDetail(request);
        }

        [HttpPost]
        public BaseResponse UpdateOrderDetail(OrderDetailsRequest request)
        {
            return service.UpdateOrderDetail(request);
        }

        [HttpDelete]
        public BaseResponse DeleteOrderDetail(int orderDetailId)
        {
            return service.DeleteOrderDetail(orderDetailId);
        }

        [HttpGet]
        public OrderDetailsResponse GetOrderDetail(int orderDetailId)
        {
            return service.GetOrderDetail(orderDetailId);
        }

        [HttpGet]
        public OrderDetailsResponse GetAllOrderDetails()
        {
            return service.GetAllOrderDetails();
        }
    }
}