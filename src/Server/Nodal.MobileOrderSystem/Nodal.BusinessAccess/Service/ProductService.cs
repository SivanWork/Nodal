using Nodal.BusinessAccess.Model;
using Nodal.DataAccess;
using System;
using System.Linq;

namespace Nodal.BusinessAccess.Service
{
    public class ProductService
    {
        private ProductsRepository repository;

        public ProductService()
        {
            repository = new ProductsRepository();
        }

        public BaseResponse InsertProduct(ProductsRequest request)
        {
            BaseResponse productResponse = new BaseResponse();
            try
            {
                var orderID = repository.InsertProduct(request.product);
                productResponse.Success = true;
            }
            catch (Exception ex)
            {
                productResponse.Success = false;
                productResponse.Message = ex.Message;
                productResponse.StackTrace = ex.StackTrace;
            }

            return productResponse;
        }

        public BaseResponse UpdateProduct(ProductsRequest request)
        {
            BaseResponse productResponse = new BaseResponse();
            try
            {
                var orderID = repository.UpdateProduct(request.product);
                productResponse.Success = true;
            }
            catch (Exception ex)
            {
                productResponse.Success = false;
                productResponse.Message = ex.Message;
                productResponse.StackTrace = ex.StackTrace;
            }

            return productResponse;
        }

        public BaseResponse DeleteProduct(int productId)
        {
            BaseResponse productResponse = new BaseResponse();
            try
            {
                repository.DeleteProduct(productId);
                productResponse.Success = true;
            }
            catch (Exception ex)
            {
                productResponse.Success = false;
                productResponse.Message = ex.Message;
                productResponse.StackTrace = ex.StackTrace;
            }

            return productResponse;
        }

        public ProductsResponse GetProduct(int productId)
        {
            ProductsResponse productResponse = new ProductsResponse();
            try
            {
                productResponse.product = repository.GetProduct(productId);
                productResponse.Success = true;
            }
            catch (Exception ex)
            {
                productResponse.Success = false;
                productResponse.Message = ex.Message;
                productResponse.StackTrace = ex.StackTrace;
            }
            return productResponse;
        }

        public ProductsResponse GetAllProducts()
        {
            ProductsResponse productResponse = new ProductsResponse();
            try
            {
                productResponse.productList = repository.GetProducts().ToList();
                productResponse.Success = true;
            }
            catch (Exception ex)
            {
                productResponse.Success = false;
                productResponse.Message = ex.Message;
                productResponse.StackTrace = ex.StackTrace;
            }

            return productResponse;
        }
    }
}
