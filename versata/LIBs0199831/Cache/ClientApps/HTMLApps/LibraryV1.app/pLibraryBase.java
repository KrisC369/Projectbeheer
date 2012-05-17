//{{IMPORT_STMTS
package LIBs0199831.LibraryV1;
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
	public static Logger logger = Logger.getLogger("LibraryV1.LibraryV1");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1Library = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T2Member = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T3Copy = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T4Member = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T8Copy = new versata.pdx.html.DataSourcePDX();
	PageNavigation T6pMember = new PageNavigation();
	PageNavigation T10pCopy = new PageNavigation();
	PageNavigation T5pMember = new PageNavigation();
	PageNavigation T7pMember = new PageNavigation();
	PageNavigation T9pCopy = new PageNavigation();
	PageNavigation T11pCopy = new PageNavigation();
	
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
			T4Member.setName("T4Member");
			T4Member.setSession( parentApp.getSession() );
			T4Member.setQueryInfo("Member", "", "[FKLibrary] = [?PKLibrary]", "", true);
			T4Member.setPage(this);
			T4Member.setDataDependency(true, false);
		       T4Member.setDataSource(T1Library);
		    T4Member.setAllowInsert(false);
		    T4Member.setAllowDelete(false);
		    T4Member.setAllowUpdate(false);
		    T4Member.setNumRowsPerPage(10);
		    T4Member.setPreFetchRowCount(false);
		    
		    T4Member.setMaxRowsPerFetch(16);
			T4Member.setSaveMode(T4Member.SAVE_BUFFERED);
		add(T4Member);
			T8Copy.setName("T8Copy");
			T8Copy.setSession( parentApp.getSession() );
			T8Copy.setQueryInfo("Copy", "", "[FKLibrary] = [?PKLibrary]", "", true);
			T8Copy.setPage(this);
			T8Copy.setDataDependency(true, false);
		       T8Copy.setDataSource(T1Library);
		    T8Copy.setAllowInsert(false);
		    T8Copy.setAllowDelete(false);
		    T8Copy.setAllowUpdate(false);
		    T8Copy.setNumRowsPerPage(10);
		    T8Copy.setPreFetchRowCount(false);
		    
		    T8Copy.setMaxRowsPerFetch(16);
			T8Copy.setSaveMode(T8Copy.SAVE_BUFFERED);
		add(T8Copy);
			
			T6pMember.setSourcePage(this);
			T6pMember.setParentApp( parentApp );
		T6pMember.setSourceDataSource(T2Member);
		T6pMember.setTargetPageName(getPackageName() + ".pMember");
			T6pMember.setName("T6pMember");
			T6pMember.setInitialAddMode(false);
			T6pMember.setInitialQueryMode(false);
			T6pMember.setMetaQueryName("Member");
			T6pMember.setRelnWhere("");
			T6pMember.setDevWhere("");
			T6pMember.setRelnFromParent(true);
			T6pMember.setOrderBy("");
			T6pMember.setTargetPageCaption("T6Member");
			T6pMember.setDataDependency(true, false);
		      T6pMember.setTargetName("");
			
		add(T6pMember);
			
			T10pCopy.setSourcePage(this);
			T10pCopy.setParentApp( parentApp );
		T10pCopy.setSourceDataSource(T3Copy);
		T10pCopy.setTargetPageName(getPackageName() + ".pCopy");
			T10pCopy.setName("T10pCopy");
			T10pCopy.setInitialAddMode(false);
			T10pCopy.setInitialQueryMode(false);
			T10pCopy.setMetaQueryName("Copy");
			T10pCopy.setRelnWhere("");
			T10pCopy.setDevWhere("");
			T10pCopy.setRelnFromParent(true);
			T10pCopy.setOrderBy("");
			T10pCopy.setTargetPageCaption("T10Copy");
			T10pCopy.setDataDependency(true, false);
		      T10pCopy.setTargetName("");
			
		add(T10pCopy);
			
			T5pMember.setSourcePage(this);
			T5pMember.setParentApp( parentApp );
		T5pMember.setSourceDataSource(T4Member);
		T5pMember.setTargetPageName(getPackageName() + ".pMember");
			T5pMember.setName("T5pMember");
			T5pMember.setInitialAddMode(false);
			T5pMember.setInitialQueryMode(false);
			T5pMember.setMetaQueryName("Member");
			T5pMember.setRelnWhere("");
			T5pMember.setDevWhere("");
			T5pMember.setRelnFromParent(true);
			T5pMember.setOrderBy("");
			T5pMember.setTargetPageCaption("T5Member");
			T5pMember.setDataDependency(true, false);
		      T5pMember.setTargetName("");
			
		add(T5pMember);
			
			T7pMember.setSourcePage(this);
			T7pMember.setParentApp( parentApp );
		T7pMember.setSourceDataSource(T1Library);
		T7pMember.setTargetPageName(getPackageName() + ".pMember");
			T7pMember.setName("T7pMember");
			T7pMember.setInitialAddMode(false);
			T7pMember.setInitialQueryMode(false);
			T7pMember.setMetaQueryName("Member");
			T7pMember.setRelnWhere("[FKLibrary] = [?PKLibrary]");
			T7pMember.setDevWhere("");
			T7pMember.setRelnFromParent(true);
			T7pMember.setOrderBy("");
			T7pMember.setTargetPageCaption("T7Member");
			T7pMember.setDataDependency(true, false);
		      T7pMember.setTargetName("");
			
		add(T7pMember);
			
			T9pCopy.setSourcePage(this);
			T9pCopy.setParentApp( parentApp );
		T9pCopy.setSourceDataSource(T8Copy);
		T9pCopy.setTargetPageName(getPackageName() + ".pCopy");
			T9pCopy.setName("T9pCopy");
			T9pCopy.setInitialAddMode(false);
			T9pCopy.setInitialQueryMode(false);
			T9pCopy.setMetaQueryName("Copy");
			T9pCopy.setRelnWhere("");
			T9pCopy.setDevWhere("");
			T9pCopy.setRelnFromParent(true);
			T9pCopy.setOrderBy("");
			T9pCopy.setTargetPageCaption("T9Copy");
			T9pCopy.setDataDependency(true, false);
		      T9pCopy.setTargetName("");
			
		add(T9pCopy);
			
			T11pCopy.setSourcePage(this);
			T11pCopy.setParentApp( parentApp );
		T11pCopy.setSourceDataSource(T1Library);
		T11pCopy.setTargetPageName(getPackageName() + ".pCopy");
			T11pCopy.setName("T11pCopy");
			T11pCopy.setInitialAddMode(false);
			T11pCopy.setInitialQueryMode(false);
			T11pCopy.setMetaQueryName("Copy");
			T11pCopy.setRelnWhere("[FKLibrary] = [?PKLibrary]");
			T11pCopy.setDevWhere("");
			T11pCopy.setRelnFromParent(true);
			T11pCopy.setOrderBy("");
			T11pCopy.setTargetPageCaption("T11Copy");
			T11pCopy.setDataDependency(true, false);
		      T11pCopy.setTargetName("");
			
		add(T11pCopy);
		
		
		
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