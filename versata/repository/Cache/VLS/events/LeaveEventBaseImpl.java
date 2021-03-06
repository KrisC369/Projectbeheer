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


public abstract class LeaveEventBaseImpl extends  DataObject
	
{
	public static Logger logger = Logger.getLogger("LIBs0199831.LeaveEvent");
	
	public LeaveEventBaseImpl () {
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
		q= new VSQueryDefinition( "LeaveEvent" );	
		q.setPackageName(deployedFromRepository);
		
		VSMetaColumn c = null;
		VSMetaTable t = new VSMetaTable("LeaveEvent");
		//{{META_QUERY_COLUMN_CTOR
		c = new VSMetaColumn("PKLeaveEvent", DataConst.BIGINT);	
		c.setAutoIncrement(true);
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("FKMember", DataConst.BIGINT);	
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNullable);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("TimeStamp", DataConst.DATE);
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNullable);
		c.setDefaultValue("date()");
		c.setCaption("");
		t.addColumn( c );
	
		
		
		// Register a foreign key from  to Member.
		t.addForeignKeyColumn( "Foreign Key To Member", "FKMember" );
	
		
		
	
		t.useQuotedIdentifier(true);
		t.setOptLock( DataConst.OptLockingOnApplicable );
	
		
	
	//END_META_QUERY_COLUMN_CTOR}}
		q.addTable( t );
		q.populateQCArray();
		VSQueryDefinition qTemp = (VSQueryDefinition)getMetaQuery( "LeaveEvent",deployedFromRepository ); 
		if ( qTemp == null ) {			
			addMetaQuery(q, deployedFromRepository);
		}					
		else
			q = qTemp;	// Keep the old query as it has cached object
			
	
		
	
	//END_COMPONENT_META_QRY}}

	//{{COMPONENT_RULES
	








	t.setDerivationType("TimeStamp", VSMetaColumn.derivationDefault);
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
	return "LeaveEvent";
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
* Constructor for the class LeaveEventBaseImpl.
* @param Session session object on which the Business object is created.
* @param boolean if true sets the default values in the data elements
*        as defined in the meta data.
*/
public	LeaveEventBaseImpl(Session session, boolean makeDefaults)
{
	super(session, LeaveEventBaseImpl.getMetaQuery(), makeDefaults);
	addListeners();
}


/**
*	Read DataObject extended property, set the cache size and expiration time for a perticular Object type
*	This should only be done once per class type, use "initialized" as flag.
*/
public static void initCache() {
	if ( initialized ) return ;
		
	AppObjectImpl appObject = AppObjectImpl.getBusinessObject( deployedFromRepository, "LeaveEvent", (Session)ServerEnvironment.getServer().getInternalSession());
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "LeaveEvent", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "LeaveEvent","", (String)null);
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
		Enumeration e= aSession.getTransactionInfo().getObjects(LeaveEventBaseImpl.getMetaQuery(), xdac, searchReq);
		
		// ******************* cache support
		if ( cacheSize!=0 && e.hasMoreElements()) {
			Vector boList = new Vector();
			while ( e.hasMoreElements()) {
				boList.addElement( e.nextElement());
			}
			
			CacheAgent.getCacheAgent().setObjects( LeaveEventBaseImpl.getMetaQuery(), boList, expires );
			
			return boList.elements();
		
		} else
			return e;
	
	} else {
		Vector boList = new Vector();
				Connection con = aSession.getConnection( xdac, true );
		ResultSet rs = new VSORBResultSetImpl( LeaveEventBaseImpl.getMetaQuery(), searchReq, null, -1 ,DataConst.NONE_LOB, xdac, con );
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
			CacheAgent.getCacheAgent().setObjects( LeaveEventBaseImpl.getMetaQuery(), boList, expires );
			
		return boList.elements();
	}
}

public static void raiseBeforeQueryEvent( SearchRequest searchReq, Session aSession )	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"LeaveEvent.BeforeQuery");
	}

	beforeQuery( "LeaveEvent", searchReq, null , aSession );
	
	if (  tr != null ) tr.end( tr_id );
}

