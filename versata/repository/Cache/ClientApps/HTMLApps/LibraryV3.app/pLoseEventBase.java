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
**  pLoseEvent
*/

abstract class pLoseEventBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1LoseEvent = new versata.pdx.html.DataSourcePDX();
	Pick T2Loan = new Pick();
	
	//END_FORM_VAR_DECL}}

	public pLoseEventBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1LoseEvent.setName("T1LoseEvent");
			T1LoseEvent.setSession( parentApp.getSession() );
			T1LoseEvent.setQueryInfo("LoseEvent", "", "", "", false);
		       setRootDataSource(T1LoseEvent);
			T1LoseEvent.setPage(this);
			T1LoseEvent.setDataDependency(false, false);
		    T1LoseEvent.setAllowInsert(true);
		    T1LoseEvent.setAllowDelete(true);
		    T1LoseEvent.setAllowUpdate(true);
		    T1LoseEvent.setNumRowsPerPage(10);
		    T1LoseEvent.setPreFetchRowCount(false);
		    
		    T1LoseEvent.setMaxRowsPerFetch(16);
			T1LoseEvent.setSaveMode(T1LoseEvent.SAVE_IMMEDIATE);
		add(T1LoseEvent);
		    				T2Loan.Init("T2Loan", T1LoseEvent, "Loan", "", "", "[PKLoan] = [?FKLoan]", "", "PKLoan, FKMember, FKCopy, StartDate, EndDate, LoanState",getPackageName() + ".genericPick", this);
			T2Loan.setParentTableAlias("");
		        
		add(T2Loan);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pLoseEvent.htm";
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