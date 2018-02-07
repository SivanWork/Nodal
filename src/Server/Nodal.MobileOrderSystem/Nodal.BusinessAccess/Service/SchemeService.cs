using Nodal.BusinessAccess.Model;
using Nodal.DataAccess;
using System;
using System.Linq;

namespace Nodal.BusinessAccess.Service
{
    public class SchemeService
    {
        private SchemesRepository repository;

        public SchemeService()
        {
            repository = new SchemesRepository();
        }

        public BaseResponse InsertScheme(SchemesRequest request)
        {
            BaseResponse schemeResponse = new BaseResponse();
            try
            {
                var orderID = repository.InsertScheme(request.scheme);
                schemeResponse.Success = true;
            }
            catch (Exception ex)
            {
                schemeResponse.Success = false;
                schemeResponse.Message = ex.Message;
                schemeResponse.StackTrace = ex.StackTrace;
            }

            return schemeResponse;
        }

        public BaseResponse UpdateScheme(SchemesRequest request)
        {
            BaseResponse schemeResponse = new BaseResponse();
            try
            {
                var orderID = repository.UpdateScheme(request.scheme);
                schemeResponse.Success = true;
            }
            catch (Exception ex)
            {
                schemeResponse.Success = false;
                schemeResponse.Message = ex.Message;
                schemeResponse.StackTrace = ex.StackTrace;
            }

            return schemeResponse;
        }

        public BaseResponse DeleteScheme(int schemeId)
        {
            BaseResponse schemeResponse = new BaseResponse();
            try
            {
                repository.DeleteScheme(schemeId);
                schemeResponse.Success = true;
            }
            catch (Exception ex)
            {
                schemeResponse.Success = false;
                schemeResponse.Message = ex.Message;
                schemeResponse.StackTrace = ex.StackTrace;
            }

            return schemeResponse;
        }

        public SchemesResponse GetScheme(int schemeId)
        {
            SchemesResponse schemeResponse = new SchemesResponse();
            try
            {
                schemeResponse.scheme = repository.GetScheme(schemeId);
                schemeResponse.Success = true;
            }
            catch (Exception ex)
            {
                schemeResponse.Success = false;
                schemeResponse.Message = ex.Message;
                schemeResponse.StackTrace = ex.StackTrace;
            }
            return schemeResponse;
        }

        public SchemesResponse GetAllSchemes()
        {
            SchemesResponse schemeResponse = new SchemesResponse();
            try
            {
                schemeResponse.schemeList = repository.GetSchemes().ToList();
                schemeResponse.Success = true;
            }
            catch (Exception ex)
            {
                schemeResponse.Success = false;
                schemeResponse.Message = ex.Message;
                schemeResponse.StackTrace = ex.StackTrace;
            }

            return schemeResponse;
        }
    }
}
