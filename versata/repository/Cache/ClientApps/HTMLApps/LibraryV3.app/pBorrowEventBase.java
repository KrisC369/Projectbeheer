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
**  pBorrowEvent
*/

abstract class pBorrowEventBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1BorrowEvent = new versata.pdx.html.DataSourcePDX();
	Pick T2Loan = new Pick();
	Pick T3Member = new Pick();
	
	//END_FORM_VAR_DECL}}

	public pBorrowEventBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1BorrowEvent.setName("T1BorrowEvent");
			T1BorrowEvent.setSession( parentApp.getSession() );
			T1BorrowEvent.setQueryInfo("BorrowEvent", "", "", "", false);
		       setRootDataSource(T1BorrowEvent);
			T1BorrowEvent.setPage(this);
			T1BorrowEvent.setDataDependency(false, false);
		    T1BorrowEvent.setAllowInsert(true);
		    T1BorrowEvent.setAllowDelete(true);
		    T1BorrowEvent.setAllowUpdate(true);
		    T1BorrowEvent.setNumRowsPerPage(10);
		    T1BorrowEvent.setPreFetchRowCount(false);
		    
		    T1BorrowEvent.setMaxRowsPerFetch(16);
			T1BorrowEvent.setSaveMode(T1BorrowEvent.SAVE_IMMEDIATE);
		add(T1BorrowEvent);
		    				T2Loan.Init("T2Loan", T1BorrowEvent, "Loan", "", "", "[PKLoan] = [?FKLoan]", "", "PKLoan, FKMember, FKCopy, StartDate, EndDate, LoanState",getPackageName() + ".genericPick", this);
			T2Loan.setParentTableAlias("");
		        
		add(T2Loan);
		    				T3Member.Init("T3Member", T1BorrowEvent, "Member", "", "", "[PKMember] = [?FKMember]", "", "PKMember, FKLibrary, FirstName, LastName, Address, City, Country, MobileNumber, MemberState, CurrentLoans, CurrentReservations",getPackageName() + ".genericPick", this);
			T3Member.setParentTableAlias("");
		        
		add(T3Member);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pBorrowEvent.htm";
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