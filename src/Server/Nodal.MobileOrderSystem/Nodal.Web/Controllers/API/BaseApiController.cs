using Nodal.BusinessAccess.Model;
using Nodal.Web.ActionFilters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Nodal.Web.Controllers.API
{
    [AuthorizationRequired]
    public class BaseApiController : ApiController
    {
        protected BaseRequest RequestConfig
        {
            get
            {
                object requestConfig;
                Request.Properties.TryGetValue("requestConfig", out requestConfig);
                return (BaseRequest)requestConfig;
            }
        }
    }
}
