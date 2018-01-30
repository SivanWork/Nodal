using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Nodal.BusinessAccess.Model
{
    public class BaseResponse
    {
        public bool Success { get; set; }

        public bool IsWarning { get; set; }

        public string Message { get; set; }

        public string StackTrace { get; set; }
    }
}
