
DROP TABLE "Library"
go

DELETE "VSVBTableVersions" WHERE RepositoryTableName =  'Library'
go

CREATE TABLE "Library"(
"PKLibrary"   INT NOT NULL  IDENTITY( 1,1), 
"amountOfMembers"   SMALLINT NULL , 
"amountOfCopies"   SMALLINT NULL , 
"Name"   VARCHAR(50) NOT NULL , 
"City"   VARCHAR(50) NOT NULL , 
"PhoneNumber"   INT NULL , 
"WebAddress"   VARCHAR(50) NULL , 
"LibraryState"   SMALLINT NOT NULL  )
go

ALTER TABLE "Library" ADD CONSTRAINT "PKEY1_LibraryPKLibrary_" PRIMARY KEY( 
"PKLibrary")
go

GRANT ALL ON "Library" TO PUBLIC
go

