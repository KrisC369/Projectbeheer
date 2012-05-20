//{{COMPONENT_BASE_IMPORT_STMTS
package LIBs0199831;
import java.util.Enumeration;
import java.util.Vector;
import versata.common.*;
import versata.common.vstrace.*;
import versata.vls.*;
import java.util.*;
import java.math.*;
import com.versata.util.logging.*;
//END_COMPONENT_BASE_IMPORT_STMTS}}
//Import statements from custom implementation class.
import  versata.vls.cache.*;


public abstract class LibraryBaseImpl extends  DataObject
	
{
	public static Logger logger = Logger.getLogger("LIBs0199831.Library");
	
	public LibraryBaseImpl () {
		super();
		addListeners();
	}

	//{{COMPONENT_META_QRY
	private final static String deployedFromRepository = "LIBs0199831";
	
	/**
	Cache support
	*/
	public static int cacheSize = 0;
	public static long expires = -1;
	public static boolean initialized = false;
	
	private static VSQueryDefinition  q= null;	
	public String getPackageName() {
		return deployedFromRepository;
	}
	static {
		q= new VSQueryDefinition( "Library" );	
		q.setPackageName(deployedFromRepository);
		
		VSMetaColumn c = null;
		VSMetaTable t = new VSMetaTable("Library");
		//{{META_QUERY_COLUMN_CTOR
		c = new VSMetaColumn("PKLibrary", DataConst.BIGINT);	
		c.setAutoIncrement(true);
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("amountOfMembers", DataConst.INTEGER);
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNullable);
		c.setComputed (true);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("amountOfCopies", DataConst.INTEGER);
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNullable);
		c.setComputed (true);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("Name", DataConst.VARCHAR);
		c.setSize(50);
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("City", DataConst.VARCHAR);
		c.setSize(50);
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("PhoneNumber", DataConst.BIGINT);	
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNullable);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("WebAddress", DataConst.VARCHAR);
		c.setSize(50);
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNullable);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("LibraryState", DataConst.INTEGER);
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setDefaultValue("1");
		c.setCaption("");
		t.addColumn( c );
	
		
		t.addUniqueKeyColumn( "Library Unique Key", "PKLibrary" );
		
		
		
	
		t.useQuotedIdentifier(true);
		t.setOptLock( DataConst.OptLockingOnApplicable );
	
		
	
	//END_META_QUERY_COLUMN_CTOR}}
		q.addTable( t );
		q.populateQCArray();
		VSQueryDefinition qTemp = (VSQueryDefinition)getMetaQuery( "Library",deployedFromRepository ); 
		if ( qTemp == null ) {			
			addMetaQuery(q, deployedFromRepository);
		}					
		else
			q = qTemp;	// Keep the old query as it has cached object
			
	
		
	
	//END_COMPONENT_META_QRY}}

	//{{COMPONENT_RULES
	


















	t.setDerivationType("LibraryState", VSMetaColumn.derivationDefault);
	t.setDerivationType("amountOfCopies", VSMetaColumn.derivationCount);
	t.setDerivationType("amountOfMembers", VSMetaColumn.derivationCount);
	String dpndsOn = "";
	// add default listener for factory events 
	
}

private boolean _cascade_deleted_in_db = false;
	
public boolean isCascadeDeletedInDB() { return _cascade_deleted_in_db; }
	
public void setCascadeDeletedInDB(boolean val) { _cascade_deleted_in_db = val; }

/**
* Returns the component name for this object, excluding the package
* (see getPackageName()).  For example, it returns 'CUSTOMERS' for the
* Sample Database CUSTOMERS Data Object.
* @return String : Returns the component name.
*/
public String getComponentName() {
	return "Library";
}

/**	  
* <br>
* MetaQuery on the component. This method returns a class defining
* the meta information of the component.
* @return VSMetaQuery : Meta data info class for the component.
*/
public static VSMetaQuery getMetaQuery() {
	return q;
    }



/**	  
* <br>
* Constructor for the class LibraryBaseImpl.
* @param Session session object on which the Business object is created.
* @param boolean if true sets the default values in the data elements
*        as defined in the meta data.
*/
public	LibraryBaseImpl(Session session, boolean makeDefaults)
{
	super(session, LibraryBaseImpl.getMetaQuery(), makeDefaults);
	addListeners();
}


/**
*	Read DataObject extended property, set the cache size and expiration time for a perticular Object type
*	This should only be done once per class type, use "initialized" as flag.
*/
public static void initCache() {
	if ( initialized ) return ;
		
	AppObjectImpl appObject = AppObjectImpl.getBusinessObject( deployedFromRepository, "Library", (Session)ServerEnvironment.getServer().getInternalSession());
	String cSize = appObject.getProperties().getProperty("CacheSize");
	if ( cSize!=null && cSize.trim().length()!=0 )
		cacheSize = Integer.parseInt( cSize );
		
	String expire = appObject.getProperties().getProperty("ExpirationMillis");
	if ( expire!=null && expire.trim().length()!=0 )
		expires = Long.parseLong(expire);

	if ( cacheSize !=0) {
		CacheManager cm = CacheAgent.getCacheAgent().getCache( q );
		cm.setMaxSize( cacheSize );

	}
	//System.err.println( "######:"+ cacheSize+"ex:"+expires);
	initialized = true;
}

