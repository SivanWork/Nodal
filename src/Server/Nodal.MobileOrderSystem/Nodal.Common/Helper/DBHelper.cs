using System.Configuration;
using System.Data.SqlClient;

namespace Nodal.Common.Helper
{
    public class DBHelper
    {
        private static DBHelper instance;

        public SqlConnection Connection { get; set; }

        public static DBHelper Instance
        {
            get
            {
                if (instance == null)
                {
                    instance = new DBHelper();
                }
                return instance;
            }
        }

        private DBHelper()
        {
            Connection = new SqlConnection(ConfigurationManager.ConnectionStrings["Connection"].ConnectionString);
        }

        public static DBHelper GetInstance()
        {
            return Instance;
        }

    }

    public struct SQLTables
    {
        public static string Users = "Users";
        public static string Tokens = "Tokens";
        public static string UserProfileView = "UserProfileView";
    }
}
