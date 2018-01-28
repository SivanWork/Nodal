
USE [master]
GO

/****** Object:  Database [NodalMOS]    Script Date: 28-01-2018 01:02:25 PM ******/
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'NodalMOS')
BEGIN
CREATE DATABASE [NodalMOS]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'NodalMOS', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\NodalMOS.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'NodalMOS_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\NodalMOS_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
END
GO

ALTER DATABASE [NodalMOS] SET COMPATIBILITY_LEVEL = 130
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [NodalMOS].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [NodalMOS] SET ANSI_NULL_DEFAULT OFF
GO

ALTER DATABASE [NodalMOS] SET ANSI_NULLS OFF
GO

ALTER DATABASE [NodalMOS] SET ANSI_PADDING OFF
GO

ALTER DATABASE [NodalMOS] SET ANSI_WARNINGS OFF
GO

ALTER DATABASE [NodalMOS] SET ARITHABORT OFF
GO

ALTER DATABASE [NodalMOS] SET AUTO_CLOSE OFF
GO

ALTER DATABASE [NodalMOS] SET AUTO_SHRINK OFF
GO

ALTER DATABASE [NodalMOS] SET AUTO_UPDATE_STATISTICS ON
GO

ALTER DATABASE [NodalMOS] SET CURSOR_CLOSE_ON_COMMIT OFF
GO

ALTER DATABASE [NodalMOS] SET CURSOR_DEFAULT  GLOBAL
GO

ALTER DATABASE [NodalMOS] SET CONCAT_NULL_YIELDS_NULL OFF
GO

ALTER DATABASE [NodalMOS] SET NUMERIC_ROUNDABORT OFF
GO

ALTER DATABASE [NodalMOS] SET QUOTED_IDENTIFIER OFF
GO

ALTER DATABASE [NodalMOS] SET RECURSIVE_TRIGGERS OFF
GO

ALTER DATABASE [NodalMOS] SET  DISABLE_BROKER
GO

ALTER DATABASE [NodalMOS] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO

ALTER DATABASE [NodalMOS] SET DATE_CORRELATION_OPTIMIZATION OFF
GO

ALTER DATABASE [NodalMOS] SET TRUSTWORTHY OFF
GO

ALTER DATABASE [NodalMOS] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO

ALTER DATABASE [NodalMOS] SET PARAMETERIZATION SIMPLE
GO

ALTER DATABASE [NodalMOS] SET READ_COMMITTED_SNAPSHOT OFF
GO

ALTER DATABASE [NodalMOS] SET HONOR_BROKER_PRIORITY OFF
GO

ALTER DATABASE [NodalMOS] SET RECOVERY FULL
GO

ALTER DATABASE [NodalMOS] SET  MULTI_USER
GO

ALTER DATABASE [NodalMOS] SET PAGE_VERIFY CHECKSUM
GO

ALTER DATABASE [NodalMOS] SET DB_CHAINING OFF
GO

ALTER DATABASE [NodalMOS] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF )
GO

ALTER DATABASE [NodalMOS] SET TARGET_RECOVERY_TIME = 60 SECONDS
GO

ALTER DATABASE [NodalMOS] SET DELAYED_DURABILITY = DISABLED
GO

EXEC sys.sp_db_vardecimal_storage_format N'NodalMOS', N'ON'
GO

ALTER DATABASE [NodalMOS] SET QUERY_STORE = OFF
GO

USE [NodalMOS]
GO

ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO

ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO

ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO

ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO

ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO

ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO

ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO

ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO

USE [NodalMOS]
GO

