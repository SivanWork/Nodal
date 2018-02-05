using Nodal.BusinessAccess.Model;
using Nodal.DataAccess;
using System;
using System.Linq;

namespace Nodal.BusinessAccess.Service
{
    public class GroupTypeService
    {
        private GroupTypeRepository repository;

        public GroupTypeService()
        {
            repository = new GroupTypeRepository();
        }

        public BaseResponse InsertGroupType(GroupTypeRequest request)
        {
            BaseResponse groupTypeResponse = new BaseResponse();
            try
            {
                var orderID = repository.InsertGroupType(request.groupType);
                groupTypeResponse.Success = true;
            }
            catch (Exception ex)
            {
                groupTypeResponse.Success = false;
                groupTypeResponse.Message = ex.Message;
                groupTypeResponse.StackTrace = ex.StackTrace;
            }

            return groupTypeResponse;
        }

        public BaseResponse UpdateGroupType(GroupTypeRequest request)
        {
            BaseResponse groupTypeResponse = new BaseResponse();
            try
            {
                var orderID = repository.UpdateGroupType(request.groupType);
                groupTypeResponse.Success = true;
            }
            catch (Exception ex)
            {
                groupTypeResponse.Success = false;
                groupTypeResponse.Message = ex.Message;
                groupTypeResponse.StackTrace = ex.StackTrace;
            }

            return groupTypeResponse;
        }

        public BaseResponse DeleteGroupType(int groupTypeId)
        {
            BaseResponse groupTypeResponse = new BaseResponse();
            try
            {
                repository.DeleteGroupType(groupTypeId);
                groupTypeResponse.Success = true;
            }
            catch (Exception ex)
            {
                groupTypeResponse.Success = false;
                groupTypeResponse.Message = ex.Message;
                groupTypeResponse.StackTrace = ex.StackTrace;
            }

            return groupTypeResponse;
        }

        public GroupTypeResponse GetGroupType(int groupTypeId)
        {
            GroupTypeResponse groupTypeResponse = new GroupTypeResponse();
            try
            {
                groupTypeResponse.groupType = repository.GetGroupType(groupTypeId);
                groupTypeResponse.Success = true;
            }
            catch (Exception ex)
            {
                groupTypeResponse.Success = false;
                groupTypeResponse.Message = ex.Message;
                groupTypeResponse.StackTrace = ex.StackTrace;
            }
            return groupTypeResponse;
        }

        public GroupTypeResponse GetAllGroupTypes()
        {
            GroupTypeResponse groupTypeResponse = new GroupTypeResponse();
            try
            {
                groupTypeResponse.groupTypeList = repository.GetAllGroupTypes().ToList();
                groupTypeResponse.Success = true;
            }
            catch (Exception ex)
            {
                groupTypeResponse.Success = false;
                groupTypeResponse.Message = ex.Message;
                groupTypeResponse.StackTrace = ex.StackTrace;
            }

            return groupTypeResponse;
        }
    }
}
