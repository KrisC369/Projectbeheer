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
**  pReservation
*/

abstract class pReservationBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1Reservation = new versata.pdx.html.DataSourcePDX();
	Pick T2Copy = new Pick();
	Pick T3Member = new Pick();
	versata.pdx.html.DataSourcePDX T4ReserveEvent = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T6CancelEvent = new versata.pdx.html.DataSourcePDX();
	versata.pdx.html.DataSourcePDX T8FetchEvent = new versata.pdx.html.DataSourcePDX();
	PageNavigation T5pReserveEvent = new PageNavigation();
	PageNavigation T7pCancelEvent = new PageNavigation();
	PageNavigation T9pFetchEvent = new PageNavigation();
	
	//END_FORM_VAR_DECL}}

	public pReservationBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1Reservation.setName("T1Reservation");
			T1Reservation.setSession( parentApp.getSession() );
			T1Reservation.setQueryInfo("Reservation", "", "", "", false);
		       setRootDataSource(T1Reservation);
			T1Reservation.setPage(this);
			T1Reservation.setDataDependency(false, false);
		    T1Reservation.setAllowInsert(true);
		    T1Reservation.setAllowDelete(true);
		    T1Reservation.setAllowUpdate(true);
		    T1Reservation.setNumRowsPerPage(10);
		    T1Reservation.setPreFetchRowCount(false);
		    
		    T1Reservation.setMaxRowsPerFetch(16);
			T1Reservation.setSaveMode(T1Reservation.SAVE_IMMEDIATE);
		add(T1Reservation);
		    				T2Copy.Init("T2Copy", T1Reservation, "Copy", "", "", "[PKCopy] = [?FKCopy]", "", "PKCopy, FKLibrary, numberOfLoans, Title, Author, ISBN, CopyState, CurrentLoans, CurrentReservations",getPackageName() + ".genericPick", this);
			T2Copy.setParentTableAlias("");
		        
		add(T2Copy);
		    				T3Member.Init("T3Member", T1Reservation, "Member", "", "", "[PKMember] = [?FKMember]", "", "PKMember, FKLibrary, FirstName, LastName, Address, City, Country, MobileNumber, MemberState, CurrentLoans, CurrentReservations",getPackageName() + ".genericPick", this);
			T3Member.setParentTableAlias("");
		        
		add(T3Member);
			T4ReserveEvent.setName("T4ReserveEvent");
			T4ReserveEvent.setSession( parentApp.getSession() );
			T4ReserveEvent.setQueryInfo("ReserveEvent", "", "[FKReservation] = [?PKReservation]", "", true);
			T4ReserveEvent.setPage(this);
			T4ReserveEvent.setDataDependency(false, false);
		       T4ReserveEvent.setDataSource(T1Reservation);
		    T4ReserveEvent.setAllowInsert(false);
		    T4ReserveEvent.setAllowDelete(false);
		    T4ReserveEvent.setAllowUpdate(false);
		    T4ReserveEvent.setNumRowsPerPage(10);
		    T4ReserveEvent.setPreFetchRowCount(false);
		    
		    T4ReserveEvent.setMaxRowsPerFetch(16);
			T4ReserveEvent.setSaveMode(T4ReserveEvent.SAVE_BUFFERED);
		add(T4ReserveEvent);
			T6CancelEvent.setName("T6CancelEvent");
			T6CancelEvent.setSession( parentApp.getSession() );
			T6CancelEvent.setQueryInfo("CancelEvent", "", "[FKReservation] = [?PKReservation]", "", true);
			T6CancelEvent.setPage(this);
			T6CancelEvent.setDataDependency(false, false);
		       T6CancelEvent.setDataSource(T1Reservation);
		    T6CancelEvent.setAllowInsert(false);
		    T6CancelEvent.setAllowDelete(false);
		    T6CancelEvent.setAllowUpdate(false);
		    T6CancelEvent.setNumRowsPerPage(10);
		    T6CancelEvent.setPreFetchRowCount(false);
		    
		    T6CancelEvent.setMaxRowsPerFetch(16);
			T6CancelEvent.setSaveMode(T6CancelEvent.SAVE_BUFFERED);
		add(T6CancelEvent);
			T8FetchEvent.setName("T8FetchEvent");
			T8FetchEvent.setSession( parentApp.getSession() );
			T8FetchEvent.setQueryInfo("FetchEvent", "", "[FKReservation] = [?PKReservation]", "", true);
			T8FetchEvent.setPage(this);
			T8FetchEvent.setDataDependency(false, false);
		       T8FetchEvent.setDataSource(T1Reservation);
		    T8FetchEvent.setAllowInsert(false);
		    T8FetchEvent.setAllowDelete(false);
		    T8FetchEvent.setAllowUpdate(false);
		    T8FetchEvent.setNumRowsPerPage(10);
		    T8FetchEvent.setPreFetchRowCount(false);
		    
		    T8FetchEvent.setMaxRowsPerFetch(16);
			T8FetchEvent.setSaveMode(T8FetchEvent.SAVE_BUFFERED);
		add(T8FetchEvent);
			
			T5pReserveEvent.setSourcePage(this);
			T5pReserveEvent.setParentApp( parentApp );
		T5pReserveEvent.setSourceDataSource(T4ReserveEvent);
		T5pReserveEvent.setTargetPageName(getPackageName() + ".pReserveEvent");
			T5pReserveEvent.setName("T5pReserveEvent");
			T5pReserveEvent.setInitialAddMode(false);
			T5pReserveEvent.setInitialQueryMode(false);
			T5pReserveEvent.setMetaQueryName("ReserveEvent");
			T5pReserveEvent.setRelnWhere("");
			T5pReserveEvent.setDevWhere("");
			T5pReserveEvent.setRelnFromParent(true);
			T5pReserveEvent.setOrderBy("");
			T5pReserveEvent.setTargetPageCaption("ReserveEvent");
			T5pReserveEvent.setDataDependency(false, false);
		      T5pReserveEvent.setTargetName("");
			
		add(T5pReserveEvent);
			
			T7pCancelEvent.setSourcePage(this);
			T7pCancelEvent.setParentApp( parentApp );
		T7pCancelEvent.setSourceDataSource(T6CancelEvent);
		T7pCancelEvent.setTargetPageName(getPackageName() + ".pCancelEvent");
			T7pCancelEvent.setName("T7pCancelEvent");
			T7pCancelEvent.setInitialAddMode(false);
			T7pCancelEvent.setInitialQueryMode(false);
			T7pCancelEvent.setMetaQueryName("CancelEvent");
			T7pCancelEvent.setRelnWhere("");
			T7pCancelEvent.setDevWhere("");
			T7pCancelEvent.setRelnFromParent(true);
			T7pCancelEvent.setOrderBy("");
			T7pCancelEvent.setTargetPageCaption("CancelEvent");
			T7pCancelEvent.setDataDependency(false, false);
		      T7pCancelEvent.setTargetName("");
			
		add(T7pCancelEvent);
			
			T9pFetchEvent.setSourcePage(this);
			T9pFetchEvent.setParentApp( parentApp );
		T9pFetchEvent.setSourceDataSource(T8FetchEvent);
		T9pFetchEvent.setTargetPageName(getPackageName() + ".pFetchEvent");
			T9pFetchEvent.setName("T9pFetchEvent");
			T9pFetchEvent.setInitialAddMode(false);
			T9pFetchEvent.setInitialQueryMode(false);
			T9pFetchEvent.setMetaQueryName("FetchEvent");
			T9pFetchEvent.setRelnWhere("");
			T9pFetchEvent.setDevWhere("");
			T9pFetchEvent.setRelnFromParent(true);
			T9pFetchEvent.setOrderBy("");
			T9pFetchEvent.setTargetPageCaption("FetchEvent");
			T9pFetchEvent.setDataDependency(false, false);
		      T9pFetchEvent.setTargetName("");
			
		add(T9pFetchEvent);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pReservation.htm";
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