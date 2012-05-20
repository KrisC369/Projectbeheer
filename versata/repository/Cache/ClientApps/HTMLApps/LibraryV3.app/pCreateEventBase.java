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
**  pCreateEvent
*/

abstract class pCreateEventBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1CreateEvent = new versata.pdx.html.DataSourcePDX();
	
	//END_FORM_VAR_DECL}}

	public pCreateEventBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1CreateEvent.setName("T1CreateEvent");
			T1CreateEvent.setSession( parentApp.getSession() );
			T1CreateEvent.setQueryInfo("CreateEvent", "", "", "", false);
		       setRootDataSource(T1CreateEvent);
			T1CreateEvent.setPage(this);
			T1CreateEvent.setDataDependency(false, false);
		    T1CreateEvent.setAllowInsert(true);
		    T1CreateEvent.setAllowDelete(true);
		    T1CreateEvent.setAllowUpdate(true);
		    T1CreateEvent.setNumRowsPerPage(10);
		    T1CreateEvent.setPreFetchRowCount(false);
		    
		    T1CreateEvent.setMaxRowsPerFetch(16);
			T1CreateEvent.setSaveMode(T1CreateEvent.SAVE_IMMEDIATE);
		add(T1CreateEvent);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pCreateEvent.htm";
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