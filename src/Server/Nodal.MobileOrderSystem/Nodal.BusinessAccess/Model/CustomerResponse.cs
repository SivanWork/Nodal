using Nodal.Common.Schema;
using System.Collections.Generic;

namespace Nodal.BusinessAccess.Model
{
    public class CustomerResponse : BaseResponse
    {
        public Customer customer { get; set; }

        public List<Customer> customerList { get; set; }
    }
}
