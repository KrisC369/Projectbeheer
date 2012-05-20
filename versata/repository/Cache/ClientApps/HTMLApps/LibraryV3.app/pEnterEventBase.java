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
**  pEnterEvent
*/

abstract class pEnterEventBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1EnterEvent = new versata.pdx.html.DataSourcePDX();
	Pick T2Member = new Pick();
	
	//END_FORM_VAR_DECL}}

	public pEnterEventBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1EnterEvent.setName("T1EnterEvent");
			T1EnterEvent.setSession( parentApp.getSession() );
			T1EnterEvent.setQueryInfo("EnterEvent", "", "", "", false);
		       setRootDataSource(T1EnterEvent);
			T1EnterEvent.setPage(this);
			T1EnterEvent.setDataDependency(false, false);
		    T1EnterEvent.setAllowInsert(true);
		    T1EnterEvent.setAllowDelete(true);
		    T1EnterEvent.setAllowUpdate(true);
		    T1EnterEvent.setNumRowsPerPage(10);
		    T1EnterEvent.setPreFetchRowCount(false);
		    
		    T1EnterEvent.setMaxRowsPerFetch(16);
			T1EnterEvent.setSaveMode(T1EnterEvent.SAVE_IMMEDIATE);
		add(T1EnterEvent);
		    				T2Member.Init("T2Member", T1EnterEvent, "Member", "", "", "[PKMember] = [?FKMember]", "", "PKMember, FKLibrary, FirstName, LastName, Address, City, Country, MobileNumber, MemberState, CurrentLoans, CurrentReservations",getPackageName() + ".genericPick", this);
			T2Member.setParentTableAlias("");
		        
		add(T2Member);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pEnterEvent.htm";
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