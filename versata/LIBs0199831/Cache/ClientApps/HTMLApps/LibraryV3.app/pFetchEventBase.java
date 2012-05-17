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
**  pFetchEvent
*/

abstract class pFetchEventBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1FetchEvent = new versata.pdx.html.DataSourcePDX();
	Pick T2Reservation = new Pick();
	Pick T3Loan = new Pick();
	
	//END_FORM_VAR_DECL}}

	public pFetchEventBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1FetchEvent.setName("T1FetchEvent");
			T1FetchEvent.setSession( parentApp.getSession() );
			T1FetchEvent.setQueryInfo("FetchEvent", "", "", "", false);
		       setRootDataSource(T1FetchEvent);
			T1FetchEvent.setPage(this);
			T1FetchEvent.setDataDependency(false, false);
		    T1FetchEvent.setAllowInsert(true);
		    T1FetchEvent.setAllowDelete(true);
		    T1FetchEvent.setAllowUpdate(true);
		    T1FetchEvent.setNumRowsPerPage(10);
		    T1FetchEvent.setPreFetchRowCount(false);
		    
		    T1FetchEvent.setMaxRowsPerFetch(16);
			T1FetchEvent.setSaveMode(T1FetchEvent.SAVE_IMMEDIATE);
		add(T1FetchEvent);
		    				T2Reservation.Init("T2Reservation", T1FetchEvent, "Reservation", "", "", "[PKReservation] = [?FKReservation]", "", "PKReservation, FKMember, FKCopy, StartDate, EndDate, ReservationState",getPackageName() + ".genericPick", this);
			T2Reservation.setParentTableAlias("");
		        
		add(T2Reservation);
		    				T3Loan.Init("T3Loan", T1FetchEvent, "Loan", "", "", "[PKLoan] = [?FKLoan]", "", "PKLoan, FKMember, FKCopy, StartDate, EndDate, LoanState",getPackageName() + ".genericPick", this);
			T3Loan.setParentTableAlias("");
		        
		add(T3Loan);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pFetchEvent.htm";
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