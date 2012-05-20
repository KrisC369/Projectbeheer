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
**  pCopy
*/

abstract class pCopyBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV2.LibraryV2");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1Copy = new versata.pdx.html.DataSourcePDX();
	Pick T2Library = new Pick();
	
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