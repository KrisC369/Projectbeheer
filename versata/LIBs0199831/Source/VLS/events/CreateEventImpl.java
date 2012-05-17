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
**  CreateEvent
*/

//{{COMPONENT_RULES_CLASS_DECL
public class CreateEventImpl extends  CreateEventBaseImpl


//END_COMPONENT_RULES_CLASS_DECL}}
{
	//{{COMP_CLASS_CTOR
	public CreateEventImpl (){
		super();
	}
	
	public CreateEventImpl(Session session, boolean makeDefaults)
	{
		super(session, makeDefaults);
	
	
	
	
	//END_COMP_CLASS_CTOR}}

	}

	//{{EVENT_CODE
	
//{{COMP_EVENT_beforeInsert
public void beforeInsert(DataObject obj, Response response)
{
	//Write Event Code below this line
	CreateEventImpl createEv = (CreateEventImpl) obj;
	LibraryImpl library = new LibraryImpl(getSession(),true);
	library.setName(createEv.getName());
	library.setCity(createEv.getCity());
	library.setPhoneNumber(createEv.getPhoneNumber());
	library.setWebAddress(createEv.getWebAddress());
	library.setLibraryState(1);
	createEv.setFKLibrary(library.getPKLibrary());
	library.executeRules();
}
//END_COMP_EVENT_beforeInsert}}

	//END_EVENT_CODE}}



	public void addListeners() {
		//{{EVENT_ADD_LISTENERS
		
	addRuleEventListener(this);
		//END_EVENT_ADD_LISTENERS}}
	}

	//{{COMPONENT_RULES
		public static CreateEventImpl getNewObject(Session session, boolean makeDefaults)
		{
			return new CreateEventImpl(session, makeDefaults);
		}	
	
	//END_COMPONENT_RULES}}
	
}

