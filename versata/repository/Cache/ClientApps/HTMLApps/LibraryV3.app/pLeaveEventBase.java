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
**  pLeaveEvent
*/

abstract class pLeaveEventBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1LeaveEvent = new versata.pdx.html.DataSourcePDX();
	Pick T2Member = new Pick();
	
	//END_FORM_VAR_DECL}}

	public pLeaveEventBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1LeaveEvent.setName("T1LeaveEvent");
			T1LeaveEvent.setSession( parentApp.getSession() );
			T1LeaveEvent.setQueryInfo("LeaveEvent", "", "", "", false);
		       setRootDataSource(T1LeaveEvent);
			T1LeaveEvent.setPage(this);
			T1LeaveEvent.setDataDependency(false, false);
		    T1LeaveEvent.setAllowInsert(true);
		    T1LeaveEvent.setAllowDelete(true);
		    T1LeaveEvent.setAllowUpdate(true);
		    T1LeaveEvent.setNumRowsPerPage(10);
		    T1LeaveEvent.setPreFetchRowCount(false);
		    
		    T1LeaveEvent.setMaxRowsPerFetch(16);
			T1LeaveEvent.setSaveMode(T1LeaveEvent.SAVE_IMMEDIATE);
		add(T1LeaveEvent);
		    				T2Member.Init("T2Member", T1LeaveEvent, "Member", "", "", "[PKMember] = [?FKMember]", "", "PKMember, FKLibrary, FirstName, LastName, Address, City, Country, MobileNumber, MemberState, CurrentLoans, CurrentReservations",getPackageName() + ".genericPick", this);
			T2Member.setParentTableAlias("");
		        
		add(T2Member);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pLeaveEvent.htm";
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