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
**  pReturnEvent
*/

abstract class pReturnEventBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1ReturnEvent = new versata.pdx.html.DataSourcePDX();
	Pick T2Loan = new Pick();
	
	//END_FORM_VAR_DECL}}

	public pReturnEventBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1ReturnEvent.setName("T1ReturnEvent");
			T1ReturnEvent.setSession( parentApp.getSession() );
			T1ReturnEvent.setQueryInfo("ReturnEvent", "", "", "", false);
		       setRootDataSource(T1ReturnEvent);
			T1ReturnEvent.setPage(this);
			T1ReturnEvent.setDataDependency(false, false);
		    T1ReturnEvent.setAllowInsert(true);
		    T1ReturnEvent.setAllowDelete(true);
		    T1ReturnEvent.setAllowUpdate(true);
		    T1ReturnEvent.setNumRowsPerPage(10);
		    T1ReturnEvent.setPreFetchRowCount(false);
		    
		    T1ReturnEvent.setMaxRowsPerFetch(16);
			T1ReturnEvent.setSaveMode(T1ReturnEvent.SAVE_IMMEDIATE);
		add(T1ReturnEvent);
		    				T2Loan.Init("T2Loan", T1ReturnEvent, "Loan", "", "", "[PKLoan] = [?FKLoan]", "", "PKLoan, FKMember, FKCopy, StartDate, EndDate, LoanState",getPackageName() + ".genericPick", this);
			T2Loan.setParentTableAlias("");
		        
		add(T2Loan);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pReturnEvent.htm";
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