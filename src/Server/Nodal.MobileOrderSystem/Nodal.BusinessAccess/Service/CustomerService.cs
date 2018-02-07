using Nodal.BusinessAccess.Model;
using Nodal.DataAccess;
using System;
using System.Linq;

namespace Nodal.BusinessAccess.Service
{
    public class CustomerService
    {
        private CustomerRepository repository;

        public CustomerService()
        {
            repository = new CustomerRepository();
        }

        public BaseResponse InsertCustomer(CustomerRequest request)
        {
            BaseResponse customerResponse = new BaseResponse();
            try
            {
                var orderID = repository.InsertCustomer(request.customer);
                customerResponse.Success = true;
            }
            catch (Exception ex)
            {
                customerResponse.Success = false;
                customerResponse.Message = ex.Message;
                customerResponse.StackTrace = ex.StackTrace;
            }

            return customerResponse;
        }

        public BaseResponse UpdateCustomer(CustomerRequest request)
        {
            BaseResponse customerResponse = new BaseResponse();
            try
            {
                var orderID = repository.UpdateCustomer(request.customer);
                customerResponse.Success = true;
            }
            catch (Exception ex)
            {
                customerResponse.Success = false;
                customerResponse.Message = ex.Message;
                customerResponse.StackTrace = ex.StackTrace;
            }

            return customerResponse;
        }

        public BaseResponse DeleteCustomer(int customerId)
        {
            BaseResponse customerResponse = new BaseResponse();
            try
            {
                repository.DeleteCustomer(customerId);
                customerResponse.Success = true;
            }
            catch (Exception ex)
            {
                customerResponse.Success = false;
                customerResponse.Message = ex.Message;
                customerResponse.StackTrace = ex.StackTrace;
            }

            return customerResponse;
        }

        public CustomerResponse GetCustomer(int customerId)
        {
            CustomerResponse customerResponse = new CustomerResponse();
            try
            {
                customerResponse.customer = repository.GetCustomer(customerId);
                customerResponse.Success = true;
            }
            catch (Exception ex)
            {
                customerResponse.Success = false;
                customerResponse.Message = ex.Message;
                customerResponse.StackTrace = ex.StackTrace;
            }
            return customerResponse;
        }

        public CustomerResponse GetAllCustomers()
        {
            CustomerResponse customerResponse = new CustomerResponse();
            try
            {
                customerResponse.customerList = repository.GetAllCustomers().ToList();
                customerResponse.Success = true;
            }
            catch (Exception ex)
            {
                customerResponse.Success = false;
                customerResponse.Message = ex.Message;
                customerResponse.StackTrace = ex.StackTrace;
            }

            return customerResponse;
        }
    }
}
