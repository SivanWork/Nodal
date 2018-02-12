CREATE TABLE [dbo].[Users](
	[UserId] [int] IDENTITY(1,1) NOT NULL,
	[Username] [varchar](50) NULL,
	[Password] [nvarchar](100) NULL,
	[FirstName] [varchar](50) NULL,
	[MiddleName] [varchar](50) NULL,
	[LastName] [varchar](50) NULL,
	[Mobile] [varchar](12) NULL,
	[Email] [varchar](30) NULL,
	[Address1] [nvarchar](200) NULL,
	[Address2] [nvarchar](200) NULL,
	[City] [varchar](20) NULL,
	[State] [varchar](20) NULL,
	[Country] [varchar](20) NULL,
	[Pin] [varchar](10) NULL,
	[UserGroupType] [int] NULL,
	[UserElementCode] [int] NULL,
	[ActiveFrom] [datetime] NULL,
	[ActiveTo] [datetime] NULL,
	[IsActive] [bit] NULL,
	[CreatedById] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[LastUpdatedById] [int] NULL,
	[LastUpdatedDate] [datetime] NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GroupElementCode]    Script Date: 01-02-2018 11:05:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GroupElementCode](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[ElementCode] [varchar](10) NULL,
	[ElementName] [varchar](50) NULL,
	[GroupType] [int] NULL,
	[IsActive] [bit] NULL,
	[CreatedById] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[LastUpdatedById] [int] NULL,
	[LastUpdatedDate] [datetime] NULL,
 CONSTRAINT [PK_GroupElementCode] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[UserProfileView]    Script Date: 01-02-2018 11:05:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[UserProfileView]
AS
select U.UserId,U.FirstName,U.MiddleName,U.LastName,U.Mobile,U.Email,GEC.ElementCode AS UserTypeCode,GEC.ElementName AS UserTypeName,GEC.IsActive AS UserTypeActive, U.Address1,U.Address2,U.City,U.State,U.Country,U.Pin,U.ActiveFrom,U.ActiveTo,U.IsActive 
from Users U INNER JOIN GroupElementCode GEC ON U.UserElementCode = GEC.Id
GO
/****** Object:  Table [dbo].[Bills]    Script Date: 01-02-2018 11:05:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 01-02-2018 11:05:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[CustomerId] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [varchar](50) NULL,
	[MiddleName] [varchar](50) NULL,
	[LastName] [varchar](50) NULL,
	[CustomerCode] [varchar](30) NULL,
	[AmountLimit] [float] NULL,
	[Mobile] [varchar](12) NULL,
	[Email] [varchar](30) NULL,
	[Address1] [nvarchar](200) NULL,
	[Address2] [nvarchar](200) NULL,
	[City] [varchar](20) NULL,
	[State] [varchar](20) NULL,
	[Country] [varchar](20) NULL,
	[Pin] [varchar](10) NULL,
	[IsActive] [bit] NULL,
	[CreatedById] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[LastUpdatedById] [int] NULL,
	[LastUpdatedDate] [datetime] NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[CustomerId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 01-02-2018 11:05:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discount](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[ProductId] [int] NULL,
	[DiscountName] [varchar](50) NULL,
	[DiscountType] [varchar](30) NULL,
	[Discount] [float] NULL,
	[IsActive] [bit] NULL,
	[CreatedById] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[LastUpdatedById] [int] NULL,
	[LastUpdatedDate] [datetime] NULL,
 CONSTRAINT [PK_Discount] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GroupType]    Script Date: 01-02-2018 11:05:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GroupType](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[GroupCode] [varchar](10) NULL,
	[GroupName] [varchar](50) NULL,
	[IsActive] [bit] NULL,
	[CreatedById] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[LastUpdatedById] [int] NULL,
	[LastUpdatedDate] [datetime] NULL,
 CONSTRAINT [PK_GroupType] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 01-02-2018 11:05:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[OrderId] [int] NULL,
	[ProductId] [int] NULL,
	[Quantity] [int] NULL,
	[CGST] [float] NULL,
	[SGST] [float] NULL,
	[IGST] [float] NULL,
	[Discount] [float] NULL,
	[NetPrice] [float] NULL,
	[CreatedById] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[LastUpdatedById] [int] NULL,
	[LastUpdatedDate] [datetime] NULL,
 CONSTRAINT [PK_OrderDetails] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderReturnDetails]    Script Date: 01-02-2018 11:05:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 01-02-2018 11:05:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderId] [int] IDENTITY(1,1) NOT NULL,
	[CustomerId] [int] NULL,
	[TotalOrderAmount] [float] NULL,
	[OrderStatusGroup] [int] NULL,
	[OrderStatusElementCode] [int] NULL,
	[CreatedById] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[LastUpdatedById] [int] NULL,
	[LastUpdatedDate] [datetime] NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[OrderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 01-02-2018 11:05:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[ProductId] [int] IDENTITY(1,1) NOT NULL,
	[ProductName] [varchar](100) NULL,
	[MRP] [float] NULL,
	[DealerPrice] [float] NULL,
	[WholesalePrice] [float] NULL,
	[CGST] [float] NULL,
	[SGST] [float] NULL,
	[IGST] [float] NULL,
	[IsActive] [bit] NULL,
	[CreatedById] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[LastUpdatedById] [int] NULL,
	[LastUpdatedDate] [datetime] NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[ProductId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SchemeProduct]    Script Date: 01-02-2018 11:05:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SchemeProduct](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[SchemeId] [int] NULL,
	[ProductId] [int] NULL,
	[IsActive] [bit] NULL,
	[CreatedById] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[LastUpdatedById] [int] NULL,
	[LastUpdatedDate] [datetime] NULL,
 CONSTRAINT [PK_SchemeProduct] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Schemes]    Script Date: 01-02-2018 11:05:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Schemes](
	[SchemeId] [int] IDENTITY(1,1) NOT NULL,
	[SchemeName] [varchar](100) NULL,
	[SchemeCode] [varchar](30) NULL,
	[IsActive] [bit] NULL,
	[CreatedById] [int] NULL,
	[CreatedDate] [datetime] NULL,
	[LastUpdatedById] [int] NULL,
	[LastUpdatedDate] [datetime] NULL,
 CONSTRAINT [PK_Schemes] PRIMARY KEY CLUSTERED 
(
	[SchemeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tokens]    Script Date: 01-02-2018 11:05:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tokens](
	[UserId] [int] NOT NULL,
	[AuthToken] [uniqueidentifier] NOT NULL,
	[IssuedOn] [datetime] NULL
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[GroupElementCode] ON 

INSERT [dbo].[GroupElementCode] ([Id], [ElementCode], [ElementName], [GroupType], [IsActive], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate]) VALUES (1, N'Admin', N'Administrator', 1, 1, NULL, CAST(N'2018-01-29T19:05:16.740' AS DateTime), NULL, NULL)
INSERT [dbo].[GroupElementCode] ([Id], [ElementCode], [ElementName], [GroupType], [IsActive], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate]) VALUES (2, N'Agent', N'Agent', 1, 1, NULL, CAST(N'2018-01-29T19:05:26.537' AS DateTime), NULL, NULL)
SET IDENTITY_INSERT [dbo].[GroupElementCode] OFF
SET IDENTITY_INSERT [dbo].[GroupType] ON 

INSERT [dbo].[GroupType] ([Id], [GroupCode], [GroupName], [IsActive], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate]) VALUES (1, N'UserType', N'UserType', 1, NULL, CAST(N'2018-01-29T19:01:50.083' AS DateTime), NULL, NULL)
SET IDENTITY_INSERT [dbo].[GroupType] OFF
INSERT [dbo].[Tokens] ([UserId], [AuthToken], [IssuedOn]) VALUES (1, N'6915b256-57f8-4eba-93f3-23939e292e0b', CAST(N'2018-01-29T19:11:17.617' AS DateTime))
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([UserId], [Username], [Password], [FirstName], [MiddleName], [LastName], [Mobile], [Email], [Address1], [Address2], [City], [State], [Country], [Pin], [UserGroupType], [UserElementCode], [ActiveFrom], [ActiveTo], [IsActive], [CreatedById], [CreatedDate], [LastUpdatedById], [LastUpdatedDate]) VALUES (1, N'admin', N'admin@123', N'Nodal', NULL, N'Admin', N'9123945678', N'admin@nodal.com', N'Bellandhur', N'Bangalore', N'Bangalore', N'KA', N'IN', N'560078', 1, 1, CAST(N'2018-01-29T19:10:00.187' AS DateTime), CAST(N'2019-01-29T19:10:00.187' AS DateTime), 1, NULL, CAST(N'2018-01-29T19:10:00.187' AS DateTime), NULL, NULL)
SET IDENTITY_INSERT [dbo].[Users] OFF
ALTER DATABASE [NodalMOS] SET  READ_WRITE 
GO
