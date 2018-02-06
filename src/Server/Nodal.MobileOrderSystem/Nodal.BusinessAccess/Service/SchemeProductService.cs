using Nodal.BusinessAccess.Model;
using Nodal.DataAccess;
using System;
using System.Linq;

namespace Nodal.BusinessAccess.Service
{
    public class SchemeProductService
    {
        private SchemeProductRepository repository;

        public SchemeProductService()
        {
            repository = new SchemeProductRepository();
        }

        public BaseResponse InsertSchemeProduct(SchemeProductRequest request)
        {
            BaseResponse schemeProductResponse = new BaseResponse();
            try
            {
                var orderID = repository.InsertSchemeProduct(request.schemeProduct);
                schemeProductResponse.Success = true;
            }
            catch (Exception ex)
            {
                schemeProductResponse.Success = false;
                schemeProductResponse.Message = ex.Message;
                schemeProductResponse.StackTrace = ex.StackTrace;
            }

            return schemeProductResponse;
        }

        public BaseResponse UpdateSchemeProduct(SchemeProductRequest request)
        {
            BaseResponse schemeProductResponse = new BaseResponse();
            try
            {
                var orderID = repository.UpdateSchemeProduct(request.schemeProduct);
                schemeProductResponse.Success = true;
            }
            catch (Exception ex)
            {
                schemeProductResponse.Success = false;
                schemeProductResponse.Message = ex.Message;
                schemeProductResponse.StackTrace = ex.StackTrace;
            }

            return schemeProductResponse;
        }

        public BaseResponse DeleteSchemeProduct(int schemeProductId)
        {
            BaseResponse schemeProductResponse = new BaseResponse();
            try
            {
                repository.DeleteSchemeProduct(schemeProductId);
                schemeProductResponse.Success = true;
            }
            catch (Exception ex)
            {
                schemeProductResponse.Success = false;
                schemeProductResponse.Message = ex.Message;
                schemeProductResponse.StackTrace = ex.StackTrace;
            }

            return schemeProductResponse;
        }

        public SchemeProductResponse GetSchemeProduct(int schemeProductId)
        {
            SchemeProductResponse schemeProductResponse = new SchemeProductResponse();
            try
            {
                schemeProductResponse.schemeProduct = repository.GetSchemeProduct(schemeProductId);
                schemeProductResponse.Success = true;
            }
            catch (Exception ex)
            {
                schemeProductResponse.Success = false;
                schemeProductResponse.Message = ex.Message;
                schemeProductResponse.StackTrace = ex.StackTrace;
            }
            return schemeProductResponse;
        }

        public SchemeProductResponse GetAllSchemeProducts()
        {
            SchemeProductResponse schemeProductResponse = new SchemeProductResponse();
            try
            {
                schemeProductResponse.schemeProductList = repository.GetSchemeProducts().ToList();
                schemeProductResponse.Success = true;
            }
            catch (Exception ex)
            {
                schemeProductResponse.Success = false;
                schemeProductResponse.Message = ex.Message;
                schemeProductResponse.StackTrace = ex.StackTrace;
            }

            return schemeProductResponse;
        }
    }
}
