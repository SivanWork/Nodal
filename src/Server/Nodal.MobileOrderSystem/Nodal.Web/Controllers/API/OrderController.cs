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
        public BaseResponse Update(OrderRequest request)
        {
            return service.InsertOrder(request);
        }

        [HttpDelete]
        public BaseResponse Delete(int orderId)
        {
            return service.DeleteOrder(orderId);
        }

        [HttpGet]
        public OrderResponse Get(int orderId)
        {
            return service.GetOrder(orderId);
        }

        [HttpGet]
        public OrderResponse GetAll()
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
