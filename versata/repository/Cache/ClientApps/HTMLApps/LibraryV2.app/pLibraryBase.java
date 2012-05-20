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
**  pLibrary
*/

abstract class pLibraryBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV2.LibraryV2");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1Library = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T2Member = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T3Copy = new versata.pdx.html.DataSourcePDX();
	PageNavigation T4pMember = new PageNavigation();
	PageNavigation T5pCopy = new PageNavigation();
	
	//END_FORM_VAR_DECL}}

	public pLibraryBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1Library.setName("T1Library");
			T1Library.setSession( parentApp.getSession() );
			T1Library.setQueryInfo("Library", "", "", "", false);
		       setRootDataSource(T1Library);
			T1Library.setPage(this);
			T1Library.setDataDependency(false, false);
		    T1Library.setAllowInsert(true);
		    T1Library.setAllowDelete(true);
		    T1Library.setAllowUpdate(true);
		    T1Library.setNumRowsPerPage(10);
		    T1Library.setPreFetchRowCount(false);
		    
		    T1Library.setMaxRowsPerFetch(16);
			T1Library.setSaveMode(T1Library.SAVE_IMMEDIATE);
		add(T1Library);
			T2Member.setName("T2Member");
			T2Member.setSession( parentApp.getSession() );
			T2Member.setQueryInfo("Member", "", "[FKLibrary] = [?PKLibrary]", "", true);
			T2Member.setPage(this);
			T2Member.setDataDependency(true, false);
		       T2Member.setDataSource(T1Library);
		    T2Member.setAllowInsert(false);
		    T2Member.setAllowDelete(false);
		    T2Member.setAllowUpdate(false);
		    T2Member.setNumRowsPerPage(10);
		    T2Member.setPreFetchRowCount(false);
		    
		    T2Member.setMaxRowsPerFetch(16);
			T2Member.setSaveMode(T2Member.SAVE_BUFFERED);
		add(T2Member);
			T3Copy.setName("T3Copy");
			T3Copy.setSession( parentApp.getSession() );
			T3Copy.setQueryInfo("Copy", "", "[FKLibrary] = [?PKLibrary]", "", true);
			T3Copy.setPage(this);
			T3Copy.setDataDependency(true, false);
		       T3Copy.setDataSource(T1Library);
		    T3Copy.setAllowInsert(false);
		    T3Copy.setAllowDelete(false);
		    T3Copy.setAllowUpdate(false);
		    T3Copy.setNumRowsPerPage(10);
		    T3Copy.setPreFetchRowCount(false);
		    
		    T3Copy.setMaxRowsPerFetch(16);
			T3Copy.setSaveMode(T3Copy.SAVE_BUFFERED);
		add(T3Copy);
			
			T4pMember.setSourcePage(this);
			T4pMember.setParentApp( parentApp );
		T4pMember.setSourceDataSource(T2Member);
		T4pMember.setTargetPageName(getPackageName() + ".pMember");
			T4pMember.setName("T4pMember");
			T4pMember.setInitialAddMode(false);
			T4pMember.setInitialQueryMode(false);
			T4pMember.setMetaQueryName("Member");
			T4pMember.setRelnWhere("");
			T4pMember.setDevWhere("");
			T4pMember.setRelnFromParent(true);
			T4pMember.setOrderBy("");
			T4pMember.setTargetPageCaption("Member");
			T4pMember.setDataDependency(true, false);
		      T4pMember.setTargetName("");
			
		add(T4pMember);
			
			T5pCopy.setSourcePage(this);
			T5pCopy.setParentApp( parentApp );
		T5pCopy.setSourceDataSource(T3Copy);
		T5pCopy.setTargetPageName(getPackageName() + ".pCopy");
			T5pCopy.setName("T5pCopy");
			T5pCopy.setInitialAddMode(false);
			T5pCopy.setInitialQueryMode(false);
			T5pCopy.setMetaQueryName("Copy");
			T5pCopy.setRelnWhere("");
			T5pCopy.setDevWhere("");
			T5pCopy.setRelnFromParent(true);
			T5pCopy.setOrderBy("");
			T5pCopy.setTargetPageCaption("Copy");
			T5pCopy.setDataDependency(true, false);
		      T5pCopy.setTargetName("");
			
		add(T5pCopy);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pLibrary.htm";
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