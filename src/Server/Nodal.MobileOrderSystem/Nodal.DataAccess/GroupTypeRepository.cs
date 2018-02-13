using Dapper;
using Nodal.Common.Helper;
using Nodal.Common.Schema;
using System.Collections.Generic;
using System.Linq;

namespace Nodal.DataAccess
{
    public class GroupTypeRepository
    {
        private DBHelper db;
        public GroupTypeRepository()
        {
            db = DBHelper.GetInstance();
        }

        public int InsertGroupType(GroupType groupType)
        {
            string sql = @"INSERT INTO [GroupType] ([GroupCode], [GroupName],[IsActive], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate])
                    VALUES (@GroupCode, @GroupName, @IsActive, @CreatedById, @CreatedDate, @LastUpdatedById, @LastUpdatedDate); SELECT CAST(SCOPE_IDENTITY() as int)";

            var id = db.Connection.Query<int>(sql, groupType).FirstOrDefault();
            return id;
        }

        public int UpdateGroupType(GroupType groupType)
        {
            string sql = @"UPDATE [GroupType] SET [GroupCode] = @GroupCode, [GroupName] = @GroupName, [IsActive] = @IsActive, [LastUpdatedById] = @LastUpdatedById, [LastUpdatedDate] = @LastUpdatedDate 
                         WHERE [Id] = @Id";

            var id = db.Connection.Query<int>(sql, groupType).FirstOrDefault();
            return id;
        }

        public int DeleteGroupType(int groupTypeId)
        {
            var result = db.Connection.Execute("DELETE FROM [GroupType] WHERE [Id] = @Id", new { groupTypeId });
            return result;
        }

        public GroupType GetGroupType(int groupTypeId)
        {
            string query = "SELECT * FROM [GroupType] WHERE [Id] = @Id";
            var result = db.Connection.Query<GroupType>(query, new { groupTypeId }).FirstOrDefault();
            return result;
        }

        public IEnumerable<GroupType> GetAllGroupTypes()
        {
            var result = db.Connection.Query<GroupType>("SELECT * FROM [GroupType]");
            return result;
        }
    }
}
