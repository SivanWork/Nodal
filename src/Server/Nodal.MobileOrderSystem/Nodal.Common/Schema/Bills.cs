namespace Nodal.Common.Schema
{
    public class Bills
    {
        public int BillId { get; set; }
        public int OrderId { get; set; }
        public float BillTotal { get; set; }
        public float PaidAmount { get; set; }
        public int PaymentStatusGroup { get; set; }
        public int PaymentStatusElementCode { get; set; }
        public string Paythru { get; set; }
    }
}
