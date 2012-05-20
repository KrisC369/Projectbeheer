CREATE TABLE VSVBTableVersions(
RepositoryTableName   VARCHAR(255) NOT NULL , 
TableVersion   VARCHAR(50) NULL , 
LastRulesUpdated   VARCHAR(50) NULL  )
go

ALTER TABLE VSVBTableVersions ADD CONSTRAINT PKPrimaryKey_VSVBTableVersions PRIMARY KEY( 
RepositoryTableName)
go

GRANT ALL ON VSVBTableVersions TO PUBLIC
go

