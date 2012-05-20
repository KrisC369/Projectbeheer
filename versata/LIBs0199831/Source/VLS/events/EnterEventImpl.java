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
**  EnterEvent
*/

//{{COMPONENT_RULES_CLASS_DECL
public class EnterEventImpl extends  EnterEventBaseImpl


//END_COMPONENT_RULES_CLASS_DECL}}
{
	//{{COMP_CLASS_CTOR
	public EnterEventImpl (){
		super();
	}
	
	public EnterEventImpl(Session session, boolean makeDefaults)
	{
		super(session, makeDefaults);
	
	
	
	
	//END_COMP_CLASS_CTOR}}

	}

	//{{EVENT_CODE
	
//{{COMP_EVENT_beforeInsert
public void beforeInsert(DataObject obj, Response response)
{
	//Write Event Code below this line
	
	EnterEventImpl enterEv = (EnterEventImpl) obj;
	MemberImpl member = new MemberImpl(getSession(),true);
	member.setFirstName(enterEv.getFirstName());
	member.setLastName(enterEv.getLastName());
	member.setAddress(enterEv.getAddress());
	member.setCity(enterEv.getCity());
	member.setCountry(enterEv.getCountry());
	member.setMobileNumber(enterEv.getMobileNumber());
	member.setMemberState(1);
	member.setFKLibrary(enterEv.getFKLibrary());
	member.executeRules();
	enterEv.setFKMember(member.getPKMember());
	

}
//END_COMP_EVENT_beforeInsert}}

	//END_EVENT_CODE}}



	public void addListeners() {
		//{{EVENT_ADD_LISTENERS
		
	addRuleEventListener(this);
		//END_EVENT_ADD_LISTENERS}}
	}

	//{{COMPONENT_RULES
		public static EnterEventImpl getNewObject(Session session, boolean makeDefaults)
		{
			return new EnterEventImpl(session, makeDefaults);
		}	
	
	//END_COMPONENT_RULES}}
	
}

