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
**  pEnterEvent
*/

//{{FORM_CLASS_DECL
public class pEnterEvent extends pEnterEventBase

//END_FORM_CLASS_DECL}}
{
	
	// This is the constructor for the generated form. This also constructs
	// all the controls on the form. Do not alter this code.
	//{{FORM_CLASS_CTOR
	public pEnterEvent ( PLSApp parentApp ) throws VSException, java.beans.PropertyVetoException {
		super(parentApp);
	//END_FORM_CLASS_CTOR}}
	}

	
	//{{EVENT_CODE
	
	//END_EVENT_CODE}}

	public void addListeners() {
	//{{EVENT_ADD_LISTENERS
	
	//END_EVENT_ADD_LISTENERS}}
	}

	//{{EVENT_ADAPTER_CODE
	
	//END_EVENT_ADAPTER_CODE}}

    	public HTMLDocumentSpec getDocumentSpecification() {    
            return getDefaultDocumentSpecification();
    	}
	
        public String getFileName() {
		    return getDefaultFileName();
	    }
    	public String getFileLocation() {
        	return getPageTemplatePath();
   	    }

	public void afterPageInitialize() {
		super.afterPageInitialize();
		//Write code here for initializing your own control
		//or creating new control.

	}


}