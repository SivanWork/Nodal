using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using System.Web.Http;

namespace Nodal.Web.Controllers.API
{
    public class CustomerController : BaseApiController
    {
        private readonly CustomerService service = new CustomerService();

        [HttpPost]
        public BaseResponse CreateCustomer(CustomerRequest request)
        {
            return service.InsertCustomer(request);
        }

        [HttpPost]
        public BaseResponse UpdateCustomer(CustomerRequest request)
        {
            return service.UpdateCustomer(request);
        }

        [HttpDelete]
        public BaseResponse DeleteCustomer(int customerId)
        {
            return service.DeleteCustomer(customerId);
        }

        [HttpGet]
        public CustomerResponse GetCustomer(int customerId)
        {
            return service.GetCustomer(customerId);
        }

        [HttpGet]
        public CustomerResponse GetAllCustomers()
        {
            return service.GetAllCustomers();
        }
    }
}