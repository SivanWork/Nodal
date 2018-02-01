using Nodal.BusinessAccess.Model;
using Nodal.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Nodal.BusinessAccess.Service
{
    public class OrderService
    {
        private OrderRepository repository;

        public OrderService()
        {
            repository = new OrderRepository();
        }

        public BaseResponse InsertOrder(OrderRequest request)
        {
            BaseResponse orderResponse = new BaseResponse();
            try
            {
                var orderID = repository.InsertOrder(request.order);
                request.order.OrderDetails.ForEach(item => item.OrderId = orderID);
                repository.InsertOrderDetails(request.order.OrderDetails);
                orderResponse.Success = true;
            }
            catch (Exception ex)
            {
                orderResponse.Success = false;
                orderResponse.Message = ex.Message;
                orderResponse.StackTrace = ex.StackTrace;
            }

            return orderResponse;
        }
    }
}
