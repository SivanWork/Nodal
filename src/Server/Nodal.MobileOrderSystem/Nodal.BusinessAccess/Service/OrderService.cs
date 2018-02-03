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

        public BaseResponse UpdateOrder(OrderRequest request)
        {
            BaseResponse orderResponse = new BaseResponse();
            try
            {
                var orderID = repository.UpdateOrder(request.order);
                request.order.OrderDetails.ForEach(item => item.OrderId = orderID);
                repository.UpdateOrderDetails(request.order.OrderDetails);
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

        public BaseResponse DeleteOrder(int orderId)
        {
            BaseResponse orderResponse = new BaseResponse();
            try
            {
                repository.DeleteOrder(orderId);
                repository.DeleteOrderDetails(orderId);
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

        public OrderResponse GetOrder(int orderId)
        {
            OrderResponse orderResponse = new OrderResponse();
            try
            {
                orderResponse.order = repository.GetOrder(orderId);
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

        public OrderResponse GetOrderDetail(int orderId)
        {
            OrderResponse orderResponse = new OrderResponse();
            try
            {
                orderResponse.orderDetail = repository.GetOrderDetail(orderId);
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

        public OrderResponse GetAllOrders()
        {
            OrderResponse orderResponse = new OrderResponse();
            try
            {
                orderResponse.orderList = repository.GetAllOrder().ToList();
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

        public OrderResponse GetAllOrderDetails()
        {
            OrderResponse orderResponse = new OrderResponse();
            try
            {
                orderResponse.OrderDetailList = repository.GetAllOrderDetails().ToList();
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
