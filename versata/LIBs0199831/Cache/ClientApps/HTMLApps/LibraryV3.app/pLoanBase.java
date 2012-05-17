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
**  pLoan
*/

abstract class pLoanBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1Loan = new versata.pdx.html.DataSourcePDX();
	Pick T2Member = new Pick();
	Pick T3Copy = new Pick();
	versata.pdx.html.DataSourcePDX T4ReturnEvent = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T6LoseEvent = new versata.pdx.html.DataSourcePDX();
	PageNavigation T5pReturnEvent = new PageNavigation();
	PageNavigation T7pLoseEvent = new PageNavigation();
	
	//END_FORM_VAR_DECL}}

	public pLoanBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1Loan.setName("T1Loan");
			T1Loan.setSession( parentApp.getSession() );
			T1Loan.setQueryInfo("Loan", "", "", "", false);
		       setRootDataSource(T1Loan);
			T1Loan.setPage(this);
			T1Loan.setDataDependency(false, false);
		    T1Loan.setAllowInsert(true);
		    T1Loan.setAllowDelete(true);
		    T1Loan.setAllowUpdate(true);
		    T1Loan.setNumRowsPerPage(10);
		    T1Loan.setPreFetchRowCount(false);
		    
		    T1Loan.setMaxRowsPerFetch(16);
			T1Loan.setSaveMode(T1Loan.SAVE_IMMEDIATE);
		add(T1Loan);
		    				T2Member.Init("T2Member", T1Loan, "Member", "", "", "[PKMember] = [?FKMember]", "", "PKMember, FKLibrary, FirstName, LastName, Address, City, Country, MobileNumber, MemberState, CurrentLoans, CurrentReservations",getPackageName() + ".genericPick", this);
			T2Member.setParentTableAlias("");
		        
		add(T2Member);
		    				T3Copy.Init("T3Copy", T1Loan, "Copy", "", "", "[PKCopy] = [?FKCopy]", "", "PKCopy, FKLibrary, numberOfLoans, Title, Author, ISBN, CopyState, CurrentLoans, CurrentReservations",getPackageName() + ".genericPick", this);
			T3Copy.setParentTableAlias("");
		        
		add(T3Copy);
			T4ReturnEvent.setName("T4ReturnEvent");
			T4ReturnEvent.setSession( parentApp.getSession() );
			T4ReturnEvent.setQueryInfo("ReturnEvent", "", "[FKLoan] = [?PKLoan]", "", true);
			T4ReturnEvent.setPage(this);
			T4ReturnEvent.setDataDependency(false, false);
		       T4ReturnEvent.setDataSource(T1Loan);
		    T4ReturnEvent.setAllowInsert(false);
		    T4ReturnEvent.setAllowDelete(false);
		    T4ReturnEvent.setAllowUpdate(false);
		    T4ReturnEvent.setNumRowsPerPage(10);
		    T4ReturnEvent.setPreFetchRowCount(false);
		    
		    T4ReturnEvent.setMaxRowsPerFetch(16);
			T4ReturnEvent.setSaveMode(T4ReturnEvent.SAVE_BUFFERED);
		add(T4ReturnEvent);
			T6LoseEvent.setName("T6LoseEvent");
			T6LoseEvent.setSession( parentApp.getSession() );
			T6LoseEvent.setQueryInfo("LoseEvent", "", "[FKLoan] = [?PKLoan]", "", true);
			T6LoseEvent.setPage(this);
			T6LoseEvent.setDataDependency(false, false);
		       T6LoseEvent.setDataSource(T1Loan);
		    T6LoseEvent.setAllowInsert(false);
		    T6LoseEvent.setAllowDelete(false);
		    T6LoseEvent.setAllowUpdate(false);
		    T6LoseEvent.setNumRowsPerPage(10);
		    T6LoseEvent.setPreFetchRowCount(false);
		    
		    T6LoseEvent.setMaxRowsPerFetch(16);
			T6LoseEvent.setSaveMode(T6LoseEvent.SAVE_BUFFERED);
		add(T6LoseEvent);
			
			T5pReturnEvent.setSourcePage(this);
			T5pReturnEvent.setParentApp( parentApp );
		T5pReturnEvent.setSourceDataSource(T4ReturnEvent);
		T5pReturnEvent.setTargetPageName(getPackageName() + ".pReturnEvent");
			T5pReturnEvent.setName("T5pReturnEvent");
			T5pReturnEvent.setInitialAddMode(false);
			T5pReturnEvent.setInitialQueryMode(false);
			T5pReturnEvent.setMetaQueryName("ReturnEvent");
			T5pReturnEvent.setRelnWhere("");
			T5pReturnEvent.setDevWhere("");
			T5pReturnEvent.setRelnFromParent(true);
			T5pReturnEvent.setOrderBy("");
			T5pReturnEvent.setTargetPageCaption("ReturnEvent");
			T5pReturnEvent.setDataDependency(false, false);
		      T5pReturnEvent.setTargetName("");
			
		add(T5pReturnEvent);
			
			T7pLoseEvent.setSourcePage(this);
			T7pLoseEvent.setParentApp( parentApp );
		T7pLoseEvent.setSourceDataSource(T6LoseEvent);
		T7pLoseEvent.setTargetPageName(getPackageName() + ".pLoseEvent");
			T7pLoseEvent.setName("T7pLoseEvent");
			T7pLoseEvent.setInitialAddMode(false);
			T7pLoseEvent.setInitialQueryMode(false);
			T7pLoseEvent.setMetaQueryName("LoseEvent");
			T7pLoseEvent.setRelnWhere("");
			T7pLoseEvent.setDevWhere("");
			T7pLoseEvent.setRelnFromParent(true);
			T7pLoseEvent.setOrderBy("");
			T7pLoseEvent.setTargetPageCaption("LoseEvent");
			T7pLoseEvent.setDataDependency(false, false);
		      T7pLoseEvent.setTargetName("");
			
		add(T7pLoseEvent);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pLoan.htm";
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