using Nodal.BusinessAccess.Model;
using Nodal.DataAccess;
using System;
using System.Linq;

namespace Nodal.BusinessAccess.Service
{
    public class OrderDetailService
    {
        private OrderDetailsRepository repository;

        public OrderDetailService()
        {
            repository = new OrderDetailsRepository();
        }

        public BaseResponse InsertOrderDetail(OrderDetailsRequest request)
        {
            BaseResponse orderDetailResponse = new BaseResponse();
            try
            {
                var orderID = repository.InsertOrderDetail(request.orderDetail);
                orderDetailResponse.Success = true;
            }
            catch (Exception ex)
            {
                orderDetailResponse.Success = false;
                orderDetailResponse.Message = ex.Message;
                orderDetailResponse.StackTrace = ex.StackTrace;
            }

            return orderDetailResponse;
        }

        public BaseResponse UpdateOrderDetail(OrderDetailsRequest request)
        {
            BaseResponse orderDetailResponse = new BaseResponse();
            try
            {
                var orderID = repository.UpdateOrderDetail(request.orderDetail);
                orderDetailResponse.Success = true;
            }
            catch (Exception ex)
            {
                orderDetailResponse.Success = false;
                orderDetailResponse.Message = ex.Message;
                orderDetailResponse.StackTrace = ex.StackTrace;
            }

            return orderDetailResponse;
        }

        public BaseResponse DeleteOrderDetail(int orderDetailId)
        {
            BaseResponse orderDetailResponse = new BaseResponse();
            try
            {
                repository.DeleteOrderDetail(orderDetailId);
                orderDetailResponse.Success = true;
            }
            catch (Exception ex)
            {
                orderDetailResponse.Success = false;
                orderDetailResponse.Message = ex.Message;
                orderDetailResponse.StackTrace = ex.StackTrace;
            }

            return orderDetailResponse;
        }

        public OrderDetailsResponse GetOrderDetail(int orderDetailId)
        {
            OrderDetailsResponse orderDetailResponse = new OrderDetailsResponse();
            try
            {
                orderDetailResponse.orderDetail = repository.GetOrderDetail(orderDetailId);
                orderDetailResponse.Success = true;
            }
            catch (Exception ex)
            {
                orderDetailResponse.Success = false;
                orderDetailResponse.Message = ex.Message;
                orderDetailResponse.StackTrace = ex.StackTrace;
            }
            return orderDetailResponse;
        }

        public OrderDetailsResponse GetAllOrderDetails()
        {
            OrderDetailsResponse orderDetailResponse = new OrderDetailsResponse();
            try
            {
                orderDetailResponse.orderDetailList = repository.GetOrderDetails().ToList();
                orderDetailResponse.Success = true;
            }
            catch (Exception ex)
            {
                orderDetailResponse.Success = false;
                orderDetailResponse.Message = ex.Message;
                orderDetailResponse.StackTrace = ex.StackTrace;
            }

            return orderDetailResponse;
        }
    }
}
