using Dapper;
using Nodal.Common.Helper;
using Nodal.Common.Schema;
using System.Collections.Generic;
using System.Linq;

namespace Nodal.DataAccess
{
    public class GroupElementCodeRepository
    {
        private DBHelper db;
        public GroupElementCodeRepository()
        {
            db = DBHelper.GetInstance();
        }

        public int InsertGroupElementCode(GroupElementCode groupElementCode)
        {
            string sql = @"INSERT INTO [GroupElementCode] ([ElementCode], [ElementName], [GroupType], [IsActive], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate])
VALUES (@ElementCode, @ElementName, @GroupType, @IsActive, @CreatedById, @CreatedDate, @LastUpdatedById, @LastUpdatedDate); SELECT CAST(SCOPE_IDENTITY() as int)";

            var id = db.Connection.Query<int>(sql, groupElementCode).FirstOrDefault();
            return id;
        }

        public int UpdateGroupElementCode(GroupElementCode groupElementCode)
        {
            string sql = @"UPDATE [GroupElementCode] SET [ElementCode] = @ElementCode, [ElementName] = @ElementName, [GroupType] = @GroupType, 
[IsActive] = @IsActive, [LastUpdatedById] = @LastUpdatedById, [LastUpdatedDate] = @LastUpdatedDate WHERE [Id] = @Id";

            var id = db.Connection.Query<int>(sql, groupElementCode).FirstOrDefault();
            return id;
        }

        public int DeleteGroupElementCode(int groupElementCodeId)
        {
            var result = db.Connection.Execute("DELETE FROM [GroupElementCode] WHERE [Id] = @Id", new { groupElementCodeId });
            return result;
        }

        public GroupElementCode GetGroupElementCode(int groupElementCodeId)
        {
            string query = "SELECT * FROM [dbo].[GroupElementCode] WHERE [Id] = @Id";
            var result = db.Connection.Query<GroupElementCode>(query, new { groupElementCodeId }).FirstOrDefault();
            return result;
        }

        public IEnumerable<GroupElementCode> GetAllGroupElementCodes()
        {
            var result = db.Connection.Query<GroupElementCode>("SELECT * FROM [dbo].[GroupElementCode]");
            return result;
        }
    }
}
