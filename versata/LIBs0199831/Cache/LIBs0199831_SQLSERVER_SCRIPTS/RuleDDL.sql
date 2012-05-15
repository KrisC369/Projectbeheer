ALTER TABLE "CancelEvent" ADD CONSTRAINT "JV__0001CancelEvent"
FOREIGN KEY ("FKReservation") REFERENCES 
"Reservation" ("PKReservation")
go

ALTER TABLE "EnterEvent" ADD CONSTRAINT "JV__0001EnterEvent"
FOREIGN KEY ("FKMember") REFERENCES 
"Member" ("PKMember")
go

ALTER TABLE "LeaveEvent" ADD CONSTRAINT "JV__0001LeaveEvent"
FOREIGN KEY ("FKMember") REFERENCES 
"Member" ("PKMember")
go

ALTER TABLE "BorrowEvent" ADD CONSTRAINT "JV__0001BorrowEvent"
FOREIGN KEY ("FKLoan") REFERENCES 
"Loan" ("PKLoan")
go

ALTER TABLE "BorrowEvent" ADD CONSTRAINT "JV__0002BorrowEvent"
FOREIGN KEY ("FKMember") REFERENCES 
"Member" ("PKMember")
go

ALTER TABLE "ReturnEvent" ADD CONSTRAINT "JV__0001ReturnEvent"
FOREIGN KEY ("FKLoan") REFERENCES 
"Loan" ("PKLoan")
go

ALTER TABLE "LoseEvent" ADD CONSTRAINT "JV__0001LoseEvent"
FOREIGN KEY ("FKLoan") REFERENCES 
"Loan" ("PKLoan")
go

ALTER TABLE "FetchEvent" ADD CONSTRAINT "JV__0001FetchEvent"
FOREIGN KEY ("FKReservation") REFERENCES 
"Reservation" ("PKReservation")
go

ALTER TABLE "FetchEvent" ADD CONSTRAINT "JV__0002FetchEvent"
FOREIGN KEY ("FKLoan") REFERENCES 
"Loan" ("PKLoan")
go

ALTER TABLE "RenewEvent" ADD CONSTRAINT "JV__0001RenewEvent"
FOREIGN KEY ("FKLoan") REFERENCES 
"Loan" ("PKLoan")
go

ALTER TABLE "Loan" ADD CONSTRAINT "JV__0001Loan"
FOREIGN KEY ("FKMember") REFERENCES 
"Member" ("PKMember")
go

ALTER TABLE "Loan" ADD CONSTRAINT "JV__0002Loan"
FOREIGN KEY ("FKCopy") REFERENCES 
"Copy" ("PKCopy")
go

ALTER TABLE "AcquireEvent" ADD CONSTRAINT "JV__0001AcquireEvent"
FOREIGN KEY ("FKCopy") REFERENCES 
"Copy" ("PKCopy")
go

ALTER TABLE "SellEvent" ADD CONSTRAINT "JV__0001SellEvent"
FOREIGN KEY ("FKCopy") REFERENCES 
"Copy" ("PKCopy")
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

ALTER TABLE "Reservation" ADD CONSTRAINT "JV__0001Reservation"
FOREIGN KEY ("FKCopy") REFERENCES 
"Copy" ("PKCopy")
go

ALTER TABLE "Reservation" ADD CONSTRAINT "JV__0002Reservation"
FOREIGN KEY ("FKMember") REFERENCES 
"Member" ("PKMember")
go

ALTER TABLE "ClassifyEvent" ADD CONSTRAINT "JV__0001ClassifyEvent"
FOREIGN KEY ("FKCopy") REFERENCES 
"Copy" ("PKCopy")
go

ALTER TABLE "Copy" ADD CONSTRAINT "JV__0001Copy"
FOREIGN KEY ("FKLibrary") REFERENCES 
"Library" ("PKLibrary")
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

