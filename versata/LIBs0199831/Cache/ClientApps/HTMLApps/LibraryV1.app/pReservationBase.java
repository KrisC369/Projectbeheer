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
**  pReservation
*/

abstract class pReservationBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV1.LibraryV1");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1Reservation = new versata.pdx.html.DataSourcePDX();
	Pick T4Copy = new Pick();
	Pick T5Member = new Pick();
	PageNavigation T2pMember = new PageNavigation();
	PageNavigation T3pCopy = new PageNavigation();
	
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
		    				T4Copy.Init("T4Copy", T1Reservation, "Copy", "", "", "[PKCopy] = [?FKCopy]", "", "PKCopy, FKLibrary, numberOfLoans, Title, Author, ISBN, CopyState",getPackageName() + ".genericPick", this);
			T4Copy.setParentTableAlias("");
		        
		add(T4Copy);
		    				T5Member.Init("T5Member", T1Reservation, "Member", "", "", "[PKMember] = [?FKMember]", "", "PKMember, FKLibrary, FirstName, LastName, Address, City, Country, MobileNumber, MemberState",getPackageName() + ".genericPick", this);
			T5Member.setParentTableAlias("");
		        
		add(T5Member);
			
			T2pMember.setSourcePage(this);
			T2pMember.setParentApp( parentApp );
		T2pMember.setSourceDataSource(T1Reservation);
		T2pMember.setTargetPageName(getPackageName() + ".pMember");
			T2pMember.setName("T2pMember");
			T2pMember.setInitialAddMode(false);
			T2pMember.setInitialQueryMode(false);
			T2pMember.setMetaQueryName("Member");
			T2pMember.setRelnWhere("[PKMember] = [?FKMember]");
			T2pMember.setDevWhere("");
			T2pMember.setRelnFromParent(false);
			T2pMember.setOrderBy("");
			T2pMember.setTargetPageCaption("Member");
			T2pMember.setDataDependency(false, false);
		      T2pMember.setTargetName("");
			
		add(T2pMember);
			
			T3pCopy.setSourcePage(this);
			T3pCopy.setParentApp( parentApp );
		T3pCopy.setSourceDataSource(T1Reservation);
		T3pCopy.setTargetPageName(getPackageName() + ".pCopy");
			T3pCopy.setName("T3pCopy");
			T3pCopy.setInitialAddMode(false);
			T3pCopy.setInitialQueryMode(false);
			T3pCopy.setMetaQueryName("Copy");
			T3pCopy.setRelnWhere("[PKCopy] = [?FKCopy]");
			T3pCopy.setDevWhere("");
			T3pCopy.setRelnFromParent(false);
			T3pCopy.setOrderBy("");
			T3pCopy.setTargetPageCaption("Copy");
			T3pCopy.setDataDependency(false, false);
		      T3pCopy.setTargetName("");
			
		add(T3pCopy);
		
		
		
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