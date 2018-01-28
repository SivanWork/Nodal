CREATE TABLE [dbo].[OrderReturnDetails](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[OrderId] [int] NULL,
	[ProductId] [int] NULL,
	[Quantity] [int] NULL,
	[CGST] [float] NULL,
	[SGST] [float] NULL,
	[IGST] [float] NULL,
	[Discount] [float] NULL,
	[NetPrice] [float] NULL,
	[OrderReturnGroup] [int] NULL,
	[OrderReturnElementCode] [int] NULL,
	[Comments] [nvarchar](500) NULL,
	[CreatedById] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[LastUpdatedById] [int] NULL,
	[LastUpdatedDate] [datetime] NULL,
 CONSTRAINT [PK_OrderReturnDetails] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]