public static void raiseAfterQueryEvent( ResultSet rs)	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"LeaveEvent.AfterQuery");
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "LeaveEvent", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "LeaveEvent","", (String)null);
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
		anObject = CacheAgent.getCacheAgent().getObject( LeaveEventBaseImpl.getMetaQuery(),key.parameterList, aSession);
		
		logger.fine(" Cache :"+ anObject);
		
		if (anObject!=null)  {
			//set current session to this cached object, ensure that object has a active session
			anObject.setSession( aSession );
			return anObject;
		}
	}

	if (aSession.isTransactionInProgress()) {
		anObject = aSession.getTransactionInfo().getObjectByKey(LeaveEventBaseImpl.getMetaQuery(),key);
	} else {		
		anObject = aSession.getObjectByKey(LeaveEventBaseImpl.getMetaQuery(),key);
	}
	
	//**************************** cache support
	if ( cacheSize != 0 )
		 CacheAgent.getCacheAgent().addObject( LeaveEventBaseImpl.getMetaQuery(),anObject, expires );
		 
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


	// Generate Columns Default code here.
	if ( isNull("TimeStamp") ) 
		setTimeStamp((VSDate)(getDate( )));
	
	XDAConnector xdac = getXDAConnector();
	// For the counter field set and get the counter value
	Object counter = null;

	if ( xdac instanceof XDASQLConnector ) {
		if ( ( counter = ((XDASQLConnector)xdac).getCounter(LeaveEventBaseImpl.getMetaQuery().getBaseTable("LeaveEvent"), 
		   session.getTransactionInfo().getConnection( xdac), rowData,"PKLeaveEvent" ) )!= null )	
		   this.setPKLeaveEvent( ((Long)counter).longValue());
	}
	else {
		if ( ( counter = xdac.getCounter(LeaveEventBaseImpl.getMetaQuery().getBaseTable("LeaveEvent"), 
		   session.getTransactionInfo().getConnection( xdac), rowData ) )!= null )	
		   this.setPKLeaveEvent( ((Long)counter).longValue());
	}

	// Do the parent checks.
	this.parentCheckFor_Member();


	// Do Column Validation Checks
	this.columnValidationCheck();

	// Do TableConstraintChecks
	this.tableConstraintCheck();

	// Now verify that not nullable columns are not null.

	// At this point all the computations for the object attributes have been made,
	// therefore save the object.	
	this.updateRowImmediate();

	// Factored out duplicate code passage - Val/Paul 03-17-03
	this.tableConditionActions();



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
	if ( getGlobalNestLevel() == 1 && isAltered("PKLeaveEvent") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'PKLeaveEvent' in Object LeaveEvent is not Alterable. Error Column: <LeaveEvent>.<PKLeaveEvent>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("FKMember") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'FKMember' in Object LeaveEvent is not Alterable. Error Column: <LeaveEvent>.<FKMember>");
	}

	// Do the parent checks.
	this.parentCheckFor_Member();

	// Set the formulae values at this point
	this.setFormulaValues();


	// Do Column Validation Checks
	this.columnValidationCheck();

	// Do TableConstraintChecks
	this.tableConstraintCheck();
	// verify that not nullable columns are not null.


	// Compute stored values if not already done

	if ( this.isCurrentEvent("RECOMPUTE_DERIVATIONS") && (getGlobalNestLevel() == 1) ) this.RecomputeDerivations();

	// At this point all the computations for the object attributes have been made
	// therefore save the object.
	this.updateRowImmediate();

	// Factored out duplicate code passage - Val/Paul 03-17-03
	this.tableConditionActions();

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
	// Do the parent checks.
	removeMeFromMemberCache();

	// Do Column Validation Checks
	this.columnValidationCheck();

	// Do TableConstraintChecks
	this.tableConstraintCheck();
	if ( !isCascadeDeletedInDB() )
	this.updateRowImmediate();

	// Factored out duplicate code passage - Val/Paul 03-17-03
	this.tableConditionActions();

	// Do Child Cascades.
 

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

	public void tableConstraintCheck() 
	{
	IVSTrace tr = null;  long tr_id = 0;

	try {
		if ( VSTrace.IS_ON ) {
			tr = VSTrace.get(); 
			tr_id = tr.beg(logger);
			tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"tableConstraintCheck").set(VST_OBJECT_NAME,"LeaveEvent_Member 1");
		}

		if ( ! (getMember( ).getMemberState( ) == 1) ) {
			raiseException("The member is not in the library Error Column: <LeaveEvent>.<FKMember>");
		}
	} catch (Exception ex) {
	 	String dependents[] = { };
		handleConstraintValidationException(ex, "Member 1", dependents);
	} finally { 
		if (tr != null) tr.end( tr_id );
	}
	
	try {
		if ( VSTrace.IS_ON ) {
			tr = VSTrace.get(); 
			tr_id = tr.beg(logger);
			tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"tableConstraintCheck").set(VST_OBJECT_NAME,"LeaveEvent_Member 2");
		}

		if ( ! (getMember( ).getCurrentReservations( ) == 0) ) {
			raiseException("Member still has pending reservations Error Column: <LeaveEvent>.<FKMember>");
		}
	} catch (Exception ex) {
	 	String dependents[] = { };
		handleConstraintValidationException(ex, "Member 2", dependents);
	} finally { 
		if (tr != null) tr.end( tr_id );
	}
	
	try {
		if ( VSTrace.IS_ON ) {
			tr = VSTrace.get(); 
			tr_id = tr.beg(logger);
			tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"tableConstraintCheck").set(VST_OBJECT_NAME,"LeaveEvent_Member 3");
		}

		if ( ! (getMember( ).getCurrentLoans( ) == 0) ) {
			raiseException("Member still has pending loans Error Column: <LeaveEvent>.<FKMember>");
		}
	} catch (Exception ex) {
	 	String dependents[] = { };
		handleConstraintValidationException(ex, "Member 3", dependents);
	} finally { 
		if (tr != null) tr.end( tr_id );
	}
	
	}

