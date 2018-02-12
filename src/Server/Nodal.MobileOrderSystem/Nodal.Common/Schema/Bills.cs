using System;

namespace Nodal.Common.Schema
{
    public class Bills
    {
        public int BillId { get; set; }
        public int OrderId { get; set; }
        public float BillTotal { get; set; }
        public float PaidAmount { get; set; }
        public string PaymentStatusGroup { get; set; }
        public string PaymentStatusElementCode { get; set; }
        public string Paythru { get; set; }
        public int CreatedById { get; set; }
        public DateTime CreatedDate { get; set; }
        public int LastUpdatedById { get; set; }
        public DateTime LastUpdatedDate { get; set; } 
    }
}
