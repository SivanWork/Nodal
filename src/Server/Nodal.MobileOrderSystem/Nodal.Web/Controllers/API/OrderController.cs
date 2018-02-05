using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using System.Web.Http;

namespace Nodal.Web.Controllers.API
{
    public class OrderController : BaseApiController
    {
        private readonly OrderService service = new OrderService();

        [HttpPost]
        public BaseResponse Create(OrderRequest request)
        {
            return service.InsertOrder(request);
        }

        [HttpPost]
        public BaseResponse UpdateOrder(OrderRequest request)
        {
            return service.UpdateOrder(request);
        }

        [HttpDelete]
        public BaseResponse DeleteOrder(int orderId)
        {
            return service.DeleteOrder(orderId);
        }

        [HttpGet]
        public OrderResponse GetOrder(int orderId)
        {
            return service.GetOrder(orderId);
        }

        [HttpGet]
        public OrderResponse GetAllOrders()
        {
            return service.GetAllOrders();
        }

        [HttpGet]
        public OrderResponse GetOrderDetail(int orderId)
        {
            return service.GetOrderDetail(orderId);
        }

        [HttpGet]
        public OrderResponse GetAllOrderDetails()
        {
            return service.GetAllOrderDetails();
        }
    }
}