/**	  
* <br>
* Factory method to get objects based on the filter (SeachRequest), which returns
* an enumeration of objects matching the filter.
* @param searchReq as SearchRequest : the filter as a SearchRequest object.
* @param aSession as Session : object to be associated with the objects.
* @return Enumeration of objects matching the filter criteria.
*/
public static Enumeration getObjects( SearchRequest searchReq, Session aSession )
	throws ServerException
{
	if ( aSession.getSecurityCheck() ) {
		try {
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "Library", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "Library","", (String)null);
		}
		}
		catch( VSORBException e ) { 
		
			Util.logWarning(logger, e);
		}
	}

	raiseBeforeQueryEvent( searchReq, aSession );
	

	XDAConnector xdac = createXDAConnector(aSession);
	
	if ( !initialized ) 
		initCache();
	
	
	if (aSession.isTransactionInProgress()) {
		Enumeration e= aSession.getTransactionInfo().getObjects(LibraryBaseImpl.getMetaQuery(), xdac, searchReq);
		
		// ******************* cache support
		if ( cacheSize!=0 && e.hasMoreElements()) {
			Vector boList = new Vector();
			while ( e.hasMoreElements()) {
				boList.addElement( e.nextElement());
			}
			
			CacheAgent.getCacheAgent().setObjects( LibraryBaseImpl.getMetaQuery(), boList, expires );
			
			return boList.elements();
		
		} else
			return e;
	
	} else {
		Vector boList = new Vector();
				Connection con = aSession.getConnection( xdac, true );
		ResultSet rs = new VSORBResultSetImpl( LibraryBaseImpl.getMetaQuery(), searchReq, null, -1 ,DataConst.NONE_LOB, xdac, con );
		raiseAfterQueryEvent( rs );
		DataRow row  = null ;
		while (  ( row =  rs.fetch() ) != null )
		{	
			DataObject bo = row.getComponent();
			boList.addElement( bo );

		}
		rs.close();
		
		//**************************** cache support
		if ( cacheSize != 0 )
			CacheAgent.getCacheAgent().setObjects( LibraryBaseImpl.getMetaQuery(), boList, expires );
			
		return boList.elements();
	}
}

public static void raiseBeforeQueryEvent( SearchRequest searchReq, Session aSession )	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"Library.BeforeQuery");
	}

	beforeQuery( "Library", searchReq, null , aSession );
	
	if (  tr != null ) tr.end( tr_id );
}

public static void raiseAfterQueryEvent( ResultSet rs)	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"Library.AfterQuery");
	}

	afterQuery( rs );
	
	if (  tr != null ) tr.end( tr_id );
}


/**	  
* <br>
* Factory method to get objects based on the filter (String), which returns
* an enumeration of objects matching the filter.
* @param searchReq as SearchRequest : the filter as a String. (e.g. State = 'NY').
* @param aSession as Session : object to be associated with the objects.
* @return Enumeration of objects matching the filter criteria.
*/
public static Enumeration getObjects(String filter, Session s) {
	SearchRequest searchReq = new SearchRequest();
	searchReq.add(filter);
	return getObjects(searchReq, s);
}

/**	  
* <br>
* Factory method to create an object based on the unique key value which 
* returns an object matching the key value.
* @param searchReq as SearchRequest : the key value as a SearchRequest object.
* @param aSession as Session : object to be associated with the objects.
* @return the object matching the Unique key.
*/
public static DataObject getObjectByKey( SearchRequest key, Session aSession )
	throws ServerException
{
	if ( aSession.getSecurityCheck() ) {
		try {
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "Library", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "Library","", (String)null);
		}
		}
		catch( VSORBException e ) { 
			
			Util.logWarning(logger, e);
		}
	}

	raiseBeforeQueryEvent( key, aSession );
	
	if ( !initialized ) 
		initCache();
	
	DataObject anObject = null;
	
	//**************************** cache support
	if ( cacheSize != 0 )    {
		anObject = CacheAgent.getCacheAgent().getObject( LibraryBaseImpl.getMetaQuery(),key.parameterList, aSession);
		
		logger.fine(" Cache :"+ anObject);
		
		if (anObject!=null)  {
			//set current session to this cached object, ensure that object has a active session
			anObject.setSession( aSession );
			return anObject;
		}
	}

	if (aSession.isTransactionInProgress()) {
		anObject = aSession.getTransactionInfo().getObjectByKey(LibraryBaseImpl.getMetaQuery(),key);
	} else {		
		anObject = aSession.getObjectByKey(LibraryBaseImpl.getMetaQuery(),key);
	}
	
	//**************************** cache support
	if ( cacheSize != 0 )
		 CacheAgent.getCacheAgent().addObject( LibraryBaseImpl.getMetaQuery(),anObject, expires );
		 
	return anObject;
}





// Factored out duplicate code passage - Val/Paul 03-17-03
protected void tableConditionActions() {
}


