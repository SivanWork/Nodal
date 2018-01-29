CREATE TABLE [dbo].[Tokens](
	[UserId] [int] NOT NULL,
	[AuthToken] [uniqueidentifier] NOT NULL,
	[IssuedOn] [datetime] NULL
) ON [PRIMARY]