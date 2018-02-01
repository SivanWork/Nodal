using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Nodal.Web.Controllers.API
{
    public class OrderController : BaseApiController
    {
        private readonly OrderService service = new OrderService();

        [HttpPost]
        public BaseResponse Create(OrderRequest request)
        {
            return service.InsertOrder(request);
        }
    }
}
