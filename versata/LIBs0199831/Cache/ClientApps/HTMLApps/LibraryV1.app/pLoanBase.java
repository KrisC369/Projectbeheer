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
**  pLoan
*/

abstract class pLoanBase extends versata.pdx.html.VSPagePDX implements java.io.Serializable
{
	// Declarations for instance variables used in the form
	//{{FORM_VAR_DECL
	public static Logger logger = Logger.getLogger("LibraryV1.LibraryV1");
	public static HTMLDocumentSpec docSpec = null;
	versata.pdx.html.DataSourcePDX T1Loan = new versata.pdx.html.DataSourcePDX();
	Pick T4Member = new Pick();
	Pick T5Copy = new Pick();
	PageNavigation T2pMember = new PageNavigation();
	PageNavigation T3pCopy = new PageNavigation();
	
	//END_FORM_VAR_DECL}}

	public pLoanBase (PLSApp parentApp) throws VSException, java.beans.PropertyVetoException {
		super();
		setParentApp(parentApp);
		//{{FORM_OBJ_CTOR
		beforePageInitialize();
		
		
			T1Loan.setName("T1Loan");
			T1Loan.setSession( parentApp.getSession() );
			T1Loan.setQueryInfo("Loan", "", "", "", false);
		       setRootDataSource(T1Loan);
			T1Loan.setPage(this);
			T1Loan.setDataDependency(false, false);
		    T1Loan.setAllowInsert(true);
		    T1Loan.setAllowDelete(true);
		    T1Loan.setAllowUpdate(true);
		    T1Loan.setNumRowsPerPage(10);
		    T1Loan.setPreFetchRowCount(false);
		    
		    T1Loan.setMaxRowsPerFetch(16);
			T1Loan.setSaveMode(T1Loan.SAVE_IMMEDIATE);
		add(T1Loan);
		    				T4Member.Init("T4Member", T1Loan, "Member", "", "", "[PKMember] = [?FKMember]", "", "PKMember, FKLibrary, FirstName, LastName, Address, City, Country, MobileNumber, MemberState",getPackageName() + ".genericPick", this);
			T4Member.setParentTableAlias("");
		        
		add(T4Member);
		    				T5Copy.Init("T5Copy", T1Loan, "Copy", "", "", "[PKCopy] = [?FKCopy]", "", "PKCopy, FKLibrary, numberOfLoans, Title, Author, ISBN, CopyState",getPackageName() + ".genericPick", this);
			T5Copy.setParentTableAlias("");
		        
		add(T5Copy);
			
			T2pMember.setSourcePage(this);
			T2pMember.setParentApp( parentApp );
		T2pMember.setSourceDataSource(T1Loan);
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
		T3pCopy.setSourceDataSource(T1Loan);
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
			T3pCopy.setDataDependency(false, true);
		      T3pCopy.setTargetName("");
			
		add(T3pCopy);
		
		
		
		addListeners();
		afterPageInitialize();
		
		//END_FORM_OBJ_CTOR}}
	}
		
	
	//{{FORM_GETFILENAME
	    public String getDefaultFileName() {
			return "pLoan.htm";
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