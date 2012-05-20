ALTER TABLE "CancelEvent" DROP CONSTRAINT "JV__0001CancelEvent"
go

ALTER TABLE "CancelEvent" ADD CONSTRAINT "JV__0001CancelEvent"
FOREIGN KEY ("FKReservation") REFERENCES 
"Reservation" ("PKReservation")
go

ALTER TABLE "EnterEvent" DROP CONSTRAINT "JV__0001EnterEvent"
go

ALTER TABLE "EnterEvent" DROP CONSTRAINT "JV__0002EnterEvent"
go

ALTER TABLE "EnterEvent" ADD CONSTRAINT "JV__0001EnterEvent"
FOREIGN KEY ("FKMember") REFERENCES 
"Member" ("PKMember")
go

ALTER TABLE "EnterEvent" ADD CONSTRAINT "JV__0002EnterEvent"
FOREIGN KEY ("FKLibrary") REFERENCES 
"Library" ("PKLibrary")
go

ALTER TABLE "LeaveEvent" DROP CONSTRAINT "JV__0001LeaveEvent"
go

ALTER TABLE "LeaveEvent" ADD CONSTRAINT "JV__0001LeaveEvent"
FOREIGN KEY ("FKMember") REFERENCES 
"Member" ("PKMember")
go

ALTER TABLE "ReturnEvent" DROP CONSTRAINT "JV__0001ReturnEvent"
go

ALTER TABLE "ReturnEvent" ADD CONSTRAINT "JV__0001ReturnEvent"
FOREIGN KEY ("FKLoan") REFERENCES 
"Loan" ("PKLoan")
go

ALTER TABLE "LoseEvent" DROP CONSTRAINT "JV__0001LoseEvent"
go

ALTER TABLE "LoseEvent" ADD CONSTRAINT "JV__0001LoseEvent"
FOREIGN KEY ("FKLoan") REFERENCES 
"Loan" ("PKLoan")
go

ALTER TABLE "FetchEvent" DROP CONSTRAINT "JV__0001FetchEvent"
go

ALTER TABLE "FetchEvent" DROP CONSTRAINT "JV__0002FetchEvent"
go

ALTER TABLE "FetchEvent" ADD CONSTRAINT "JV__0001FetchEvent"
FOREIGN KEY ("FKReservation") REFERENCES 
"Reservation" ("PKReservation")
go

ALTER TABLE "FetchEvent" ADD CONSTRAINT "JV__0002FetchEvent"
FOREIGN KEY ("FKLoan") REFERENCES 
"Loan" ("PKLoan")
go

ALTER TABLE "RenewEvent" DROP CONSTRAINT "JV__0001RenewEvent"
go

ALTER TABLE "RenewEvent" ADD CONSTRAINT "JV__0001RenewEvent"
FOREIGN KEY ("FKLoan") REFERENCES 
"Loan" ("PKLoan")
go

ALTER TABLE "AcquireEvent" DROP CONSTRAINT "JV__0001AcquireEvent"
go

ALTER TABLE "AcquireEvent" DROP CONSTRAINT "JV__0002AcquireEvent"
go

ALTER TABLE "AcquireEvent" ADD CONSTRAINT "JV__0001AcquireEvent"
FOREIGN KEY ("FKCopy") REFERENCES 
"Copy" ("PKCopy")
go

ALTER TABLE "AcquireEvent" ADD CONSTRAINT "JV__0002AcquireEvent"
FOREIGN KEY ("FKLibrary") REFERENCES 
"Library" ("PKLibrary")
go

ALTER TABLE "SellEvent" DROP CONSTRAINT "JV__0001SellEvent"
go

ALTER TABLE "SellEvent" ADD CONSTRAINT "JV__0001SellEvent"
FOREIGN KEY ("FKCopy") REFERENCES 
"Copy" ("PKCopy")
go

ALTER TABLE "ReserveEvent" DROP CONSTRAINT "JV__0001ReserveEvent"
go

ALTER TABLE "ReserveEvent" DROP CONSTRAINT "JV__0002ReserveEvent"
go

