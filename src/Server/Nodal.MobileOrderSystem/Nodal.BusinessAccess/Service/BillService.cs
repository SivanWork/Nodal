using Nodal.BusinessAccess.Model;
using Nodal.DataAccess;
using System;
using System.Linq;

namespace Nodal.BusinessAccess.Service
{
    public class BillService
    {
        private BillsRepository repository;

        public BillService()
        {
            repository = new BillsRepository();
        }

        public BaseResponse InsertBill(BillRequest request)
        {
            BaseResponse billResponse = new BaseResponse();
            try
            {
                var orderID = repository.InsertBill(request.bill);
                billResponse.Success = true;
            }
            catch (Exception ex)
            {
                billResponse.Success = false;
                billResponse.Message = ex.Message;
                billResponse.StackTrace = ex.StackTrace;
            }

            return billResponse;
        }

        public BaseResponse UpdateBill(BillRequest request)
        {
            BaseResponse billResponse = new BaseResponse();
            try
            {
                var orderID = repository.UpdateBill(request.bill);
                billResponse.Success = true;
            }
            catch (Exception ex)
            {
                billResponse.Success = false;
                billResponse.Message = ex.Message;
                billResponse.StackTrace = ex.StackTrace;
            }

            return billResponse;
        }

        public BaseResponse DeleteBill(int billId)
        {
            BaseResponse billResponse = new BaseResponse();
            try
            {
                repository.DeleteBill(billId);
                billResponse.Success = true;
            }
            catch (Exception ex)
            {
                billResponse.Success = false;
                billResponse.Message = ex.Message;
                billResponse.StackTrace = ex.StackTrace;
            }

            return billResponse;
        }

        public BillResponse GetBill(int billId)
        {
            BillResponse billResponse = new BillResponse();
            try
            {
                billResponse.bill = repository.GetBill(billId);
                billResponse.Success = true;
            }
            catch (Exception ex)
            {
                billResponse.Success = false;
                billResponse.Message = ex.Message;
                billResponse.StackTrace = ex.StackTrace;
            }
            return billResponse;
        }

        public BillResponse GetAllBills()
        {
            BillResponse billResponse = new BillResponse();
            try
            {
                billResponse.billList = repository.GetAllBills().ToList();
                billResponse.Success = true;
            }
            catch (Exception ex)
            {
                billResponse.Success = false;
                billResponse.Message = ex.Message;
                billResponse.StackTrace = ex.StackTrace;
            }

            return billResponse;
        }
    }
}
