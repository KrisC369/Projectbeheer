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
**  pMember
*/

abstract class pMemberBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV2.LibraryV2");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1Member = new versata.pdx.html.DataSourcePDX();
	Pick T2Library = new Pick();
	
	//END_FORM_VAR_DECL}}

	public pMemberBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1Member.setName("T1Member");
			T1Member.setSession( parentApp.getSession() );
			T1Member.setQueryInfo("Member", "", "", "", false);
		       setRootDataSource(T1Member);
			T1Member.setPage(this);
			T1Member.setDataDependency(false, false);
		    T1Member.setAllowInsert(true);
		    T1Member.setAllowDelete(true);
		    T1Member.setAllowUpdate(true);
		    T1Member.setNumRowsPerPage(10);
		    T1Member.setPreFetchRowCount(false);
		    
		    T1Member.setMaxRowsPerFetch(16);
			T1Member.setSaveMode(T1Member.SAVE_IMMEDIATE);
		add(T1Member);
		    				T2Library.Init("T2Library", T1Member, "Library", "", "", "[PKLibrary] = [?FKLibrary]", "", "PKLibrary, amountOfMembers, amountOfCopies, Name, City, PhoneNumber, WebAddress, LibraryState",getPackageName() + ".genericPick", this);
			T2Library.setParentTableAlias("");
		        
		add(T2Library);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pMember.htm";
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