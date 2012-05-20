//{{IMPORT_STMTS
package LIBs0199831.LibraryV3;
import versata.vfc.html.*;
import versata.vfc.*;
import versata.common.*;
import java.io.*;
import javax.swing.text.html.*;
import com.versata.util.logging.*;
//END_IMPORT_STMTS}}

/*
**  pMember
*/

abstract class pMemberBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1Member = new versata.pdx.html.DataSourcePDX();
	Pick T2Library = new Pick();
	versata.pdx.html.DataSourcePDX T3Loan = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T4Reservation = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T7BorrowEvent = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T9ReturnEvent = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T10EnterEvent = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T12LeaveEvent = new versata.pdx.html.DataSourcePDX();
	PageNavigation T5pLoan = new PageNavigation();
	PageNavigation T6pReservation = new PageNavigation();
	PageNavigation T8pBorrowEvent = new PageNavigation();
	PageNavigation T11pEnterEvent = new PageNavigation();
	PageNavigation T13pLeaveEvent = new PageNavigation();
	
	//END_FORM_VAR_DECL}}

	public pMemberBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1Member.setName("T1Member");
			T1Member.setSession( parentApp.getSession() );
			T1Member.setQueryInfo("Member", "", "", "", false);
		       setRootDataSource(T1Member);
			T1Member.setPage(this);
			T1Member.setDataDependency(false, false);
		    T1Member.setAllowInsert(true);
		    T1Member.setAllowDelete(true);
		    T1Member.setAllowUpdate(true);
		    T1Member.setNumRowsPerPage(10);
		    T1Member.setPreFetchRowCount(false);
		    
		    T1Member.setMaxRowsPerFetch(16);
			T1Member.setSaveMode(T1Member.SAVE_IMMEDIATE);
		add(T1Member);
		    				T2Library.Init("T2Library", T1Member, "Library", "", "", "[PKLibrary] = [?FKLibrary]", "", "PKLibrary, amountOfMembers, amountOfCopies, Name, City, PhoneNumber, WebAddress, LibraryState",getPackageName() + ".genericPick", this);
			T2Library.setParentTableAlias("");
		        
		add(T2Library);
			T3Loan.setName("T3Loan");
			T3Loan.setSession( parentApp.getSession() );
			T3Loan.setQueryInfo("Loan", "", "[FKMember] = [?PKMember]", "", true);
			T3Loan.setPage(this);
			T3Loan.setDataDependency(true, false);
		       T3Loan.setDataSource(T1Member);
		    T3Loan.setAllowInsert(false);
		    T3Loan.setAllowDelete(false);
		    T3Loan.setAllowUpdate(false);
		    T3Loan.setNumRowsPerPage(10);
		    T3Loan.setPreFetchRowCount(false);
		    
		    T3Loan.setMaxRowsPerFetch(16);
			T3Loan.setSaveMode(T3Loan.SAVE_BUFFERED);
		add(T3Loan);
			T4Reservation.setName("T4Reservation");
			T4Reservation.setSession( parentApp.getSession() );
			T4Reservation.setQueryInfo("Reservation", "", "[FKMember] = [?PKMember]", "", true);
			T4Reservation.setPage(this);
			T4Reservation.setDataDependency(true, false);
		       T4Reservation.setDataSource(T1Member);
		    T4Reservation.setAllowInsert(false);
		    T4Reservation.setAllowDelete(false);
		    T4Reservation.setAllowUpdate(false);
		    T4Reservation.setNumRowsPerPage(10);
		    T4Reservation.setPreFetchRowCount(false);
		    
		    T4Reservation.setMaxRowsPerFetch(16);
			T4Reservation.setSaveMode(T4Reservation.SAVE_BUFFERED);
		add(T4Reservation);
			T7BorrowEvent.setName("T7BorrowEvent");
			T7BorrowEvent.setSession( parentApp.getSession() );
			T7BorrowEvent.setQueryInfo("BorrowEvent", "", "[FKMember] = [?PKMember]", "", true);
			T7BorrowEvent.setPage(this);
			T7BorrowEvent.setDataDependency(false, false);
		       T7BorrowEvent.setDataSource(T1Member);
		    T7BorrowEvent.setAllowInsert(false);
		    T7BorrowEvent.setAllowDelete(false);
		    T7BorrowEvent.setAllowUpdate(false);
		    T7BorrowEvent.setNumRowsPerPage(10);
		    T7BorrowEvent.setPreFetchRowCount(false);
		    
		    T7BorrowEvent.setMaxRowsPerFetch(16);
			T7BorrowEvent.setSaveMode(T7BorrowEvent.SAVE_BUFFERED);
		add(T7BorrowEvent);
			T9ReturnEvent.setName("T9ReturnEvent");
			T9ReturnEvent.setSession( parentApp.getSession() );
			T9ReturnEvent.setQueryInfo("ReturnEvent", "", "[FKLoan] = [?PKLoan]", "", true);
			T9ReturnEvent.setPage(this);
			T9ReturnEvent.setDataDependency(false, false);
		       T9ReturnEvent.setDataSource(T3Loan);
		    T9ReturnEvent.setAllowInsert(false);
		    T9ReturnEvent.setAllowDelete(false);
		    T9ReturnEvent.setAllowUpdate(false);
		    T9ReturnEvent.setNumRowsPerPage(10);
		    T9ReturnEvent.setPreFetchRowCount(false);
		    
		    T9ReturnEvent.setMaxRowsPerFetch(16);
			T9ReturnEvent.setSaveMode(T9ReturnEvent.SAVE_BUFFERED);
		add(T9ReturnEvent);
			T10EnterEvent.setName("T10EnterEvent");
			T10EnterEvent.setSession( parentApp.getSession() );
			T10EnterEvent.setQueryInfo("EnterEvent", "", "[FKMember] = [?PKMember]", "", true);
			T10EnterEvent.setPage(this);
			T10EnterEvent.setDataDependency(false, false);
		       T10EnterEvent.setDataSource(T1Member);
		    T10EnterEvent.setAllowInsert(false);
		    T10EnterEvent.setAllowDelete(false);
		    T10EnterEvent.setAllowUpdate(false);
		    T10EnterEvent.setNumRowsPerPage(10);
		    T10EnterEvent.setPreFetchRowCount(false);
		    
		    T10EnterEvent.setMaxRowsPerFetch(16);
			T10EnterEvent.setSaveMode(T10EnterEvent.SAVE_BUFFERED);
		add(T10EnterEvent);
			T12LeaveEvent.setName("T12LeaveEvent");
			T12LeaveEvent.setSession( parentApp.getSession() );
			T12LeaveEvent.setQueryInfo("LeaveEvent", "", "[FKMember] = [?PKMember]", "", true);
			T12LeaveEvent.setPage(this);
			T12LeaveEvent.setDataDependency(false, false);
		       T12LeaveEvent.setDataSource(T1Member);
		    T12LeaveEvent.setAllowInsert(false);
		    T12LeaveEvent.setAllowDelete(false);
		    T12LeaveEvent.setAllowUpdate(false);
		    T12LeaveEvent.setNumRowsPerPage(10);
		    T12LeaveEvent.setPreFetchRowCount(false);
		    
		    T12LeaveEvent.setMaxRowsPerFetch(16);
			T12LeaveEvent.setSaveMode(T12LeaveEvent.SAVE_BUFFERED);
		add(T12LeaveEvent);
			
			T5pLoan.setSourcePage(this);
			T5pLoan.setParentApp( parentApp );
		T5pLoan.setSourceDataSource(T3Loan);
		T5pLoan.setTargetPageName(getPackageName() + ".pLoan");
			T5pLoan.setName("T5pLoan");
			T5pLoan.setInitialAddMode(false);
			T5pLoan.setInitialQueryMode(false);
			T5pLoan.setMetaQueryName("Loan");
			T5pLoan.setRelnWhere("");
			T5pLoan.setDevWhere("");
			T5pLoan.setRelnFromParent(true);
			T5pLoan.setOrderBy("");
			T5pLoan.setTargetPageCaption("Loan");
			T5pLoan.setDataDependency(true, false);
		      T5pLoan.setTargetName("");
			
		add(T5pLoan);
			
			T6pReservation.setSourcePage(this);
			T6pReservation.setParentApp( parentApp );
		T6pReservation.setSourceDataSource(T4Reservation);
		T6pReservation.setTargetPageName(getPackageName() + ".pReservation");
			T6pReservation.setName("T6pReservation");
			T6pReservation.setInitialAddMode(false);
			T6pReservation.setInitialQueryMode(false);
			T6pReservation.setMetaQueryName("Reservation");
			T6pReservation.setRelnWhere("");
			T6pReservation.setDevWhere("");
			T6pReservation.setRelnFromParent(true);
			T6pReservation.setOrderBy("");
			T6pReservation.setTargetPageCaption("Reservation");
			T6pReservation.setDataDependency(true, false);
		      T6pReservation.setTargetName("");
			
		add(T6pReservation);
			
			T8pBorrowEvent.setSourcePage(this);
			T8pBorrowEvent.setParentApp( parentApp );
		T8pBorrowEvent.setSourceDataSource(T7BorrowEvent);
		T8pBorrowEvent.setTargetPageName(getPackageName() + ".pBorrowEvent");
			T8pBorrowEvent.setName("T8pBorrowEvent");
			T8pBorrowEvent.setInitialAddMode(false);
			T8pBorrowEvent.setInitialQueryMode(false);
			T8pBorrowEvent.setMetaQueryName("BorrowEvent");
			T8pBorrowEvent.setRelnWhere("");
			T8pBorrowEvent.setDevWhere("");
			T8pBorrowEvent.setRelnFromParent(true);
			T8pBorrowEvent.setOrderBy("");
			T8pBorrowEvent.setTargetPageCaption("BorrowEvent");
			T8pBorrowEvent.setDataDependency(false, false);
		      T8pBorrowEvent.setTargetName("");
			
		add(T8pBorrowEvent);
			
			T11pEnterEvent.setSourcePage(this);
			T11pEnterEvent.setParentApp( parentApp );
		T11pEnterEvent.setSourceDataSource(T10EnterEvent);
		T11pEnterEvent.setTargetPageName(getPackageName() + ".pEnterEvent");
			T11pEnterEvent.setName("T11pEnterEvent");
			T11pEnterEvent.setInitialAddMode(false);
			T11pEnterEvent.setInitialQueryMode(false);
			T11pEnterEvent.setMetaQueryName("EnterEvent");
			T11pEnterEvent.setRelnWhere("");
			T11pEnterEvent.setDevWhere("");
			T11pEnterEvent.setRelnFromParent(true);
			T11pEnterEvent.setOrderBy("");
			T11pEnterEvent.setTargetPageCaption("EnterEvent");
			T11pEnterEvent.setDataDependency(false, false);
		      T11pEnterEvent.setTargetName("");
			
		add(T11pEnterEvent);
			
			T13pLeaveEvent.setSourcePage(this);
			T13pLeaveEvent.setParentApp( parentApp );
		T13pLeaveEvent.setSourceDataSource(T12LeaveEvent);
		T13pLeaveEvent.setTargetPageName(getPackageName() + ".pLeaveEvent");
			T13pLeaveEvent.setName("T13pLeaveEvent");
			T13pLeaveEvent.setInitialAddMode(false);
			T13pLeaveEvent.setInitialQueryMode(false);
			T13pLeaveEvent.setMetaQueryName("LeaveEvent");
			T13pLeaveEvent.setRelnWhere("");
			T13pLeaveEvent.setDevWhere("");
			T13pLeaveEvent.setRelnFromParent(true);
			T13pLeaveEvent.setOrderBy("");
			T13pLeaveEvent.setTargetPageCaption("LeaveEvent");
			T13pLeaveEvent.setDataDependency(false, false);
		      T13pLeaveEvent.setTargetName("");
			
		add(T13pLeaveEvent);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pMember.htm";
		}
	    public HTMLDocumentSpec getDefaultDocumentSpecification() {    
			//At one point of time only one thread should
			//read document specification. 
			synchronized(this.getClass()) {
		        	if (docSpec == null) {
	        	    		docSpec = VSPage.createSpecification(getFileLocation()  + getFileName());
	        		}
			}
	        	return docSpec;
	    }
	
	//END_FORM_GETFILENAME}}
	
	

	abstract void addListeners();
	public void afterPageInitialize() {
		super.afterPageInitialize();
	}
}