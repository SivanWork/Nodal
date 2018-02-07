using Nodal.BusinessAccess.Model;
using Nodal.DataAccess;
using System;
using System.Linq;

namespace Nodal.BusinessAccess.Service
{
    public class OrderReturnDetailService
    {
        private OrderReturnDetailsRepository repository;

        public OrderReturnDetailService()
        {
            repository = new OrderReturnDetailsRepository();
        }

        public BaseResponse InsertOrderReturnDetail(OrderReturnDetailsRequest request)
        {
            BaseResponse orderReturnDetailResponse = new BaseResponse();
            try
            {
                var orderID = repository.InsertOrderReturnDetail(request.orderReturnDetail);
                orderReturnDetailResponse.Success = true;
            }
            catch (Exception ex)
            {
                orderReturnDetailResponse.Success = false;
                orderReturnDetailResponse.Message = ex.Message;
                orderReturnDetailResponse.StackTrace = ex.StackTrace;
            }

            return orderReturnDetailResponse;
        }

        public BaseResponse UpdateOrderReturnDetail(OrderReturnDetailsRequest request)
        {
            BaseResponse orderReturnDetailResponse = new BaseResponse();
            try
            {
                var orderID = repository.UpdateOrderReturnDetail(request.orderReturnDetail);
                orderReturnDetailResponse.Success = true;
            }
            catch (Exception ex)
            {
                orderReturnDetailResponse.Success = false;
                orderReturnDetailResponse.Message = ex.Message;
                orderReturnDetailResponse.StackTrace = ex.StackTrace;
            }

            return orderReturnDetailResponse;
        }

        public BaseResponse DeleteOrderReturnDetail(int orderReturnDetailId)
        {
            BaseResponse orderReturnDetailResponse = new BaseResponse();
            try
            {
                repository.DeleteOrderReturnDetail(orderReturnDetailId);
                orderReturnDetailResponse.Success = true;
            }
            catch (Exception ex)
            {
                orderReturnDetailResponse.Success = false;
                orderReturnDetailResponse.Message = ex.Message;
                orderReturnDetailResponse.StackTrace = ex.StackTrace;
            }

            return orderReturnDetailResponse;
        }

        public OrderReturnDetailsResponse GetOrderReturnDetail(int orderReturnDetailId)
        {
            OrderReturnDetailsResponse orderReturnDetailResponse = new OrderReturnDetailsResponse();
            try
            {
                orderReturnDetailResponse.orderReturnDetails = repository.GetOrderReturnDetail(orderReturnDetailId);
                orderReturnDetailResponse.Success = true;
            }
            catch (Exception ex)
            {
                orderReturnDetailResponse.Success = false;
                orderReturnDetailResponse.Message = ex.Message;
                orderReturnDetailResponse.StackTrace = ex.StackTrace;
            }
            return orderReturnDetailResponse;
        }

        public OrderReturnDetailsResponse GetAllOrderReturnDetails()
        {
            OrderReturnDetailsResponse orderReturnDetailResponse = new OrderReturnDetailsResponse();
            try
            {
                orderReturnDetailResponse.orderReturnDetailList = repository.GetOrderReturnDetails().ToList();
                orderReturnDetailResponse.Success = true;
            }
            catch (Exception ex)
            {
                orderReturnDetailResponse.Success = false;
                orderReturnDetailResponse.Message = ex.Message;
                orderReturnDetailResponse.StackTrace = ex.StackTrace;
            }

            return orderReturnDetailResponse;
        }
    }
}
