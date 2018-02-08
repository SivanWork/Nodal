using Dapper;
using Nodal.Common.Helper;
using Nodal.Common.Schema;
using System.Collections.Generic;
using System.Linq;

namespace Nodal.DataAccess
{
    public class BillsRepository
    {
        private DBHelper db;

        public BillsRepository()
        {
            db = DBHelper.GetInstance();
        }

        public int InsertBill(Bills bill)
        {
            string sql = @"INSERT INTO [Bills] ([OrderId],[BillTotal],[PaidAmount],[PaymentStatusGroup],[PaymentStatusElementCode],[Paythru],[CreatedById],[CreatedDate],[LastUpdatedById],[LastUpdatedDate])
VALUES (@OrderId, @BillTotal, @PaidAmount, @PaymentStatusGroup, @PaymentStatusElementCode, @Paythru, @CreatedById, @CreatedDate, @LastUpdatedById, @LastUpdatedDate) ; SELECT CAST(SCOPE_IDENTITY() as int)";

            var id = db.Connection.Query<int>(sql, bill).Single();
            return id;
        }

        public int UpdateBill(Bills bill)
        {
            string sql = @"UPDATE [Bills] SET [OrderId] = @OrderId, [BillTotal] = @BillTotal, [PaidAmount] = @PaidAmount, 
[PaymentStatusGroup] = @PaymentStatusGroup, [PaymentStatusElementCode] = @PaymentStatusElementCode, [Paythru] = @Paythru, [LastUpdatedById] = @LastUpdatedById, [LastUpdatedDate] = @LastUpdatedDate  
 WHERE [BillId] = @BillId";

            var id = db.Connection.Query<int>(sql, bill).Single();
            return id;
        }

        public int DeleteBill(int billId)
        {
            var result = db.Connection.Execute("DELETE FROM [Bills] WHERE [BillId] = @BillId", new { billId });
            return result;
        }

        public Bills GetBill(int billId)
        {
            string query = "SELECT * FROM [Bills] WHERE [BillId] = @BillId";
            var result = db.Connection.Query(query, new { billId }).FirstOrDefault();
            return result;
        }

        public IEnumerable<Bills> GetAllBills()
        {
            var result = db.Connection.Query<Bills>("SELECT * FROM [Bills]");
            return result;
        }
    }
}
