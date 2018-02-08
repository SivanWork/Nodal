using Dapper;
using Nodal.Common.Helper;
using Nodal.Common.Schema;
using System.Collections.Generic;
using System.Linq;

namespace Nodal.DataAccess
{
    public class CustomerRepository
    {
        private DBHelper db;
        public CustomerRepository()
        {
            db = DBHelper.GetInstance();
        }

        public int InsertCustomer(Customer customer)
        {
            string sql = @"INSERT INTO [Customer]
           ([FirstName],[MiddleName],[LastName],[CustomerCode],[AmountLimit],[Mobile],[Email],[Address1],[Address2],[City],[State],[Country],[Pin],[IsActive],[CreatedById],[CreatedDate],[LastUpdatedById],[LastUpdatedDate])
			VALUES (@FirstName, @MiddleName, @LastName, @CustomerCode, @AmountLimit, @Mobile, @Email, @Address1, @Address2, @City, @State, @Country, @Pin, @IsActive, @CreatedById, @CreatedDate, @LastUpdatedById, @LastUpdatedDate); 
            SELECT CAST(SCOPE_IDENTITY() as int)";

            var id = db.Connection.Query<int>(sql, customer).Single();
            return id;
        }

        public int UpdateCustomer(Customer customer)
        {
            string sql = @"UPDATE [Customer] SET [FirstName] = @FirstName, [MiddleName] = @MiddleName, [LastName] = @LastName, 
      [CustomerCode] = @CustomerCode, [AmountLimit] = @AmountLimit, [Mobile] = @Mobile, 
      [Email] = @Email, [Address1] = @Address1, [Address2] = @Address2, [City] = @City, 
      [State] = @State, [Country] = @Country, [Pin] = @Pin, [IsActive] = @IsActive, [LastUpdatedById] = @LastUpdatedById, [LastUpdatedDate] = @LastUpdatedDate 
	  WHERE [CustomerId] = @CustomerId";

            var id = db.Connection.Query<int>(sql, customer).Single();
            return id;
        }

        public int DeleteCustomer(int customerId)
        {
            var result = db.Connection.Execute("DELETE FROM [Customer] WHERE [CustomerId] = @CustomerId", new { customerId });
            return result;
        }

        public Customer GetCustomer(int customerId)
        {
            string query = "SELECT * FROM [Customer] WHERE [CustomerId] = @CustomerId";
            var result = db.Connection.Query(query, new { customerId }).FirstOrDefault();
            return result;
        }

        public IEnumerable<Customer> GetAllCustomers()
        {
            var result = db.Connection.Query<Customer>("SELECT * FROM [Customer]");
            return result;
        }
    }
}
