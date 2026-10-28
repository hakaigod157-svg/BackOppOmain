use ERPO;

CREATE TABLE KHistory(
	KHSEQ numeric identity NOT NULL,
	KHOP char(1) NOT NULL,
	KHTABLE varchar(64) NOT NULL,
	KHCOL varchar(64) NULL,
	KHCODE varchar(255) NULL,
	KHOLD text NULL,
	KHNEW text NULL,
	KHUSER int NOT NULL,
	KHDATE smalldatetime NOT NULL,
	PRIMARY KEY(KHSEQ)
);
CREATE UNIQUE INDEX KHistory_SEQ ON KHistory(KHSEQ);

