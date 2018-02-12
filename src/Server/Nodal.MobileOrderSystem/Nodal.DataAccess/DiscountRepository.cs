using Dapper;
using Nodal.Common.Helper;
using Nodal.Common.Schema;
using System.Collections.Generic;
using System.Linq;

namespace Nodal.DataAccess
{
    public class DiscountRepository
    {
        private DBHelper db;
        public DiscountRepository()
        {
            db = DBHelper.GetInstance();
        }

        public int InsertDiscount(Discounts discount)
        {
            string sql = @"INSERT INTO [Discount] ([ProductId], [DiscountName], [DiscountType], [Discount], [IsActive], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate])
VALUES (@ProductId, @DiscountName, @DiscountType, @Discount, @IsActive, @CreatedById, @CreatedDate, @LastUpdatedById, @LastUpdatedDate) ; SELECT CAST(SCOPE_IDENTITY() as int)";

            var id = db.Connection.Query<int>(sql, discount).Single();
            return id;
        }

        public int UpdateDiscount(Discounts discount)
        {
            string sql = @"UPDATE [Discount] SET [ProductId] = @ProductId, [DiscountName] = @DiscountName,[DiscountType] = @DiscountType,
       [Discount] = @Discount, [IsActive] = @IsActive, [LastUpdatedById] = @LastUpdatedById, [LastUpdatedDate] = @LastUpdatedDate WHERE [Id] = @Id";

            var id = db.Connection.Query<int>(sql, discount).Single();
            return id;
        }

        public int DeleteDiscount(int discountId)
        {
            var result = db.Connection.Execute("DELETE FROM [dbo].[Discount] WHERE [Id] = @Id", new { discountId });
            return result;
        }

        public Discounts GetDiscount(int discountId)
        {
            string query = "SELECT * FROM [Discount] WHERE [Id] = @Id";
            var result = db.Connection.Query<Discounts>(query, new { discountId }).FirstOrDefault();
            return result;
        }

        public IEnumerable<Discounts> GetAllDiscounts()
        {
            var result = db.Connection.Query<Discounts>("SELECT * FROM [Discount]");
            return result;
        }
    }
}
