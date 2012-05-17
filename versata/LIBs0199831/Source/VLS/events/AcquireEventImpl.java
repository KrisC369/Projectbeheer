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
**  AcquireEvent
*/

//{{COMPONENT_RULES_CLASS_DECL
public class AcquireEventImpl extends  AcquireEventBaseImpl


//END_COMPONENT_RULES_CLASS_DECL}}
{
	//{{COMP_CLASS_CTOR
	public AcquireEventImpl (){
		super();
	}
	
	public AcquireEventImpl(Session session, boolean makeDefaults)
	{
		super(session, makeDefaults);
	
	
	
	
	//END_COMP_CLASS_CTOR}}

	}

	//{{EVENT_CODE
	
//{{COMP_EVENT_beforeInsert
public void beforeInsert(DataObject obj, Response response)
{
	//Write Event Code below this line
	AcquireEventImpl AcquireEvent = (AcquireEventImpl) obj;
	CopyImpl copy = new CopyImpl(getSession(),true);
	copy.setTitle(AcquireEvent.getTitle());
	copy.setAuthor(AcquireEvent.getAuthor());
	copy.setISBN(AcquireEvent.getISBN());
	copy.setCopyState(1);
	copy.executeRules();
	AcquireEvent.setFKCopy(copy.getPKCopy());
}
//END_COMP_EVENT_beforeInsert}}

	//END_EVENT_CODE}}



	public void addListeners() {
		//{{EVENT_ADD_LISTENERS
		
	addRuleEventListener(this);
		//END_EVENT_ADD_LISTENERS}}
	}

	//{{COMPONENT_RULES
		public static AcquireEventImpl getNewObject(Session session, boolean makeDefaults)
		{
			return new AcquireEventImpl(session, makeDefaults);
		}	
	
	//END_COMPONENT_RULES}}
	
}