protected void insert() throws ServerException
{
	// Post Rule Event
	postRuleEvent(VLSEvent.BEFORE_INSERT, response.reset());
	
	if (response.rejected()) return;


	// Set the default values for the aggregates
	setamountOfCopies((int)0);
	setamountOfMembers((int)0);
	// Generate Columns Default code here.
	if ( isNull("LibraryState") ) 
		setLibraryState((int)(1));
	
	XDAConnector xdac = getXDAConnector();
	// For the counter field set and get the counter value
	Object counter = null;

	if ( xdac instanceof XDASQLConnector ) {
		if ( ( counter = ((XDASQLConnector)xdac).getCounter(LibraryBaseImpl.getMetaQuery().getBaseTable("Library"), 
		   session.getTransactionInfo().getConnection( xdac), rowData,"PKLibrary" ) )!= null )	
		   this.setPKLibrary( ((Long)counter).longValue());
	}
	else {
		if ( ( counter = xdac.getCounter(LibraryBaseImpl.getMetaQuery().getBaseTable("Library"), 
		   session.getTransactionInfo().getConnection( xdac), rowData ) )!= null )	
		   this.setPKLibrary( ((Long)counter).longValue());
	}



	// Do Column Validation Checks
	this.columnValidationCheck();

	// Do TableConstraintChecks

	// Now verify that not nullable columns are not null.
	if ( isNull("LibraryState") )
	{
		// Raise the exception here.
		raiseException("Attribute 'Library.LibraryState' does not allow Null Values. Error Column: <Library>.<LibraryState>");

	}


	// At this point all the computations for the object attributes have been made,
	// therefore save the object.	
	this.updateRowImmediate();

	// Factored out duplicate code passage - Val/Paul 03-17-03
	this.tableConditionActions();


	// Do Child Cascades.
	// Child Cascade for Role pribrary)-Member(Member): Library->>Member
	this.childCascadeFor_Member();
	// Child Cascade for Role pribrary)-CreateEvent(CreateEvent): Library->>CreateEvent
	this.childCascadeFor_CreateEvent();
	// Child Cascade for Role pribrary)-EnterEvent(EnterEvent): Library->>EnterEvent
	this.childCascadeFor_EnterEvent();
	// Child Cascade for Role pribrary)-AcquireEvent(AcquireEvent): Library->>AcquireEvent
	this.childCascadeFor_AcquireEvent();
	// Child Cascade for Role pribrary)-DestroyEvent(DestroyEvent): Library->>DestroyEvent
	this.childCascadeFor_DestroyEvent();
	// Child Cascade for Role pribrary)-Copy(Copy): Library->>Copy
	this.childCascadeFor_Copy();

	// Post Rule Event
	postRuleEvent(VLSEvent.AFTER_INSERT, null);
	// This concludes the insert procedure.	
}

protected void update() throws ServerException
{
	// Post Rule Event
	postRuleEvent(VLSEvent.BEFORE_UPDATE, response.reset());
	if (response.rejected()) return;

	// Column Non Alterability check
	if ( getGlobalNestLevel() == 1 && isAltered("amountOfCopies") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'amountOfCopies' in Object Library is not Alterable. Error Column: <Library>.<amountOfCopies>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("amountOfMembers") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'amountOfMembers' in Object Library is not Alterable. Error Column: <Library>.<amountOfMembers>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("PKLibrary") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'PKLibrary' in Object Library is not Alterable. Error Column: <Library>.<PKLibrary>");
	}

	// Do the parent checks.

	// Set the formulae values at this point
	this.setFormulaValues();


	// Do Column Validation Checks
	this.columnValidationCheck();

	// verify that not nullable columns are not null.
	if ( isNull("LibraryState") )
	{
		// Raise the exception here.
		raiseException("Attribute 'Library.LibraryState' does not allow Null Values. Error Column: <Library>.<LibraryState>");

	}



	// Compute stored values if not already done
	if ( isChanged("amountOfCopies") )
		getamountOfCopies();
	if ( isChanged("amountOfMembers") )
		getamountOfMembers();

	if ( this.isCurrentEvent("RECOMPUTE_DERIVATIONS") && (getGlobalNestLevel() == 1) ) this.RecomputeDerivations();

	// At this point all the computations for the object attributes have been made
	// therefore save the object.
	this.updateRowImmediate();

	// Factored out duplicate code passage - Val/Paul 03-17-03
	this.tableConditionActions();

	// Do Child Cascades.
	// Child Cascade for Role pribrary)-Member(Member): Library->>Member
	this.childCascadeFor_Member();
	// Child Cascade for Role pribrary)-CreateEvent(CreateEvent): Library->>CreateEvent
	this.childCascadeFor_CreateEvent();
	// Child Cascade for Role pribrary)-EnterEvent(EnterEvent): Library->>EnterEvent
	this.childCascadeFor_EnterEvent();
	// Child Cascade for Role pribrary)-AcquireEvent(AcquireEvent): Library->>AcquireEvent
	this.childCascadeFor_AcquireEvent();
	// Child Cascade for Role pribrary)-DestroyEvent(DestroyEvent): Library->>DestroyEvent
	this.childCascadeFor_DestroyEvent();
	// Child Cascade for Role pribrary)-Copy(Copy): Library->>Copy
	this.childCascadeFor_Copy();
	// Post Rule Event
	postRuleEvent(VLSEvent.AFTER_UPDATE, null);
	// This concludes the update procedure.
}

protected void delete() throws ServerException
{
	// Post Rule Event
	postRuleEvent(VLSEvent.BEFORE_DELETE, response.reset());
	if (response.rejected()) return;

	// Remove myself from the parent cache.

	// Do Column Validation Checks
	this.columnValidationCheck();

	// Do TableConstraintChecks
	if ( !isCascadeDeletedInDB() )
	this.updateRowImmediate();

	// Factored out duplicate code passage - Val/Paul 03-17-03
	this.tableConditionActions();

	// Do Child Cascades.
	// Child Cascade for Role pribrary)-Member(Member): Library->>Member
	this.childCascadeFor_Member();
	// Child Cascade for Role pribrary)-CreateEvent(CreateEvent): Library->>CreateEvent
	this.childCascadeFor_CreateEvent();
	// Child Cascade for Role pribrary)-EnterEvent(EnterEvent): Library->>EnterEvent
	this.childCascadeFor_EnterEvent();
	// Child Cascade for Role pribrary)-AcquireEvent(AcquireEvent): Library->>AcquireEvent
	this.childCascadeFor_AcquireEvent();
	// Child Cascade for Role pribrary)-DestroyEvent(DestroyEvent): Library->>DestroyEvent
	this.childCascadeFor_DestroyEvent();
	// Child Cascade for Role pribrary)-Copy(Copy): Library->>Copy
	this.childCascadeFor_Copy();
 

	// Do the Parent Adjustment.

	// Post Rule Event
	postRuleEvent(VLSEvent.AFTER_DELETE, null);
	// This concludes the delete procedure.
}

