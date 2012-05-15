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
	
	//END_EVENT_CODE}}



	public void addListeners() {
		//{{EVENT_ADD_LISTENERS
		
		//END_EVENT_ADD_LISTENERS}}
	}

	//{{COMPONENT_RULES
		public static CreateEventImpl getNewObject(Session session, boolean makeDefaults)
		{
			return new CreateEventImpl(session, makeDefaults);
		}	
	
	//END_COMPONENT_RULES}}
	
}
