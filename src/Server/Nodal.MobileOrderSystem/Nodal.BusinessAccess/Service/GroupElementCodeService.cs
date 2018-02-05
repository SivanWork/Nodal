using Nodal.BusinessAccess.Model;
using Nodal.DataAccess;
using System;
using System.Linq;

namespace Nodal.BusinessAccess.Service
{
    public class GroupElementCodeService
    {
        private GroupElementCodeRepository repository;

        public GroupElementCodeService()
        {
            repository = new GroupElementCodeRepository();
        }

        public BaseResponse InsertGroupElementCode(GroupElementCodeRequest request)
        {
            BaseResponse groupElementCodeResponse = new BaseResponse();
            try
            {
                var orderID = repository.InsertGroupElementCode(request.groupElementCode);
                groupElementCodeResponse.Success = true;
            }
            catch (Exception ex)
            {
                groupElementCodeResponse.Success = false;
                groupElementCodeResponse.Message = ex.Message;
                groupElementCodeResponse.StackTrace = ex.StackTrace;
            }

            return groupElementCodeResponse;
        }

        public BaseResponse UpdateGroupElementCode(GroupElementCodeRequest request)
        {
            BaseResponse groupElementCodeResponse = new BaseResponse();
            try
            {
                var orderID = repository.UpdateGroupElementCode(request.groupElementCode);
                groupElementCodeResponse.Success = true;
            }
            catch (Exception ex)
            {
                groupElementCodeResponse.Success = false;
                groupElementCodeResponse.Message = ex.Message;
                groupElementCodeResponse.StackTrace = ex.StackTrace;
            }

            return groupElementCodeResponse;
        }

        public BaseResponse DeleteGroupElementCode(int groupElementCodeId)
        {
            BaseResponse groupElementCodeResponse = new BaseResponse();
            try
            {
                repository.DeleteGroupElementCode(groupElementCodeId);
                groupElementCodeResponse.Success = true;
            }
            catch (Exception ex)
            {
                groupElementCodeResponse.Success = false;
                groupElementCodeResponse.Message = ex.Message;
                groupElementCodeResponse.StackTrace = ex.StackTrace;
            }

            return groupElementCodeResponse;
        }

        public GroupElementCodeResponse GetGroupElementCode(int groupElementCodeId)
        {
            GroupElementCodeResponse groupElementCodeResponse = new GroupElementCodeResponse();
            try
            {
                groupElementCodeResponse.groupElementCode = repository.GetGroupElementCode(groupElementCodeId);
                groupElementCodeResponse.Success = true;
            }
            catch (Exception ex)
            {
                groupElementCodeResponse.Success = false;
                groupElementCodeResponse.Message = ex.Message;
                groupElementCodeResponse.StackTrace = ex.StackTrace;
            }
            return groupElementCodeResponse;
        }

        public GroupElementCodeResponse GetAllGroupElementCodes()
        {
            GroupElementCodeResponse groupElementCodeResponse = new GroupElementCodeResponse();
            try
            {
                groupElementCodeResponse.groupElementCodeList = repository.GetAllGroupElementCodes().ToList();
                groupElementCodeResponse.Success = true;
            }
            catch (Exception ex)
            {
                groupElementCodeResponse.Success = false;
                groupElementCodeResponse.Message = ex.Message;
                groupElementCodeResponse.StackTrace = ex.StackTrace;
            }

            return groupElementCodeResponse;
        }
    }
}
