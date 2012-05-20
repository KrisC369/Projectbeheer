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
**  pReserveEvent
*/

abstract class pReserveEventBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1ReserveEvent = new versata.pdx.html.DataSourcePDX();
	Pick T2Reservation = new Pick();
	Pick T3Copy = new Pick();
	Pick T4Member = new Pick();
	
	//END_FORM_VAR_DECL}}

	public pReserveEventBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1ReserveEvent.setName("T1ReserveEvent");
			T1ReserveEvent.setSession( parentApp.getSession() );
			T1ReserveEvent.setQueryInfo("ReserveEvent", "", "", "", false);
		       setRootDataSource(T1ReserveEvent);
			T1ReserveEvent.setPage(this);
			T1ReserveEvent.setDataDependency(false, false);
		    T1ReserveEvent.setAllowInsert(true);
		    T1ReserveEvent.setAllowDelete(true);
		    T1ReserveEvent.setAllowUpdate(true);
		    T1ReserveEvent.setNumRowsPerPage(10);
		    T1ReserveEvent.setPreFetchRowCount(false);
		    
		    T1ReserveEvent.setMaxRowsPerFetch(16);
			T1ReserveEvent.setSaveMode(T1ReserveEvent.SAVE_IMMEDIATE);
		add(T1ReserveEvent);
		    				T2Reservation.Init("T2Reservation", T1ReserveEvent, "Reservation", "", "", "[PKReservation] = [?FKReservation]", "", "PKReservation, FKMember, FKCopy, StartDate, EndDate, ReservationState",getPackageName() + ".genericPick", this);
			T2Reservation.setParentTableAlias("");
		        
		add(T2Reservation);
		    				T3Copy.Init("T3Copy", T1ReserveEvent, "Copy", "", "", "[PKCopy] = [?FKCopy]", "", "PKCopy, FKLibrary, numberOfLoans, Title, Author, ISBN, CopyState, CurrentLoans, CurrentReservations",getPackageName() + ".genericPick", this);
			T3Copy.setParentTableAlias("");
		        
		add(T3Copy);
		    				T4Member.Init("T4Member", T1ReserveEvent, "Member", "", "", "[PKMember] = [?FKMember]", "", "PKMember, FKLibrary, FirstName, LastName, Address, City, Country, MobileNumber, MemberState, CurrentLoans, CurrentReservations",getPackageName() + ".genericPick", this);
			T4Member.setParentTableAlias("");
		        
		add(T4Member);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pReserveEvent.htm";
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