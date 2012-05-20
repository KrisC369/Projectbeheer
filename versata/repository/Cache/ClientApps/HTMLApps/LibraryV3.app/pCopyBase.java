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
**  pCopy
*/

abstract class pCopyBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1Copy = new versata.pdx.html.DataSourcePDX();
	Pick T2Library = new Pick();
	versata.pdx.html.DataSourcePDX T3Loan = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T4Reservation = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T7BorrowEvent = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T9ReturnEvent = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T10AcquireEvent = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T12SellEvent = new versata.pdx.html.DataSourcePDX();
	PageNavigation T5pLoan = new PageNavigation();
	PageNavigation T6pReservation = new PageNavigation();
	PageNavigation T8pBorrowEvent = new PageNavigation();
	PageNavigation T11pAcquireEvent = new PageNavigation();
	PageNavigation T13pSellEvent = new PageNavigation();
	
	//END_FORM_VAR_DECL}}

	public pCopyBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1Copy.setName("T1Copy");
			T1Copy.setSession( parentApp.getSession() );
			T1Copy.setQueryInfo("Copy", "", "", "", false);
		       setRootDataSource(T1Copy);
			T1Copy.setPage(this);
			T1Copy.setDataDependency(false, false);
		    T1Copy.setAllowInsert(true);
		    T1Copy.setAllowDelete(true);
		    T1Copy.setAllowUpdate(true);
		    T1Copy.setNumRowsPerPage(10);
		    T1Copy.setPreFetchRowCount(false);
		    
		    T1Copy.setMaxRowsPerFetch(16);
			T1Copy.setSaveMode(T1Copy.SAVE_IMMEDIATE);
		add(T1Copy);
		    				T2Library.Init("T2Library", T1Copy, "Library", "", "", "[PKLibrary] = [?FKLibrary]", "", "PKLibrary, amountOfMembers, amountOfCopies, Name, City, PhoneNumber, WebAddress, LibraryState",getPackageName() + ".genericPick", this);
			T2Library.setParentTableAlias("");
		        
		add(T2Library);
			T3Loan.setName("T3Loan");
			T3Loan.setSession( parentApp.getSession() );
			T3Loan.setQueryInfo("Loan", "", "[FKCopy] = [?PKCopy]", "", true);
			T3Loan.setPage(this);
			T3Loan.setDataDependency(true, false);
		       T3Loan.setDataSource(T1Copy);
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
			T4Reservation.setQueryInfo("Reservation", "", "[FKCopy] = [?PKCopy]", "", true);
			T4Reservation.setPage(this);
			T4Reservation.setDataDependency(true, false);
		       T4Reservation.setDataSource(T1Copy);
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
			T7BorrowEvent.setQueryInfo("BorrowEvent", "", "[FKCopy] = [?PKCopy]", "", false);
			T7BorrowEvent.setPage(this);
			T7BorrowEvent.setDataDependency(false, false);
		       T7BorrowEvent.setDataSource(T1Copy);
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
			T9ReturnEvent.setQueryInfo("ReturnEvent", "", "", "", true);
			T9ReturnEvent.setPage(this);
			T9ReturnEvent.setDataDependency(false, false);
		       T9ReturnEvent.setDataSource(T1Copy);
		    T9ReturnEvent.setAllowInsert(false);
		    T9ReturnEvent.setAllowDelete(false);
		    T9ReturnEvent.setAllowUpdate(false);
		    T9ReturnEvent.setNumRowsPerPage(10);
		    T9ReturnEvent.setPreFetchRowCount(false);
		    
		    T9ReturnEvent.setMaxRowsPerFetch(16);
			T9ReturnEvent.setSaveMode(T9ReturnEvent.SAVE_BUFFERED);
		add(T9ReturnEvent);
			T10AcquireEvent.setName("T10AcquireEvent");
			T10AcquireEvent.setSession( parentApp.getSession() );
			T10AcquireEvent.setQueryInfo("AcquireEvent", "", "[FKCopy] = [?PKCopy]", "", true);
			T10AcquireEvent.setPage(this);
			T10AcquireEvent.setDataDependency(false, false);
		       T10AcquireEvent.setDataSource(T1Copy);
		    T10AcquireEvent.setAllowInsert(false);
		    T10AcquireEvent.setAllowDelete(false);
		    T10AcquireEvent.setAllowUpdate(false);
		    T10AcquireEvent.setNumRowsPerPage(10);
		    T10AcquireEvent.setPreFetchRowCount(false);
		    
		    T10AcquireEvent.setMaxRowsPerFetch(16);
			T10AcquireEvent.setSaveMode(T10AcquireEvent.SAVE_BUFFERED);
		add(T10AcquireEvent);
			T12SellEvent.setName("T12SellEvent");
			T12SellEvent.setSession( parentApp.getSession() );
			T12SellEvent.setQueryInfo("SellEvent", "", "[FKCopy] = [?PKCopy]", "", true);
			T12SellEvent.setPage(this);
			T12SellEvent.setDataDependency(false, false);
		       T12SellEvent.setDataSource(T1Copy);
		    T12SellEvent.setAllowInsert(false);
		    T12SellEvent.setAllowDelete(false);
		    T12SellEvent.setAllowUpdate(false);
		    T12SellEvent.setNumRowsPerPage(10);
		    T12SellEvent.setPreFetchRowCount(false);
		    
		    T12SellEvent.setMaxRowsPerFetch(16);
			T12SellEvent.setSaveMode(T12SellEvent.SAVE_BUFFERED);
		add(T12SellEvent);
			
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
		T8pBorrowEvent.setSourceDataSource(T1Copy);
		T8pBorrowEvent.setTargetPageName(getPackageName() + ".pBorrowEvent");
			T8pBorrowEvent.setName("T8pBorrowEvent");
			T8pBorrowEvent.setInitialAddMode(true);
			T8pBorrowEvent.setInitialQueryMode(false);
			T8pBorrowEvent.setMetaQueryName("BorrowEvent");
			T8pBorrowEvent.setRelnWhere("[FKCopy] = [?PKCopy]");
			T8pBorrowEvent.setDevWhere("");
			T8pBorrowEvent.setRelnFromParent(false);
			T8pBorrowEvent.setOrderBy("");
			T8pBorrowEvent.setTargetPageCaption("BorrowEvent");
			T8pBorrowEvent.setDataDependency(false, false);
		      T8pBorrowEvent.setTargetName("");
			
		add(T8pBorrowEvent);
			
			T11pAcquireEvent.setSourcePage(this);
			T11pAcquireEvent.setParentApp( parentApp );
		T11pAcquireEvent.setSourceDataSource(T10AcquireEvent);
		T11pAcquireEvent.setTargetPageName(getPackageName() + ".pAcquireEvent");
			T11pAcquireEvent.setName("T11pAcquireEvent");
			T11pAcquireEvent.setInitialAddMode(false);
			T11pAcquireEvent.setInitialQueryMode(false);
			T11pAcquireEvent.setMetaQueryName("AcquireEvent");
			T11pAcquireEvent.setRelnWhere("");
			T11pAcquireEvent.setDevWhere("");
			T11pAcquireEvent.setRelnFromParent(true);
			T11pAcquireEvent.setOrderBy("");
			T11pAcquireEvent.setTargetPageCaption("AcquireEvent");
			T11pAcquireEvent.setDataDependency(false, false);
		      T11pAcquireEvent.setTargetName("");
			
		add(T11pAcquireEvent);
			
			T13pSellEvent.setSourcePage(this);
			T13pSellEvent.setParentApp( parentApp );
		T13pSellEvent.setSourceDataSource(T12SellEvent);
		T13pSellEvent.setTargetPageName(getPackageName() + ".pSellEvent");
			T13pSellEvent.setName("T13pSellEvent");
			T13pSellEvent.setInitialAddMode(false);
			T13pSellEvent.setInitialQueryMode(false);
			T13pSellEvent.setMetaQueryName("SellEvent");
			T13pSellEvent.setRelnWhere("");
			T13pSellEvent.setDevWhere("");
			T13pSellEvent.setRelnFromParent(true);
			T13pSellEvent.setOrderBy("");
			T13pSellEvent.setTargetPageCaption("SellEvent");
			T13pSellEvent.setDataDependency(false, false);
		      T13pSellEvent.setTargetName("");
			
		add(T13pSellEvent);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pCopy.htm";
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