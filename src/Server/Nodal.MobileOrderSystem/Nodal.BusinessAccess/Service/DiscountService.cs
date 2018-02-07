using Nodal.BusinessAccess.Model;
using Nodal.DataAccess;
using System;
using System.Linq;

namespace Nodal.BusinessAccess.Service
{
    public class DiscountService
    {
        private DiscountRepository repository;

        public DiscountService()
        {
            repository = new DiscountRepository();
        }

        public BaseResponse InsertDiscount(DiscountRequest request)
        {
            BaseResponse discountResponse = new BaseResponse();
            try
            {
                var orderID = repository.InsertDiscount(request.discount);
                discountResponse.Success = true;
            }
            catch (Exception ex)
            {
                discountResponse.Success = false;
                discountResponse.Message = ex.Message;
                discountResponse.StackTrace = ex.StackTrace;
            }

            return discountResponse;
        }

        public BaseResponse UpdateDiscount(DiscountRequest request)
        {
            BaseResponse discountResponse = new BaseResponse();
            try
            {
                var orderID = repository.UpdateDiscount(request.discount);
                discountResponse.Success = true;
            }
            catch (Exception ex)
            {
                discountResponse.Success = false;
                discountResponse.Message = ex.Message;
                discountResponse.StackTrace = ex.StackTrace;
            }

            return discountResponse;
        }

        public BaseResponse DeleteDiscount(int discountId)
        {
            BaseResponse discountResponse = new BaseResponse();
            try
            {
                repository.DeleteDiscount(discountId);
                discountResponse.Success = true;
            }
            catch (Exception ex)
            {
                discountResponse.Success = false;
                discountResponse.Message = ex.Message;
                discountResponse.StackTrace = ex.StackTrace;
            }

            return discountResponse;
        }

        public DiscountResponse GetDiscount(int discountId)
        {
            DiscountResponse discountResponse = new DiscountResponse();
            try
            {
                discountResponse.discount = repository.GetDiscount(discountId);
                discountResponse.Success = true;
            }
            catch (Exception ex)
            {
                discountResponse.Success = false;
                discountResponse.Message = ex.Message;
                discountResponse.StackTrace = ex.StackTrace;
            }
            return discountResponse;
        }

        public DiscountResponse GetAllDiscounts()
        {
            DiscountResponse discountResponse = new DiscountResponse();
            try
            {
                discountResponse.discountList = repository.GetAllDiscounts().ToList();
                discountResponse.Success = true;
            }
            catch (Exception ex)
            {
                discountResponse.Success = false;
                discountResponse.Message = ex.Message;
                discountResponse.StackTrace = ex.StackTrace;
            }

            return discountResponse;
        }
    }
}
