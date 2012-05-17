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
**  pAcquireEvent
*/

abstract class pAcquireEventBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1AcquireEvent = new versata.pdx.html.DataSourcePDX();
	Pick T2Copy = new Pick();
	
	//END_FORM_VAR_DECL}}

	public pAcquireEventBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1AcquireEvent.setName("T1AcquireEvent");
			T1AcquireEvent.setSession( parentApp.getSession() );
			T1AcquireEvent.setQueryInfo("AcquireEvent", "", "", "", false);
		       setRootDataSource(T1AcquireEvent);
			T1AcquireEvent.setPage(this);
			T1AcquireEvent.setDataDependency(false, false);
		    T1AcquireEvent.setAllowInsert(true);
		    T1AcquireEvent.setAllowDelete(true);
		    T1AcquireEvent.setAllowUpdate(true);
		    T1AcquireEvent.setNumRowsPerPage(10);
		    T1AcquireEvent.setPreFetchRowCount(false);
		    
		    T1AcquireEvent.setMaxRowsPerFetch(16);
			T1AcquireEvent.setSaveMode(T1AcquireEvent.SAVE_IMMEDIATE);
		add(T1AcquireEvent);
		    				T2Copy.Init("T2Copy", T1AcquireEvent, "Copy", "", "", "[PKCopy] = [?FKCopy]", "", "PKCopy, FKLibrary, numberOfLoans, Title, Author, ISBN, CopyState, CurrentLoans, CurrentReservations",getPackageName() + ".genericPick", this);
			T2Copy.setParentTableAlias("");
		        
		add(T2Copy);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pAcquireEvent.htm";
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