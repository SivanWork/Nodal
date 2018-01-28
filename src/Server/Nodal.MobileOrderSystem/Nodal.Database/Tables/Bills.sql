CREATE TABLE [dbo].[Bills](
	[BillId] [int] IDENTITY(1,1) NOT NULL,
	[OrderId] [int] NULL,
	[BillTotal] [float] NULL,
	[PaidAmount] [float] NULL,
	[PaymentStatusGroup] [int] NULL,
	[PaymentStatusElementCode] [int] NULL,
	[Paythru] [varchar](50) NULL,
	[CreatedById] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[LastUpdatedById] [int] NULL,
	[LastUpdatedDate] [datetime] NULL,
 CONSTRAINT [PK_Bills] PRIMARY KEY CLUSTERED 
(
	[BillId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]