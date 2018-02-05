using Nodal.BusinessAccess.Model;
using Nodal.BusinessAccess.Service;
using System.Web.Http;

namespace Nodal.Web.Controllers.API
{
    public class BillController : BaseApiController
    {
        private readonly BillService service = new BillService();

        [HttpPost]
        public BaseResponse CreateBill(BillRequest request)
        {
            return service.InsertBill(request);
        }

        [HttpPost]
        public BaseResponse UpdateBill(BillRequest request)
        {
            return service.UpdateBill(request);
        }

        [HttpDelete]
        public BaseResponse DeleteBill(int billId)
        {
            return service.DeleteBill(billId);
        }

        [HttpGet]
        public BillResponse GetBill(int billId)
        {
            return service.GetBill(billId);
        }

        [HttpGet]
        public BillResponse GetAllBills()
        {
            return service.GetAllBills();
        }
    }
}