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
**  pSellEvent
*/

abstract class pSellEventBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV3.LibraryV3");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1SellEvent = new versata.pdx.html.DataSourcePDX();
	Pick T2Copy = new Pick();
	
	//END_FORM_VAR_DECL}}

	public pSellEventBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1SellEvent.setName("T1SellEvent");
			T1SellEvent.setSession( parentApp.getSession() );
			T1SellEvent.setQueryInfo("SellEvent", "", "", "", false);
		       setRootDataSource(T1SellEvent);
			T1SellEvent.setPage(this);
			T1SellEvent.setDataDependency(false, false);
		    T1SellEvent.setAllowInsert(true);
		    T1SellEvent.setAllowDelete(true);
		    T1SellEvent.setAllowUpdate(true);
		    T1SellEvent.setNumRowsPerPage(10);
		    T1SellEvent.setPreFetchRowCount(false);
		    
		    T1SellEvent.setMaxRowsPerFetch(16);
			T1SellEvent.setSaveMode(T1SellEvent.SAVE_IMMEDIATE);
		add(T1SellEvent);
		    				T2Copy.Init("T2Copy", T1SellEvent, "Copy", "", "", "[PKCopy] = [?FKCopy]", "", "PKCopy, FKLibrary, numberOfLoans, Title, Author, ISBN, CopyState, CurrentLoans, CurrentReservations",getPackageName() + ".genericPick", this);
			T2Copy.setParentTableAlias("");
		        
		add(T2Copy);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pSellEvent.htm";
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