protected void setFormulaValues()
{
		Data dataVal = null;
		IVSTrace tr = null;
		long tr_id = 0;
			

}

protected void setFormulaValuesWithoutException()
{
		Data dataVal = null;

}


public void columnValidationCheck() 
{
}


	protected void childCascadeFor_Member()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_Member").set(VST_OBJECT_NAME,"Library");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKLibrary")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldMember(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are Member found for Library");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldMember();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are Member found for Library");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}
	protected void childCascadeFor_CreateEvent()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_CreateEvent").set(VST_OBJECT_NAME,"Library");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKLibrary")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldCreateEvent(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are CreateEvent found for Library");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldCreateEvent();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are CreateEvent found for Library");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}
	protected void childCascadeFor_EnterEvent()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_EnterEvent").set(VST_OBJECT_NAME,"Library");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKLibrary")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldEnterEvent(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are EnterEvent found for Library");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldEnterEvent();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are EnterEvent found for Library");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}
	protected void childCascadeFor_AcquireEvent()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_AcquireEvent").set(VST_OBJECT_NAME,"Library");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKLibrary")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldAcquireEvent(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are AcquireEvent found for Library");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldAcquireEvent();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are AcquireEvent found for Library");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}
	protected void childCascadeFor_DestroyEvent()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_DestroyEvent").set(VST_OBJECT_NAME,"Library");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKLibrary")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldDestroyEvent(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are DestroyEvent found for Library");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldDestroyEvent();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are DestroyEvent found for Library");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}
	protected void childCascadeFor_Copy()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_Copy").set(VST_OBJECT_NAME,"Library");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKLibrary")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldCopy(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are Copy found for Library");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldCopy();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are Copy found for Library");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}




	

private ObjectHashtable MemberCache = null;

