//{{APP_IMPORT_STMTS
package LIBs0199831.LibraryV2;
import versata.vfc.html.*;
import versata.vfc.html.common.*;
import versata.common.*;
import versata.common.vstrace.*;
import versata.vls.*;
import java.util.*;
import java.math.BigDecimal;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.versata.util.logging.*;
//END_APP_IMPORT_STMTS}}

public abstract class LibraryV2Base extends PLSApp implements java.io.Serializable
{
	public static Logger logger = Logger.getLogger("LIBs0199831.LibraryV2");
	    
	public synchronized PLSResponse doAction(PLSRequest preq)
	{
		PLSResponse response = new PLSResponse();
		VSPage p = null;
		p = getPageFromList(preq.getParameter("page_id"));
		try
		{
			response.responseValue = p.doAction(preq);
	    		response.metaContentType = p.getContentType();	
		}
		catch (Exception ex)
		{
			Util.logWarning(logger, ex);
			
			response.responseValue = ex.toString(); //FIXME need to improve error handling
			response.metaContentType = p.getContentType();	
		}
	
		return response;
	}


	public PLSResponse start(String sessionID, VSSession session)
	{   
		VSPage p = null;
		PLSResponse response = super.start( sessionID, session );
		String s = "";

		try
		{

				String startPage = null;
				if (getPackageName() != null && getPackageName().length() != 0)
					startPage = getPackageName() + "." + getStartPageName();
				else
					startPage = getStartPageName();
				PageNavigation StartPageNav = new PageNavigation(null, startPage);
				StartPageNav.setParentApp(this);
        			StartPageNav.setTargetFramePageName(getPackageName() + "." + "FramesetPage");
				StartPageNav.setTargetName("Startup");
			StartPageNav.setName("StartPageNav");
			p = StartPageNav.startPage();
			p.setSessionId(sessionID);
			s = p.generate();
			response.responseValue = s;
			response.metaContentType = p.getContentType();	

		}
		catch (Exception ex)
		{
			Util.logWarning(logger, ex);
			
			response.responseValue = "<html> " + ex.toString() + "</html>";
			response.metaContentType = p.getContentType();	
			return response;
		}
		if (response.responseValue.length() == 0)
		{
			response.responseValue = "<html> Unexpected Error </html>";
			response.metaContentType = p.getContentType();	
		}	
		return (response);
	}

	 abstract public String getStartPageName();
  	 abstract public String getPackageName();

	//{{APP_OTHERS
	public String getDefaultStartPageName()
	{
		return "StartupPage";
	}
	
	public String getDefaultPackageName()
	{
		return "LIBs0199831.LibraryV2";
	}
	
	//END_APP_OTHERS}}

}