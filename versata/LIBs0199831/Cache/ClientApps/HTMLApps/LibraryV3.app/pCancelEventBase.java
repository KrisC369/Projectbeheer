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
**  pCancelEvent
*/

abstract class pCancelEventBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1CancelEvent = new versata.pdx.html.DataSourcePDX();
	Pick T2Reservation = new Pick();
	
	//END_FORM_VAR_DECL}}

	public pCancelEventBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1CancelEvent.setName("T1CancelEvent");
			T1CancelEvent.setSession( parentApp.getSession() );
			T1CancelEvent.setQueryInfo("CancelEvent", "", "", "", false);
		       setRootDataSource(T1CancelEvent);
			T1CancelEvent.setPage(this);
			T1CancelEvent.setDataDependency(false, false);
		    T1CancelEvent.setAllowInsert(true);
		    T1CancelEvent.setAllowDelete(true);
		    T1CancelEvent.setAllowUpdate(true);
		    T1CancelEvent.setNumRowsPerPage(10);
		    T1CancelEvent.setPreFetchRowCount(false);
		    
		    T1CancelEvent.setMaxRowsPerFetch(16);
			T1CancelEvent.setSaveMode(T1CancelEvent.SAVE_IMMEDIATE);
		add(T1CancelEvent);
		    				T2Reservation.Init("T2Reservation", T1CancelEvent, "Reservation", "", "", "[PKReservation] = [?FKReservation]", "", "PKReservation, FKMember, FKCopy, StartDate, EndDate, ReservationState",getPackageName() + ".genericPick", this);
			T2Reservation.setParentTableAlias("");
		        
		add(T2Reservation);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pCancelEvent.htm";
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