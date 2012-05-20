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
**  pDestroyEvent
*/

abstract class pDestroyEventBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1DestroyEvent = new versata.pdx.html.DataSourcePDX();
	
	//END_FORM_VAR_DECL}}

	public pDestroyEventBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1DestroyEvent.setName("T1DestroyEvent");
			T1DestroyEvent.setSession( parentApp.getSession() );
			T1DestroyEvent.setQueryInfo("DestroyEvent", "", "", "", false);
		       setRootDataSource(T1DestroyEvent);
			T1DestroyEvent.setPage(this);
			T1DestroyEvent.setDataDependency(false, false);
		    T1DestroyEvent.setAllowInsert(true);
		    T1DestroyEvent.setAllowDelete(true);
		    T1DestroyEvent.setAllowUpdate(true);
		    T1DestroyEvent.setNumRowsPerPage(10);
		    T1DestroyEvent.setPreFetchRowCount(false);
		    
		    T1DestroyEvent.setMaxRowsPerFetch(16);
			T1DestroyEvent.setSaveMode(T1DestroyEvent.SAVE_IMMEDIATE);
		add(T1DestroyEvent);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pDestroyEvent.htm";
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