public void columnValidationCheck() 
{
}

	protected void parentCheckFor_Member()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentCheckFor_Member").set(VST_OBJECT_NAME,"LeaveEvent");
	}
	
	try {
	
	MemberImpl	parent = null;
	boolean		ParentKeyChanged = false;
	boolean		OrphanChildParenting;
	if (( isInserted() || isUpdated() ) && ( 
	(isChanged("FKMember"))
 ) &&
	((!isNull("FKMember")) ))
	{
		// This would cause the row to be dropped from the old parent if it exist.
		removeMeFromMemberCache();
		parent = this.getMember();
			
		if ( parent == null )
		{
			raiseException("Member not found for LeaveEvent. Error Column: <LeaveEvent>.<FKMember>");
		}
		else
		{
			// Add myself to the new parent cache.
			parent.updateCacheForLeaveEvent(this, false);
		}
		
	}
		
	
	}
	finally { if (  tr != null ) tr.end( tr_id ); }
	}




	

	




/**	
* Invalidates the relationship cache. Called by the system on transaction begin.
*/
public void invalidateNonTransactionCaches() 
{
}

	// Do the parent checks.
public void removeMeFromMemberCache()
{
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Member";
	param.fieldName = "PKMember";
	param.value = getData("FKMember").getPreviousString();
	searchReq.add(param);
	if (getSession().getTransactionInfo().isInCache(MemberImpl.getMetaQuery(), searchReq))
	{
		MemberBaseImpl	parent = this.getOldMember();
		parent.updateCacheForLeaveEvent(this, true);
	}
}

/**	  
* <br>
* method to get the Member object for this LeaveEvent
* this method currently does not support additional conditional params.
* @return Object : the  parent object Member for this LeaveEvent.
*/
public	MemberImpl	getMember()
{
	MemberImpl parent = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Member";
	param.fieldName = "PKMember";
	param.value = getData("FKMember").getString();
	searchReq.add(param);
	parent = (MemberImpl)(MemberBaseImpl.getObjectByKey(searchReq ,getSession()));
	return parent;
}

