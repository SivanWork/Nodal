using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using System.Collections.Generic;
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
        public List<BulkOrderResponse> CreateBulkOrder(List<OrderRequest> request)
        {
            List<BulkOrderResponse> orderResponse = new List<BulkOrderResponse>();
            foreach (OrderRequest item in request)
            {
                var res = service.InsertOrder(item);
                orderResponse.Add(new BulkOrderResponse()
                {
                    orderId = item.order.OrderId,
                    Success = res.Success
                });
            }
            return orderResponse;
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