ALTER TABLE "ReserveEvent" DROP CONSTRAINT "JV__0003ReserveEvent"
go

ALTER TABLE "ReserveEvent" ADD CONSTRAINT "JV__0001ReserveEvent"
FOREIGN KEY ("FKReservation") REFERENCES 
"Reservation" ("PKReservation")
go

ALTER TABLE "ReserveEvent" ADD CONSTRAINT "JV__0002ReserveEvent"
FOREIGN KEY ("FKCopy") REFERENCES 
"Copy" ("PKCopy")
go

ALTER TABLE "ReserveEvent" ADD CONSTRAINT "JV__0003ReserveEvent"
FOREIGN KEY ("FKMember") REFERENCES 
"Member" ("PKMember")
go

ALTER TABLE "Reservation" DROP CONSTRAINT "JV__0001Reservation"
go

ALTER TABLE "Reservation" DROP CONSTRAINT "JV__0002Reservation"
go

ALTER TABLE "Reservation" ADD CONSTRAINT "JV__0001Reservation"
FOREIGN KEY ("FKCopy") REFERENCES 
"Copy" ("PKCopy")
go

ALTER TABLE "Reservation" ADD CONSTRAINT "JV__0002Reservation"
FOREIGN KEY ("FKMember") REFERENCES 
"Member" ("PKMember")
go

ALTER TABLE "ClassifyEvent" DROP CONSTRAINT "JV__0001ClassifyEvent"
go

ALTER TABLE "ClassifyEvent" ADD CONSTRAINT "JV__0001ClassifyEvent"
FOREIGN KEY ("FKCopy") REFERENCES 
"Copy" ("PKCopy")
go

ALTER TABLE "Loan" DROP CONSTRAINT "JV__0001Loan"
go

ALTER TABLE "Loan" DROP CONSTRAINT "JV__0002Loan"
go

ALTER TABLE "Loan" ADD CONSTRAINT "JV__0001Loan"
FOREIGN KEY ("FKMember") REFERENCES 
"Member" ("PKMember")
go

ALTER TABLE "Loan" ADD CONSTRAINT "JV__0002Loan"
FOREIGN KEY ("FKCopy") REFERENCES 
"Copy" ("PKCopy")
go

ALTER TABLE "Copy" DROP CONSTRAINT "JV__0001Copy"
go

ALTER TABLE "Copy" DROP CONSTRAINT "JV__0002Copy"
go

ALTER TABLE "Copy" ADD CONSTRAINT "JV__0001Copy"
FOREIGN KEY ("FKLibrary") REFERENCES 
"Library" ("PKLibrary")
go

ALTER TABLE "Copy" ADD CONSTRAINT "JV__0002Copy"
FOREIGN KEY ("PKCopy") REFERENCES 
"BorrowEvent" ("FKCopy")
go

ALTER TABLE "BorrowEvent" DROP CONSTRAINT "JV__0001BorrowEvent"
go

ALTER TABLE "BorrowEvent" DROP CONSTRAINT "JV__0002BorrowEvent"
go

ALTER TABLE "BorrowEvent" ADD CONSTRAINT "JV__0001BorrowEvent"
FOREIGN KEY ("FKLoan") REFERENCES 
"Loan" ("PKLoan")
go

ALTER TABLE "BorrowEvent" ADD CONSTRAINT "JV__0002BorrowEvent"
FOREIGN KEY ("FKMember") REFERENCES 
"Member" ("PKMember")
go

ALTER TABLE "Member" DROP CONSTRAINT "JV__0001Member"
go

ALTER TABLE "Member" ADD CONSTRAINT "JV__0001Member"
FOREIGN KEY ("FKLibrary") REFERENCES 
"Library" ("PKLibrary")
go

ALTER TABLE "CreateEvent" ADD CONSTRAINT "JV__0001CreateEvent"
FOREIGN KEY ("FKLibrary") REFERENCES 
"Library" ("PKLibrary")
go

ALTER TABLE "DestroyEvent" ADD CONSTRAINT "JV__0001DestroyEvent"
FOREIGN KEY ("FKLibrary") REFERENCES 
"Library" ("PKLibrary")
go