/**	  
* <br>
* method to set the Member object for this LeaveEvent.
* @param Object : the  parent object Member for this LeaveEvent.
*/
public	void	setMember(MemberImpl parentObj)
{
	this.setFKMember(parentObj.getPKMember());
}


/**	  
* <br>
* method to get the old Member object for this LeaveEvent
* this method currently does not support additional conditional params.
* @return Object : the  old parent object Member for this LeaveEvent.
*/
public	MemberImpl	getOldMember()
{
	MemberImpl parent = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Member";
	param.fieldName = "PKMember";
	param.value = getData("FKMember").getPreviousString();
	searchReq.add(param);
	parent = (MemberImpl)(MemberBaseImpl.getObjectByKey(searchReq ,getSession()));
	return parent;
}


	/**	  
	* <br>
	* method to get the FKMember attribute for the LeaveEvent
	* @return long : the  value of the attribute FKMember as long.
	*/
	public long	getFKMember() 
	{
	return getData("FKMember").getlong();
	}

	/**	  
	* <br>
	* method to set the FKMember attribute for the LeaveEvent
	* @param long : value of the attribute FKMember as long.
	* @return nothing
	*/
	public void	setFKMember(long value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("FKMember");
	dataVal.setlong(value);
	}

	/**	  
	* <br>
	* method to get the old FKMember attribute for the LeaveEvent
	* @return long : the  value of the old attribute FKMember as long.
	*/
	public long	getOldFKMember()
	{
	return getData("FKMember").getPreviouslong();
	}

	/**	  
	* <br>
	* method to get the TimeStamp attribute for the LeaveEvent
	* @return VSDate : the  value of the attribute TimeStamp as VSDate.
	*/
	public VSDate	getTimeStamp() 
	{
	return getData("TimeStamp").getVSDate();
	}

	/**	  
	* <br>
	* method to set the TimeStamp attribute for the LeaveEvent
	* @param VSDate : value of the attribute TimeStamp as VSDate.
	* @return nothing
	*/
	public void	setTimeStamp(VSDate value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("TimeStamp");
	dataVal.setVSDate(value);
	}

	/**	  
	* <br>
	* method to get the old TimeStamp attribute for the LeaveEvent
	* @return VSDate : the  value of the old attribute TimeStamp as VSDate.
	*/
	public VSDate	getOldTimeStamp()
	{
	return getData("TimeStamp").getPreviousVSDate();
	}


/**	  
* <br>
* method to get the PKLeaveEvent attribute for the LeaveEvent
* @return long : the  value of the attribute PKLeaveEvent as long.
*/
public long	getPKLeaveEvent() 
{
	return getData("PKLeaveEvent").getlong();
}

/**	  
* <br>
* method to set the PKLeaveEvent attribute for the LeaveEvent
* @param long : value of the attribute PKLeaveEvent as long.
* @return nothing
*/
public void	setPKLeaveEvent(long value)
{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("PKLeaveEvent");
	dataVal.setlong(value);
}

/**	  
* <br>
* method to get the old PKLeaveEvent attribute for the LeaveEvent
* @return long : the  value of the old attribute PKLeaveEvent as long.
*/
public long	getOldPKLeaveEvent()
{
	return getData("PKLeaveEvent").getPreviouslong();
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
		count = aSession.getTransactionInfo().getObjectsCount(LeaveEventBaseImpl.getMetaQuery(), xdac, searchReq);
	else
	{
		con = aSession.getConnection( xdac, true );
  	  	count = xdac.getRowCount( LeaveEventBaseImpl.getMetaQuery(), searchReq, con );
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
		VSMetaTable table = LeaveEventBaseImpl.getMetaQuery().getChildMostTable();
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
		VSMetaTable table = LeaveEventBaseImpl.getMetaQuery().getChildMostTable();
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
	Properties props = ((VSORBSessionImpl)aSession).getMyDataServiceLoginForObject("LeaveEvent");
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

