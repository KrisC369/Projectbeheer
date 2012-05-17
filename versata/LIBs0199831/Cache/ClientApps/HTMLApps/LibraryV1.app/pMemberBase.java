//{{IMPORT_STMTS
package LIBs0199831.LibraryV1;
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
	public static Logger logger = Logger.getLogger("LibraryV1.LibraryV1");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1Member = new versata.pdx.html.DataSourcePDX();
	Pick T3Library = new Pick();
	versata.pdx.html.DataSourcePDX T4Loan = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T5Loan = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T9Reservation = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T10Reservation = new versata.pdx.html.DataSourcePDX();
	PageNavigation T2pLibrary = new PageNavigation();
	PageNavigation T7pLoan = new PageNavigation();
	PageNavigation T6pLoan = new PageNavigation();
	PageNavigation T8pLoan = new PageNavigation();
	PageNavigation T12pReservation = new PageNavigation();
	PageNavigation T11pReservation = new PageNavigation();
	PageNavigation T13pReservation = new PageNavigation();
	
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
		    				T3Library.Init("T3Library", T1Member, "Library", "", "", "[PKLibrary] = [?FKLibrary]", "", "PKLibrary, amountOfMembers, amountOfCopies, Name, City, PhoneNumber, WebAddress, LibraryState",getPackageName() + ".genericPick", this);
			T3Library.setParentTableAlias("");
		        
		add(T3Library);
			T4Loan.setName("T4Loan");
			T4Loan.setSession( parentApp.getSession() );
			T4Loan.setQueryInfo("Loan", "", "[FKMember] = [?PKMember]", "", true);
			T4Loan.setPage(this);
			T4Loan.setDataDependency(false, false);
		       T4Loan.setDataSource(T1Member);
		    T4Loan.setAllowInsert(false);
		    T4Loan.setAllowDelete(false);
		    T4Loan.setAllowUpdate(false);
		    T4Loan.setNumRowsPerPage(10);
		    T4Loan.setPreFetchRowCount(false);
		    
		    T4Loan.setMaxRowsPerFetch(16);
			T4Loan.setSaveMode(T4Loan.SAVE_BUFFERED);
		add(T4Loan);
			T5Loan.setName("T5Loan");
			T5Loan.setSession( parentApp.getSession() );
			T5Loan.setQueryInfo("Loan", "", "[FKMember] = [?PKMember]", "", true);
			T5Loan.setPage(this);
			T5Loan.setDataDependency(false, false);
		       T5Loan.setDataSource(T1Member);
		    T5Loan.setAllowInsert(false);
		    T5Loan.setAllowDelete(false);
		    T5Loan.setAllowUpdate(false);
		    T5Loan.setNumRowsPerPage(10);
		    T5Loan.setPreFetchRowCount(false);
		    
		    T5Loan.setMaxRowsPerFetch(16);
			T5Loan.setSaveMode(T5Loan.SAVE_BUFFERED);
		add(T5Loan);
			T9Reservation.setName("T9Reservation");
			T9Reservation.setSession( parentApp.getSession() );
			T9Reservation.setQueryInfo("Reservation", "", "[FKMember] = [?PKMember]", "", true);
			T9Reservation.setPage(this);
			T9Reservation.setDataDependency(false, false);
		       T9Reservation.setDataSource(T1Member);
		    T9Reservation.setAllowInsert(false);
		    T9Reservation.setAllowDelete(false);
		    T9Reservation.setAllowUpdate(false);
		    T9Reservation.setNumRowsPerPage(10);
		    T9Reservation.setPreFetchRowCount(false);
		    
		    T9Reservation.setMaxRowsPerFetch(16);
			T9Reservation.setSaveMode(T9Reservation.SAVE_BUFFERED);
		add(T9Reservation);
			T10Reservation.setName("T10Reservation");
			T10Reservation.setSession( parentApp.getSession() );
			T10Reservation.setQueryInfo("Reservation", "", "[FKMember] = [?PKMember]", "", true);
			T10Reservation.setPage(this);
			T10Reservation.setDataDependency(false, false);
		       T10Reservation.setDataSource(T1Member);
		    T10Reservation.setAllowInsert(false);
		    T10Reservation.setAllowDelete(false);
		    T10Reservation.setAllowUpdate(false);
		    T10Reservation.setNumRowsPerPage(10);
		    T10Reservation.setPreFetchRowCount(false);
		    
		    T10Reservation.setMaxRowsPerFetch(16);
			T10Reservation.setSaveMode(T10Reservation.SAVE_BUFFERED);
		add(T10Reservation);
			
			T2pLibrary.setSourcePage(this);
			T2pLibrary.setParentApp( parentApp );
		T2pLibrary.setSourceDataSource(T1Member);
		T2pLibrary.setTargetPageName(getPackageName() + ".pLibrary");
			T2pLibrary.setName("T2pLibrary");
			T2pLibrary.setInitialAddMode(false);
			T2pLibrary.setInitialQueryMode(false);
			T2pLibrary.setMetaQueryName("Library");
			T2pLibrary.setRelnWhere("[PKLibrary] = [?FKLibrary]");
			T2pLibrary.setDevWhere("");
			T2pLibrary.setRelnFromParent(false);
			T2pLibrary.setOrderBy("");
			T2pLibrary.setTargetPageCaption("Library");
			T2pLibrary.setDataDependency(false, true);
		      T2pLibrary.setTargetName("");
			
		add(T2pLibrary);
			
			T7pLoan.setSourcePage(this);
			T7pLoan.setParentApp( parentApp );
		T7pLoan.setSourceDataSource(T4Loan);
		T7pLoan.setTargetPageName(getPackageName() + ".pLoan");
			T7pLoan.setName("T7pLoan");
			T7pLoan.setInitialAddMode(false);
			T7pLoan.setInitialQueryMode(false);
			T7pLoan.setMetaQueryName("Loan");
			T7pLoan.setRelnWhere("");
			T7pLoan.setDevWhere("");
			T7pLoan.setRelnFromParent(true);
			T7pLoan.setOrderBy("");
			T7pLoan.setTargetPageCaption("T7Loan");
			T7pLoan.setDataDependency(false, false);
		      T7pLoan.setTargetName("");
			
		add(T7pLoan);
			
			T6pLoan.setSourcePage(this);
			T6pLoan.setParentApp( parentApp );
		T6pLoan.setSourceDataSource(T5Loan);
		T6pLoan.setTargetPageName(getPackageName() + ".pLoan");
			T6pLoan.setName("T6pLoan");
			T6pLoan.setInitialAddMode(false);
			T6pLoan.setInitialQueryMode(false);
			T6pLoan.setMetaQueryName("Loan");
			T6pLoan.setRelnWhere("");
			T6pLoan.setDevWhere("");
			T6pLoan.setRelnFromParent(true);
			T6pLoan.setOrderBy("");
			T6pLoan.setTargetPageCaption("T6Loan");
			T6pLoan.setDataDependency(false, false);
		      T6pLoan.setTargetName("");
			
		add(T6pLoan);
			
			T8pLoan.setSourcePage(this);
			T8pLoan.setParentApp( parentApp );
		T8pLoan.setSourceDataSource(T1Member);
		T8pLoan.setTargetPageName(getPackageName() + ".pLoan");
			T8pLoan.setName("T8pLoan");
			T8pLoan.setInitialAddMode(false);
			T8pLoan.setInitialQueryMode(false);
			T8pLoan.setMetaQueryName("Loan");
			T8pLoan.setRelnWhere("[FKMember] = [?PKMember]");
			T8pLoan.setDevWhere("");
			T8pLoan.setRelnFromParent(true);
			T8pLoan.setOrderBy("");
			T8pLoan.setTargetPageCaption("T8Loan");
			T8pLoan.setDataDependency(false, false);
		      T8pLoan.setTargetName("");
			
		add(T8pLoan);
			
			T12pReservation.setSourcePage(this);
			T12pReservation.setParentApp( parentApp );
		T12pReservation.setSourceDataSource(T9Reservation);
		T12pReservation.setTargetPageName(getPackageName() + ".pReservation");
			T12pReservation.setName("T12pReservation");
			T12pReservation.setInitialAddMode(false);
			T12pReservation.setInitialQueryMode(false);
			T12pReservation.setMetaQueryName("Reservation");
			T12pReservation.setRelnWhere("");
			T12pReservation.setDevWhere("");
			T12pReservation.setRelnFromParent(true);
			T12pReservation.setOrderBy("");
			T12pReservation.setTargetPageCaption("T12Reservation");
			T12pReservation.setDataDependency(false, false);
		      T12pReservation.setTargetName("");
			
		add(T12pReservation);
			
			T11pReservation.setSourcePage(this);
			T11pReservation.setParentApp( parentApp );
		T11pReservation.setSourceDataSource(T10Reservation);
		T11pReservation.setTargetPageName(getPackageName() + ".pReservation");
			T11pReservation.setName("T11pReservation");
			T11pReservation.setInitialAddMode(false);
			T11pReservation.setInitialQueryMode(false);
			T11pReservation.setMetaQueryName("Reservation");
			T11pReservation.setRelnWhere("");
			T11pReservation.setDevWhere("");
			T11pReservation.setRelnFromParent(true);
			T11pReservation.setOrderBy("");
			T11pReservation.setTargetPageCaption("T11Reservation");
			T11pReservation.setDataDependency(false, false);
		      T11pReservation.setTargetName("");
			
		add(T11pReservation);
			
			T13pReservation.setSourcePage(this);
			T13pReservation.setParentApp( parentApp );
		T13pReservation.setSourceDataSource(T1Member);
		T13pReservation.setTargetPageName(getPackageName() + ".pReservation");
			T13pReservation.setName("T13pReservation");
			T13pReservation.setInitialAddMode(false);
			T13pReservation.setInitialQueryMode(false);
			T13pReservation.setMetaQueryName("Reservation");
			T13pReservation.setRelnWhere("[FKMember] = [?PKMember]");
			T13pReservation.setDevWhere("");
			T13pReservation.setRelnFromParent(true);
			T13pReservation.setOrderBy("");
			T13pReservation.setTargetPageCaption("T13Reservation");
			T13pReservation.setDataDependency(false, false);
		      T13pReservation.setTargetName("");
			
		add(T13pReservation);
		
		
		
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