/**	  
* <br>
* method to retrieve the Member objects for this Library
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of Member objects.
*/
public Enumeration getMember()
{
	if (!getSession().getProperty("NoCacheMember").equals("true"))
	{
		if ( MemberCache != null ) return MemberCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "Member";
	param.fieldName = "FKLibrary";
	param.value = getData("PKLibrary").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheMember").equals("true"))
	{
		MemberCache = new ObjectHashtable();
		for (Enumeration e = (MemberBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			MemberCache.put(pkey,cacheBO);								
		}
		return (MemberCache.elements());
	}
	else
	{
		return (MemberBaseImpl.getObjects(searchReq ,getSession()));
	}
}
private ObjectHashtable CreateEventCache = null;

/**	  
* <br>
* method to retrieve the CreateEvent objects for this Library
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of CreateEvent objects.
*/
public Enumeration getCreateEvent()
{
	if (!getSession().getProperty("NoCacheCreateEvent").equals("true"))
	{
		if ( CreateEventCache != null ) return CreateEventCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "CreateEvent";
	param.fieldName = "FKLibrary";
	param.value = getData("PKLibrary").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheCreateEvent").equals("true"))
	{
		CreateEventCache = new ObjectHashtable();
		for (Enumeration e = (CreateEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			CreateEventCache.put(pkey,cacheBO);								
		}
		return (CreateEventCache.elements());
	}
	else
	{
		return (CreateEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}
private ObjectHashtable EnterEventCache = null;

/**	  
* <br>
* method to retrieve the EnterEvent objects for this Library
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of EnterEvent objects.
*/
public Enumeration getEnterEvent()
{
	if (!getSession().getProperty("NoCacheEnterEvent").equals("true"))
	{
		if ( EnterEventCache != null ) return EnterEventCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "EnterEvent";
	param.fieldName = "FKLibrary";
	param.value = getData("PKLibrary").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheEnterEvent").equals("true"))
	{
		EnterEventCache = new ObjectHashtable();
		for (Enumeration e = (EnterEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			EnterEventCache.put(pkey,cacheBO);								
		}
		return (EnterEventCache.elements());
	}
	else
	{
		return (EnterEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}
private ObjectHashtable AcquireEventCache = null;

/**	  
* <br>
* method to retrieve the AcquireEvent objects for this Library
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of AcquireEvent objects.
*/
public Enumeration getAcquireEvent()
{
	if (!getSession().getProperty("NoCacheAcquireEvent").equals("true"))
	{
		if ( AcquireEventCache != null ) return AcquireEventCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "AcquireEvent";
	param.fieldName = "FKLibrary";
	param.value = getData("PKLibrary").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheAcquireEvent").equals("true"))
	{
		AcquireEventCache = new ObjectHashtable();
		for (Enumeration e = (AcquireEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			AcquireEventCache.put(pkey,cacheBO);								
		}
		return (AcquireEventCache.elements());
	}
	else
	{
		return (AcquireEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}
private ObjectHashtable DestroyEventCache = null;

/**	  
* <br>
* method to retrieve the DestroyEvent objects for this Library
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of DestroyEvent objects.
*/
public Enumeration getDestroyEvent()
{
	if (!getSession().getProperty("NoCacheDestroyEvent").equals("true"))
	{
		if ( DestroyEventCache != null ) return DestroyEventCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "DestroyEvent";
	param.fieldName = "FKLibrary";
	param.value = getData("PKLibrary").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheDestroyEvent").equals("true"))
	{
		DestroyEventCache = new ObjectHashtable();
		for (Enumeration e = (DestroyEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			DestroyEventCache.put(pkey,cacheBO);								
		}
		return (DestroyEventCache.elements());
	}
	else
	{
		return (DestroyEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}
private ObjectHashtable CopyCache = null;

/**	  
* <br>
* method to retrieve the Copy objects for this Library
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of Copy objects.
*/
public Enumeration getCopy()
{
	if (!getSession().getProperty("NoCacheCopy").equals("true"))
	{
		if ( CopyCache != null ) return CopyCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "Copy";
	param.fieldName = "FKLibrary";
	param.value = getData("PKLibrary").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheCopy").equals("true"))
	{
		CopyCache = new ObjectHashtable();
		for (Enumeration e = (CopyBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			CopyCache.put(pkey,cacheBO);								
		}
		return (CopyCache.elements());
	}
	else
	{
		return (CopyBaseImpl.getObjects(searchReq ,getSession()));
	}
}



/**	  
* <br>
* method to retrieve the old Member objects for this Library
* old Member objects would be different from the new ones usualy if
* the Library has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old Member objects.
*/
  public Enumeration getOldMember(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheMember");
    if (cache)
      getSession().setProperty("NoCacheMember", "false");
    else
      getSession().setProperty("NoCacheMember", "true");   
    
    try {
      return getOldMember();
    } finally {                     
        getSession().setProperty("NoCacheMember", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old Member objects for this Library
* old Member objects would be different from the new ones usualy if
* the Library has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old Member objects.
*/
public Enumeration	getOldMember()
{
	if (!getSession().getProperty("NoCacheMember").equals("true"))
	{
		if ( MemberCache != null ) return MemberCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "Member";
	param.fieldName = "FKLibrary";
	param.value = getData("PKLibrary").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheMember").equals("true"))
	{
		MemberCache = new ObjectHashtable();
		for (Enumeration e = (MemberBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			MemberCache.put(pkey,cacheBO);								
		}
		return (MemberCache.elements());
	}
	else
	{
		return (MemberBaseImpl.getObjects(searchReq ,getSession()));
	}
}


/**	  
* <br>
* method to retrieve the old CreateEvent objects for this Library
* old CreateEvent objects would be different from the new ones usualy if
* the Library has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old CreateEvent objects.
*/
  public Enumeration getOldCreateEvent(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheCreateEvent");
    if (cache)
      getSession().setProperty("NoCacheCreateEvent", "false");
    else
      getSession().setProperty("NoCacheCreateEvent", "true");   
    
    try {
      return getOldCreateEvent();
    } finally {                     
        getSession().setProperty("NoCacheCreateEvent", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old CreateEvent objects for this Library
* old CreateEvent objects would be different from the new ones usualy if
* the Library has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old CreateEvent objects.
*/
public Enumeration	getOldCreateEvent()
{
	if (!getSession().getProperty("NoCacheCreateEvent").equals("true"))
	{
		if ( CreateEventCache != null ) return CreateEventCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "CreateEvent";
	param.fieldName = "FKLibrary";
	param.value = getData("PKLibrary").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheCreateEvent").equals("true"))
	{
		CreateEventCache = new ObjectHashtable();
		for (Enumeration e = (CreateEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			CreateEventCache.put(pkey,cacheBO);								
		}
		return (CreateEventCache.elements());
	}
	else
	{
		return (CreateEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}


/**	  
* <br>
* method to retrieve the old EnterEvent objects for this Library
* old EnterEvent objects would be different from the new ones usualy if
* the Library has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old EnterEvent objects.
*/
  public Enumeration getOldEnterEvent(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheEnterEvent");
    if (cache)
      getSession().setProperty("NoCacheEnterEvent", "false");
    else
      getSession().setProperty("NoCacheEnterEvent", "true");   
    
    try {
      return getOldEnterEvent();
    } finally {                     
        getSession().setProperty("NoCacheEnterEvent", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old EnterEvent objects for this Library
* old EnterEvent objects would be different from the new ones usualy if
* the Library has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old EnterEvent objects.
*/
public Enumeration	getOldEnterEvent()
{
	if (!getSession().getProperty("NoCacheEnterEvent").equals("true"))
	{
		if ( EnterEventCache != null ) return EnterEventCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "EnterEvent";
	param.fieldName = "FKLibrary";
	param.value = getData("PKLibrary").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheEnterEvent").equals("true"))
	{
		EnterEventCache = new ObjectHashtable();
		for (Enumeration e = (EnterEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			EnterEventCache.put(pkey,cacheBO);								
		}
		return (EnterEventCache.elements());
	}
	else
	{
		return (EnterEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}


/**	  
* <br>
* method to retrieve the old AcquireEvent objects for this Library
* old AcquireEvent objects would be different from the new ones usualy if
* the Library has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old AcquireEvent objects.
*/
  public Enumeration getOldAcquireEvent(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheAcquireEvent");
    if (cache)
      getSession().setProperty("NoCacheAcquireEvent", "false");
    else
      getSession().setProperty("NoCacheAcquireEvent", "true");   
    
    try {
      return getOldAcquireEvent();
    } finally {                     
        getSession().setProperty("NoCacheAcquireEvent", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old AcquireEvent objects for this Library
* old AcquireEvent objects would be different from the new ones usualy if
* the Library has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old AcquireEvent objects.
*/
public Enumeration	getOldAcquireEvent()
{
	if (!getSession().getProperty("NoCacheAcquireEvent").equals("true"))
	{
		if ( AcquireEventCache != null ) return AcquireEventCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "AcquireEvent";
	param.fieldName = "FKLibrary";
	param.value = getData("PKLibrary").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheAcquireEvent").equals("true"))
	{
		AcquireEventCache = new ObjectHashtable();
		for (Enumeration e = (AcquireEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			AcquireEventCache.put(pkey,cacheBO);								
		}
		return (AcquireEventCache.elements());
	}
	else
	{
		return (AcquireEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}


/**	  
* <br>
* method to retrieve the old DestroyEvent objects for this Library
* old DestroyEvent objects would be different from the new ones usualy if
* the Library has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old DestroyEvent objects.
*/
  public Enumeration getOldDestroyEvent(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheDestroyEvent");
    if (cache)
      getSession().setProperty("NoCacheDestroyEvent", "false");
    else
      getSession().setProperty("NoCacheDestroyEvent", "true");   
    
    try {
      return getOldDestroyEvent();
    } finally {                     
        getSession().setProperty("NoCacheDestroyEvent", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old DestroyEvent objects for this Library
* old DestroyEvent objects would be different from the new ones usualy if
* the Library has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old DestroyEvent objects.
*/
public Enumeration	getOldDestroyEvent()
{
	if (!getSession().getProperty("NoCacheDestroyEvent").equals("true"))
	{
		if ( DestroyEventCache != null ) return DestroyEventCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "DestroyEvent";
	param.fieldName = "FKLibrary";
	param.value = getData("PKLibrary").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheDestroyEvent").equals("true"))
	{
		DestroyEventCache = new ObjectHashtable();
		for (Enumeration e = (DestroyEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			DestroyEventCache.put(pkey,cacheBO);								
		}
		return (DestroyEventCache.elements());
	}
	else
	{
		return (DestroyEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}


/**	  
* <br>
* method to retrieve the old Copy objects for this Library
* old Copy objects would be different from the new ones usualy if
* the Library has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old Copy objects.
*/
  public Enumeration getOldCopy(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheCopy");
    if (cache)
      getSession().setProperty("NoCacheCopy", "false");
    else
      getSession().setProperty("NoCacheCopy", "true");   
    
    try {
      return getOldCopy();
    } finally {                     
        getSession().setProperty("NoCacheCopy", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old Copy objects for this Library
* old Copy objects would be different from the new ones usualy if
* the Library has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old Copy objects.
*/
public Enumeration	getOldCopy()
{
	if (!getSession().getProperty("NoCacheCopy").equals("true"))
	{
		if ( CopyCache != null ) return CopyCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "Copy";
	param.fieldName = "FKLibrary";
	param.value = getData("PKLibrary").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheCopy").equals("true"))
	{
		CopyCache = new ObjectHashtable();
		for (Enumeration e = (CopyBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			CopyCache.put(pkey,cacheBO);								
		}
		return (CopyCache.elements());
	}
	else
	{
		return (CopyBaseImpl.getObjects(searchReq ,getSession()));
	}
}

public void updateCacheForMember(MemberBaseImpl child, boolean remove)
{
	if ( MemberCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( MemberCache.containsKey(child.getRow().getPkeyParams()) )
				MemberCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! MemberCache.containsKey(child.getRow().getPkeyParams()) )
				MemberCache.put(child.getRow().getPkeyParams(), child);
		}
	}
}
public void updateCacheForCreateEvent(CreateEventBaseImpl child, boolean remove)
{
	if ( CreateEventCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( CreateEventCache.containsKey(child.getRow().getPkeyParams()) )
				CreateEventCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! CreateEventCache.containsKey(child.getRow().getPkeyParams()) )
				CreateEventCache.put(child.getRow().getPkeyParams(), child);
		}
	}
}
public void updateCacheForEnterEvent(EnterEventBaseImpl child, boolean remove)
{
	if ( EnterEventCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( EnterEventCache.containsKey(child.getRow().getPkeyParams()) )
				EnterEventCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! EnterEventCache.containsKey(child.getRow().getPkeyParams()) )
				EnterEventCache.put(child.getRow().getPkeyParams(), child);
		}
	}
}
public void updateCacheForAcquireEvent(AcquireEventBaseImpl child, boolean remove)
{
	if ( AcquireEventCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( AcquireEventCache.containsKey(child.getRow().getPkeyParams()) )
				AcquireEventCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! AcquireEventCache.containsKey(child.getRow().getPkeyParams()) )
				AcquireEventCache.put(child.getRow().getPkeyParams(), child);
		}
	}
}
public void updateCacheForDestroyEvent(DestroyEventBaseImpl child, boolean remove)
{
	if ( DestroyEventCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( DestroyEventCache.containsKey(child.getRow().getPkeyParams()) )
				DestroyEventCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! DestroyEventCache.containsKey(child.getRow().getPkeyParams()) )
				DestroyEventCache.put(child.getRow().getPkeyParams(), child);
		}
	}
}
public void updateCacheForCopy(CopyBaseImpl child, boolean remove)
{
	if ( CopyCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( CopyCache.containsKey(child.getRow().getPkeyParams()) )
				CopyCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! CopyCache.containsKey(child.getRow().getPkeyParams()) )
				CopyCache.put(child.getRow().getPkeyParams(), child);
		}
	}
}

/**	
* Invalidates the relationship cache. Called by the system on transaction begin.
*/
public void invalidateNonTransactionCaches() 
{
 
  MemberCache = null;
  CreateEventCache = null;
  EnterEventCache = null;
  AcquireEventCache = null;
  DestroyEventCache = null;
  CopyCache = null;
}





	/**	  
	* <br>
	* method to get the LibraryState attribute for the Library
	* @return int : the  value of the attribute LibraryState as int.
	*/
	public int	getLibraryState() 
	{
	return getData("LibraryState").getint();
	}

	/**	  
	* <br>
	* method to set the LibraryState attribute for the Library
	* @param int : value of the attribute LibraryState as int.
	* @return nothing
	*/
	public void	setLibraryState(int value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("LibraryState");
	dataVal.setint(value);
	}

	/**	  
	* <br>
	* method to get the old LibraryState attribute for the Library
	* @return int : the  value of the old attribute LibraryState as int.
	*/
	public int	getOldLibraryState()
	{
	return getData("LibraryState").getPreviousint();
	}

	/**	  
	* <br>
	* method to get the City attribute for the Library
	* @return String : the  value of the attribute City as String.
	*/
	public String	getCity() 
	{
	return getData("City").getString();
	}

	/**	  
	* <br>
	* method to set the City attribute for the Library
	* @param String : value of the attribute City as String.
	* @return nothing
	*/
	public void	setCity(String value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("City");
	dataVal.setString(value);
	}

	/**	  
	* <br>
	* method to get the old City attribute for the Library
	* @return String : the  value of the old attribute City as String.
	*/
	public String	getOldCity()
	{
	return getData("City").getPreviousString();
	}

	/**	  
	* <br>
	* method to get the Name attribute for the Library
	* @return String : the  value of the attribute Name as String.
	*/
	public String	getName() 
	{
	return getData("Name").getString();
	}

	/**	  
	* <br>
	* method to set the Name attribute for the Library
	* @param String : value of the attribute Name as String.
	* @return nothing
	*/
	public void	setName(String value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("Name");
	dataVal.setString(value);
	}

	/**	  
	* <br>
	* method to get the old Name attribute for the Library
	* @return String : the  value of the old attribute Name as String.
	*/
	public String	getOldName()
	{
	return getData("Name").getPreviousString();
	}

	/**	  
	* <br>
	* method to get the PhoneNumber attribute for the Library
	* @return long : the  value of the attribute PhoneNumber as long.
	*/
	public long	getPhoneNumber() 
	{
	return getData("PhoneNumber").getlong();
	}

	/**	  
	* <br>
	* method to set the PhoneNumber attribute for the Library
	* @param long : value of the attribute PhoneNumber as long.
	* @return nothing
	*/
	public void	setPhoneNumber(long value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("PhoneNumber");
	dataVal.setlong(value);
	}

	/**	  
	* <br>
	* method to get the old PhoneNumber attribute for the Library
	* @return long : the  value of the old attribute PhoneNumber as long.
	*/
	public long	getOldPhoneNumber()
	{
	return getData("PhoneNumber").getPreviouslong();
	}

	/**	  
	* <br>
	* method to get the WebAddress attribute for the Library
	* @return String : the  value of the attribute WebAddress as String.
	*/
	public String	getWebAddress() 
	{
	return getData("WebAddress").getString();
	}

	/**	  
	* <br>
	* method to set the WebAddress attribute for the Library
	* @param String : value of the attribute WebAddress as String.
	* @return nothing
	*/
	public void	setWebAddress(String value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("WebAddress");
	dataVal.setString(value);
	}

	/**	  
	* <br>
	* method to get the old WebAddress attribute for the Library
	* @return String : the  value of the old attribute WebAddress as String.
	*/
	public String	getOldWebAddress()
	{
	return getData("WebAddress").getPreviousString();
	}


/**	  
* <br>
* method to get the PKLibrary attribute for the Library
* @return long : the  value of the attribute PKLibrary as long.
*/
public long	getPKLibrary() 
{
	return getData("PKLibrary").getlong();
}

/**	  
* <br>
* method to set the PKLibrary attribute for the Library
* @param long : value of the attribute PKLibrary as long.
* @return nothing
*/
public void	setPKLibrary(long value)
{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("PKLibrary");
	dataVal.setlong(value);
}

/**	  
* <br>
* method to get the old PKLibrary attribute for the Library
* @return long : the  value of the old attribute PKLibrary as long.
*/
public long	getOldPKLibrary()
{
	return getData("PKLibrary").getPreviouslong();
}


	/**	  
	* <br>
	* method to get the count attribute amountOfCopies for the Library
	* @return int : the  value of the attribute amountOfCopies as int.
	*/
	public int	getamountOfCopies() 
	{
	Data dataVal = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Copy";
	param.fieldName = "FKLibrary";
	if (getSession().isTransactionInProgress())
	{
		if ( isUpdated() && (getGlobalNestLevel() == 1) && (isChanged("PKLibrary")))
			param.value = getData("PKLibrary").getOldString();
		else
			param.value = getData("PKLibrary").getString();
	}
	else
		param.value = getData("PKLibrary").getString();
	searchReq.add(param);

		dataVal = getData("amountOfCopies");											
	if (dataVal.isComputationPending() ||  (this.isCurrentEvent("RECOMPUTE_DERIVATIONS") && (getGlobalNestLevel() == 1)) )
	{
		if (dataVal.isDeltaValid() && !(this.isCurrentEvent("RECOMPUTE_DERIVATIONS") && (getGlobalNestLevel() == 1)) )
		{
			dataVal.setint((int)(dataVal.getint() + dataVal.getDeltaint()));
			dataVal.setDeltaValid(false);
		}
		else
		{
			dataVal.setint((CopyBaseImpl.getLibraryamountOfCopies(searchReq, getSession())));
		}

		dataVal.setInitialized(true);
		dataVal.setPushPending(false);
	}
	return dataVal.getint();
	}

/**	  
* <br>
* method to get the value before data change for the count attribute amountOfCopies
* in the data object Library
* @return int : the  value of the attribute amountOfCopies as int before change.
*/
public int	getOldamountOfCopies()
{
	return getData("amountOfCopies").getPreviousint();
}

/**	  
* <br>
* method to set the value of the count attribute amountOfCopies in the data object Library
* @param int : value of the attribute amountOfCopies as int.
* @return nothing
*/
public void setamountOfCopies(int value)
{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("amountOfCopies");
	dataVal.setint(value);
}


	/**	  
	* <br>
	* method to get the count attribute amountOfMembers for the Library
	* @return int : the  value of the attribute amountOfMembers as int.
	*/
	public int	getamountOfMembers() 
	{
	Data dataVal = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Member";
	param.fieldName = "FKLibrary";
	if (getSession().isTransactionInProgress())
	{
		if ( isUpdated() && (getGlobalNestLevel() == 1) && (isChanged("PKLibrary")))
			param.value = getData("PKLibrary").getOldString();
		else
			param.value = getData("PKLibrary").getString();
	}
	else
		param.value = getData("PKLibrary").getString();
	searchReq.add(param);

		dataVal = getData("amountOfMembers");											
	if (dataVal.isComputationPending() ||  (this.isCurrentEvent("RECOMPUTE_DERIVATIONS") && (getGlobalNestLevel() == 1)) )
	{
		if (dataVal.isDeltaValid() && !(this.isCurrentEvent("RECOMPUTE_DERIVATIONS") && (getGlobalNestLevel() == 1)) )
		{
			dataVal.setint((int)(dataVal.getint() + dataVal.getDeltaint()));
			dataVal.setDeltaValid(false);
		}
		else
		{
			dataVal.setint((MemberBaseImpl.getLibraryamountOfMembers(searchReq, getSession())));
		}

		dataVal.setInitialized(true);
		dataVal.setPushPending(false);
	}
	return dataVal.getint();
	}

/**	  
* <br>
* method to get the value before data change for the count attribute amountOfMembers
* in the data object Library
* @return int : the  value of the attribute amountOfMembers as int before change.
*/
public int	getOldamountOfMembers()
{
	return getData("amountOfMembers").getPreviousint();
}

/**	  
* <br>
* method to set the value of the count attribute amountOfMembers in the data object Library
* @param int : value of the attribute amountOfMembers as int.
* @return nothing
*/
public void setamountOfMembers(int value)
{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("amountOfMembers");
	dataVal.setint(value);
}





	




/**	  
* <br>
* a factory method to get the rowcount on the objects satisfying the criterion.
* @param SearchRequest : the criterion for the selection
* @param Session : Session object on which the operation is performed.
* @return int : the count of objects satistying the condition.
*/
public static int getObjectCount( SearchRequest searchReq , Session aSession )
throws ServerException
    {
	XDAConnector xdac = createXDAConnector(aSession);
	Connection con = null;
	int count = 0;

	if ( aSession.isTransactionInProgress())
		count = aSession.getTransactionInfo().getObjectsCount(LibraryBaseImpl.getMetaQuery(), xdac, searchReq);
	else
	{
		con = aSession.getConnection( xdac, true );
  	  	count = xdac.getRowCount( LibraryBaseImpl.getMetaQuery(), searchReq, con );
	}

  	return count;
  
}


/**	  
* <br>
* a factory method to create the XDAConnector object for this class.
* @return XDAConnector : if succcessful returns an instance of the XDA Connector.
*/
public static XDAConnector createXDAConnector()
{ 
	XDAConnector xda = null;
	try {
		VSMetaTable table = LibraryBaseImpl.getMetaQuery().getChildMostTable();
		if ( table != null )
			xda = ( XDAConnector)Class.forName( table.getXDAConnectorClassName() ).newInstance();
		else
			xda = ( XDAConnector)Class.forName( "versata.vls.XDASQLConnector").newInstance();

	}
	catch ( Exception ex )
	{
		Util.logWarning(logger, ex);
	}

	return xda;
}


/**	  
* <br>
* a factory method to create the XDAConnector object for this class for a perticular session.
* @param Session : Session object with which to associate the XDA Connector.
* @return XDAConnector : if succcessful returns an instance of the XDA Connector.
*/
public static XDAConnector createXDAConnector( Session aSession )
	throws ServerException
{ 
	XDAConnector xda = null;
	try {
		VSMetaTable table = LibraryBaseImpl.getMetaQuery().getChildMostTable();
		if ( table != null )
			xda = ( XDAConnector)Class.forName( table.getXDAConnectorClassName() ).newInstance();
		else
			xda = ( XDAConnector)Class.forName( "versata.vls.XDASQLConnector").newInstance();

	}
	catch ( Exception ex )
	{
		Util.logWarning(logger, ex);
	}
	
	xda.setSession( aSession );
	Properties props = ((VSORBSessionImpl)aSession).getMyDataServiceLoginForObject("Library");
	xda.setProperties(props);

	return xda;
}  

/**	  
* <br>
* returns the current XDA Connector for this object if one is already created, otherwise
* an instance of the XDA Connector is created and returned.
* @return XDAConnector : if succcessful returns an instance of the XDA Connector.
*/
public XDAConnector getXDAConnector()
{
	if ( xdac == null )
		xdac = createXDAConnector(session);
	return xdac;
}
/**	  
* <br>
* @RECOMPUTE: This routine forces recalculation of formulaes.
*/
public void RecomputeDerivations()
{
	this.setamountOfCopies(getamountOfCopies());
	this.setamountOfMembers(getamountOfMembers());

	this.setFormulaValues();
}

	//END_COMPONENT_RULES}}	

	abstract protected void addListeners();
	//{{ABSTRACT_CUSTOM_METHODS
	/**
	* User defined methods used in a rule, are defined in the Impl class,
	* have corresponding abstract methods in BaseImpl.
	* Please add @SuppressAbstract as comment after Method Declaration in Impl Source file
	* to avoid abstract declaration of any Method.
	**/

	//END_ABSTRACT_CUSTOM_METHODS}}
}

