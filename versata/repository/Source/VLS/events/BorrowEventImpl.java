//{{COMPONENT_IMPORT_STMTS
package LIBs0199831;
import java.util.Enumeration;
import java.util.Vector;
import versata.common.*;
import versata.common.vstrace.*;
import versata.vls.*;
import java.util.*;
import java.math.*;
import versata.vls.cache.*;
import com.versata.util.logging.*;

//END_COMPONENT_IMPORT_STMTS}}

/*
**  BorrowEvent
*/

//{{COMPONENT_RULES_CLASS_DECL
public class BorrowEventImpl extends  BorrowEventBaseImpl


//END_COMPONENT_RULES_CLASS_DECL}}
{
	//{{COMP_CLASS_CTOR
	public BorrowEventImpl (){
		super();
	}
	
	public BorrowEventImpl(Session session, boolean makeDefaults)
	{
		super(session, makeDefaults);
	
	
	
	
	//END_COMP_CLASS_CTOR}}

	}

	//{{EVENT_CODE
	
//{{COMP_EVENT_beforeInsert
public void beforeInsert(DataObject obj, Response response)
{
	//Write Event Code below this line
	BorrowEventImpl borrowEv = (BorrowEventImpl) obj;
	LoanImpl loan = new LoanImpl(getSession(), true);
	loan.setFKMember(borrowEv.getFKMember());
	loan.setFKCopy(borrowEv.getFKCopy());
	loan.setStartDate(getDate());
	loan.setLoanState(1);
	loan.executeRules();
	borrowEv.setFKLoan(loan.getPKLoan());
}
//END_COMP_EVENT_beforeInsert}}

	//END_EVENT_CODE}}



	public void addListeners() {
		//{{EVENT_ADD_LISTENERS
		
	addRuleEventListener(this);
		//END_EVENT_ADD_LISTENERS}}
	}

	//{{COMPONENT_RULES
		public static BorrowEventImpl getNewObject(Session session, boolean makeDefaults)
		{
			return new BorrowEventImpl(session, makeDefaults);
		}	
	
	//END_COMPONENT_RULES}}
	
}

