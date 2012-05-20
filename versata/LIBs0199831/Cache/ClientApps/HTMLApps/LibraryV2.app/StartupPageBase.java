//{{IMPORT_STMTS
package LIBs0199831.LibraryV2;
import versata.vfc.html.*;
import versata.vfc.*;
import versata.common.*;
import java.io.*;
import javax.swing.text.html.*;
import com.versata.util.logging.*;
//END_IMPORT_STMTS}}

/*
**  StartupPage
*/

abstract class StartupPageBase extends VSPage implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV2.LibraryV2");
	public static HTMLDocumentSpec docSpec = null;
	PageNavigation T1pLibrary = new PageNavigation();
	PageNavigation T2pEnterEvent = new PageNavigation();
	
	//END_FORM_VAR_DECL}}

	public StartupPageBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			
			T1pLibrary.setSourcePage(this);
			T1pLibrary.setParentApp( parentApp );
		    T1pLibrary.setSourceDataSource(null);
		T1pLibrary.setTargetPageName(getPackageName() + ".pLibrary");
			T1pLibrary.setName("T1pLibrary");
			T1pLibrary.setInitialAddMode(false);
			T1pLibrary.setInitialQueryMode(false);
			T1pLibrary.setMetaQueryName("Library");
			T1pLibrary.setRelnWhere("");
			T1pLibrary.setDevWhere("");
			T1pLibrary.setRelnFromParent(true);
			T1pLibrary.setOrderBy("");
			T1pLibrary.setTargetPageCaption("Library");
			T1pLibrary.setDataDependency(false, false);
		      T1pLibrary.setTargetName("Display");
			
		add(T1pLibrary);
			
			T2pEnterEvent.setSourcePage(this);
			T2pEnterEvent.setParentApp( parentApp );
		    T2pEnterEvent.setSourceDataSource(null);
		T2pEnterEvent.setTargetPageName(getPackageName() + ".pEnterEvent");
			T2pEnterEvent.setName("T2pEnterEvent");
			T2pEnterEvent.setInitialAddMode(false);
			T2pEnterEvent.setInitialQueryMode(false);
			T2pEnterEvent.setMetaQueryName("EnterEvent");
			T2pEnterEvent.setRelnWhere("");
			T2pEnterEvent.setDevWhere("");
			T2pEnterEvent.setRelnFromParent(true);
			T2pEnterEvent.setOrderBy("");
			T2pEnterEvent.setTargetPageCaption("EnterEvent");
			T2pEnterEvent.setDataDependency(false, false);
		      T2pEnterEvent.setTargetName("Display");
			
		add(T2pEnterEvent);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "StartupPage.htm";
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