/****** Object:  Table [dbo].[Bills]    Script Date: 28-01-2018 01:02:26 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Bills]') AND type in (N'U'))
BEGIN
--The following statement was imported into the database project as a schema object and named dbo.Bills.
--CREATE TABLE [dbo].[Bills](
--	[BillId] [int] IDENTITY(1,1) NOT NULL,
--	[OrderId] [int] NULL,
--	[BillTotal] [float] NULL,
--	[PaidAmount] [float] NULL,
--	[PaymentStatusGroup] [int] NULL,
--	[PaymentStatusElementCode] [int] NULL,
--	[Paythru] [varchar](50) NULL,
--	[CreatedById] [int] NULL,
--	[CreatedDate] [datetime] NULL,
--	[LastUpdatedById] [int] NULL,
--	[LastUpdatedDate] [datetime] NULL,
-- CONSTRAINT [PK_Bills] PRIMARY KEY CLUSTERED 
--(
--	[BillId] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
--) ON [PRIMARY]

END
GO

/****** Object:  Table [dbo].[Customer]    Script Date: 28-01-2018 01:02:26 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Customer]') AND type in (N'U'))
BEGIN
--The following statement was imported into the database project as a schema object and named dbo.Customer.
--CREATE TABLE [dbo].[Customer](
--	[CustomerId] [int] IDENTITY(1,1) NOT NULL,
--	[FirstName] [varchar](50) NULL,
--	[MiddleName] [varchar](50) NULL,
--	[LastName] [varchar](50) NULL,
--	[CustomerCode] [varchar](30) NULL,
--	[AmountLimit] [float] NULL,
--	[Mobile] [varchar](12) NULL,
--	[Email] [varchar](30) NULL,
--	[Address1] [nvarchar](200) NULL,
--	[Address2] [nvarchar](200) NULL,
--	[City] [varchar](20) NULL,
--	[State] [varchar](20) NULL,
--	[Country] [varchar](20) NULL,
--	[Pin] [varchar](10) NULL,
--	[IsActive] [bit] NULL,
--	[CreatedById] [int] NULL,
--	[CreatedDate] [datetime] NULL,
--	[LastUpdatedById] [int] NULL,
--	[LastUpdatedDate] [datetime] NULL,
-- CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
--(
--	[CustomerId] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
--) ON [PRIMARY]

END
GO

/****** Object:  Table [dbo].[Discount]    Script Date: 28-01-2018 01:02:26 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Discount]') AND type in (N'U'))
BEGIN
--The following statement was imported into the database project as a schema object and named dbo.Discount.
--CREATE TABLE [dbo].[Discount](
--	[Id] [int] IDENTITY(1,1) NOT NULL,
--	[ProductId] [int] NULL,
--	[DiscountName] [varchar](50) NULL,
--	[DiscountType] [varchar](30) NULL,
--	[Discount] [float] NULL,
--	[IsActive] [bit] NULL,
--	[CreatedById] [int] NULL,
--	[CreatedDate] [datetime] NULL,
--	[LastUpdatedById] [int] NULL,
--	[LastUpdatedDate] [datetime] NULL,
-- CONSTRAINT [PK_Discount] PRIMARY KEY CLUSTERED 
--(
--	[Id] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
--) ON [PRIMARY]

END
GO

/****** Object:  Table [dbo].[GroupElementCode]    Script Date: 28-01-2018 01:02:26 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[GroupElementCode]') AND type in (N'U'))
BEGIN
--The following statement was imported into the database project as a schema object and named dbo.GroupElementCode.
--CREATE TABLE [dbo].[GroupElementCode](
--	[Id] [int] IDENTITY(1,1) NOT NULL,
--	[ElementCode] [varchar](10) NULL,
--	[ElementName] [varchar](50) NULL,
--	[GroupType] [int] NULL,
--	[IsActive] [bit] NULL,
--	[CreatedById] [int] NULL,
--	[CreatedDate] [datetime] NULL,
--	[LastUpdatedById] [int] NULL,
--	[LastUpdatedDate] [datetime] NULL,
-- CONSTRAINT [PK_GroupElementCode] PRIMARY KEY CLUSTERED 
--(
--	[Id] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
--) ON [PRIMARY]

END
GO

/****** Object:  Table [dbo].[GroupType]    Script Date: 28-01-2018 01:02:26 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[GroupType]') AND type in (N'U'))
BEGIN
--The following statement was imported into the database project as a schema object and named dbo.GroupType.
--CREATE TABLE [dbo].[GroupType](
--	[Id] [int] IDENTITY(1,1) NOT NULL,
--	[GroupCode] [varchar](10) NULL,
--	[GroupName] [varchar](50) NULL,
--	[IsActive] [bit] NULL,
--	[CreatedById] [int] NULL,
--	[CreatedDate] [datetime] NULL,
--	[LastUpdatedById] [int] NULL,
--	[LastUpdatedDate] [datetime] NULL,
-- CONSTRAINT [PK_GroupType] PRIMARY KEY CLUSTERED 
--(
--	[Id] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
--) ON [PRIMARY]

END
GO

/****** Object:  Table [dbo].[OrderDetails]    Script Date: 28-01-2018 01:02:26 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[OrderDetails]') AND type in (N'U'))
BEGIN
--The following statement was imported into the database project as a schema object and named dbo.OrderDetails.
--CREATE TABLE [dbo].[OrderDetails](
--	[Id] [int] IDENTITY(1,1) NOT NULL,
--	[OrderId] [int] NULL,
--	[ProductId] [int] NULL,
--	[Quantity] [int] NULL,
--	[CGST] [float] NULL,
--	[SGST] [float] NULL,
--	[IGST] [float] NULL,
--	[Discount] [float] NULL,
--	[NetPrice] [float] NULL,
--	[CreatedById] [int] NULL,
--	[CreatedDate] [datetime] NULL,
--	[LastUpdatedById] [int] NULL,
--	[LastUpdatedDate] [datetime] NULL,
-- CONSTRAINT [PK_OrderDetails] PRIMARY KEY CLUSTERED 
--(
--	[Id] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
--) ON [PRIMARY]

END
GO

/****** Object:  Table [dbo].[OrderReturnDetails]    Script Date: 28-01-2018 01:02:26 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[OrderReturnDetails]') AND type in (N'U'))
BEGIN
--The following statement was imported into the database project as a schema object and named dbo.OrderReturnDetails.
--CREATE TABLE [dbo].[OrderReturnDetails](
--	[Id] [int] IDENTITY(1,1) NOT NULL,
--	[OrderId] [int] NULL,
--	[ProductId] [int] NULL,
--	[Quantity] [int] NULL,
--	[CGST] [float] NULL,
--	[SGST] [float] NULL,
--	[IGST] [float] NULL,
--	[Discount] [float] NULL,
--	[NetPrice] [float] NULL,
--	[OrderReturnGroup] [int] NULL,
--	[OrderReturnElementCode] [int] NULL,
--	[Comments] [nvarchar](500) NULL,
--	[CreatedById] [int] NULL,
--	[CreatedDate] [datetime] NULL,
--	[LastUpdatedById] [int] NULL,
--	[LastUpdatedDate] [datetime] NULL,
-- CONSTRAINT [PK_OrderReturnDetails] PRIMARY KEY CLUSTERED 
--(
--	[Id] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
--) ON [PRIMARY]

END
GO

/****** Object:  Table [dbo].[Orders]    Script Date: 28-01-2018 01:02:26 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Orders]') AND type in (N'U'))
BEGIN
--The following statement was imported into the database project as a schema object and named dbo.Orders.
--CREATE TABLE [dbo].[Orders](
--	[OrderId] [int] IDENTITY(1,1) NOT NULL,
--	[CustomerId] [int] NULL,
--	[TotalOrderAmount] [float] NULL,
--	[OrderStatusGroup] [int] NULL,
--	[OrderStatusElementCode] [int] NULL,
--	[CreatedById] [int] NULL,
--	[CreatedDate] [datetime] NULL,
--	[LastUpdatedById] [int] NULL,
--	[LastUpdatedDate] [datetime] NULL,
-- CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
--(
--	[OrderId] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
--) ON [PRIMARY]

END
GO

/****** Object:  Table [dbo].[Products]    Script Date: 28-01-2018 01:02:26 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Products]') AND type in (N'U'))
BEGIN
--The following statement was imported into the database project as a schema object and named dbo.Products.
--CREATE TABLE [dbo].[Products](
--	[ProductId] [int] IDENTITY(1,1) NOT NULL,
--	[ProductName] [varchar](100) NULL,
--	[MRP] [float] NULL,
--	[DealerPrice] [float] NULL,
--	[WholesalePrice] [float] NULL,
--	[CGST] [float] NULL,
--	[SGST] [float] NULL,
--	[IGST] [float] NULL,
--	[IsActive] [bit] NULL,
--	[CreatedById] [int] NULL,
--	[CreatedDate] [datetime] NULL,
--	[LastUpdatedById] [int] NULL,
--	[LastUpdatedDate] [datetime] NULL,
-- CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
--(
--	[ProductId] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
--) ON [PRIMARY]

END
GO

/****** Object:  Table [dbo].[SchemeProduct]    Script Date: 28-01-2018 01:02:26 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SchemeProduct]') AND type in (N'U'))
BEGIN
--The following statement was imported into the database project as a schema object and named dbo.SchemeProduct.
--CREATE TABLE [dbo].[SchemeProduct](
--	[Id] [int] IDENTITY(1,1) NOT NULL,
--	[SchemeId] [int] NULL,
--	[ProductId] [int] NULL,
--	[IsActive] [bit] NULL,
--	[CreatedById] [int] NULL,
--	[CreatedDate] [datetime] NULL,
--	[LastUpdatedById] [int] NULL,
--	[LastUpdatedDate] [datetime] NULL,
-- CONSTRAINT [PK_SchemeProduct] PRIMARY KEY CLUSTERED 
--(
--	[Id] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
--) ON [PRIMARY]

END
GO

/****** Object:  Table [dbo].[Schemes]    Script Date: 28-01-2018 01:02:26 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Schemes]') AND type in (N'U'))
BEGIN
--The following statement was imported into the database project as a schema object and named dbo.Schemes.
--CREATE TABLE [dbo].[Schemes](
--	[SchemeId] [int] IDENTITY(1,1) NOT NULL,
--	[SchemeName] [varchar](100) NULL,
--	[SchemeCode] [varchar](30) NULL,
--	[IsActive] [bit] NULL,
--	[CreatedById] [int] NULL,
--	[CreatedDate] [datetime] NULL,
--	[LastUpdatedById] [int] NULL,
--	[LastUpdatedDate] [datetime] NULL,
-- CONSTRAINT [PK_Schemes] PRIMARY KEY CLUSTERED 
--(
--	[SchemeId] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
--) ON [PRIMARY]

END
GO

/****** Object:  Table [dbo].[Users]    Script Date: 28-01-2018 01:02:26 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Users]') AND type in (N'U'))
BEGIN
--The following statement was imported into the database project as a schema object and named dbo.Users.
--CREATE TABLE [dbo].[Users](
--	[UserId] [int] IDENTITY(1,1) NOT NULL,
--	[Username] [varchar](50) NULL,
--	[Password] [nvarchar](100) NULL,
--	[FirstName] [varchar](50) NULL,
--	[MiddleName] [varchar](50) NULL,
--	[LastName] [varchar](50) NULL,
--	[Mobile] [varchar](12) NULL,
--	[Email] [varchar](30) NULL,
--	[Address1] [nvarchar](200) NULL,
--	[Address2] [nvarchar](200) NULL,
--	[City] [varchar](20) NULL,
--	[State] [varchar](20) NULL,
--	[Country] [varchar](20) NULL,
--	[Pin] [varchar](10) NULL,
--	[UserGroupType] [int] NULL,
--	[UserElementCode] [int] NULL,
--	[ActiveFrom] [datetime] NULL,
--	[ActiveTo] [datetime] NULL,
--	[IsActive] [bit] NULL,
--	[CreatedById] [int] NULL,
--	[CreatedDate] [datetime] NULL,
--	[LastUpdatedById] [int] NULL,
--	[LastUpdatedDate] [datetime] NULL,
-- CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
--(
--	[UserId] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
--) ON [PRIMARY]

END
GO

USE [master]
GO

ALTER DATABASE [NodalMOS] SET  READ_WRITE
GO
