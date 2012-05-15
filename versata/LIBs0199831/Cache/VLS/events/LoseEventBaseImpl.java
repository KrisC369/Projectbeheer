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


public abstract class LoseEventBaseImpl extends  DataObject
	
{
	public static Logger logger = Logger.getLogger("LIBs0199831.LoseEvent");
	
	public LoseEventBaseImpl () {
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
		q= new VSQueryDefinition( "LoseEvent" );	
		q.setPackageName(deployedFromRepository);
		
		VSMetaColumn c = null;
		VSMetaTable t = new VSMetaTable("LoseEvent");
		//{{META_QUERY_COLUMN_CTOR
		c = new VSMetaColumn("PKLoseEvent", DataConst.BIGINT);	
		c.setAutoIncrement(true);
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("TimeStamp", DataConst.DATE);
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNullable);
		c.setDefaultValue("date()");
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("FKLoan", DataConst.BIGINT);	
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNullable);
		c.setCaption("");
		t.addColumn( c );
	
		
		
		// Register a foreign key from  to Loan.
		t.addForeignKeyColumn( "Foreign Key To Loan", "FKLoan" );
	
		
		
	
		t.useQuotedIdentifier(true);
		t.setOptLock( DataConst.OptLockingOnApplicable );
	
		
	
	//END_META_QUERY_COLUMN_CTOR}}
		q.addTable( t );
		q.populateQCArray();
		VSQueryDefinition qTemp = (VSQueryDefinition)getMetaQuery( "LoseEvent",deployedFromRepository ); 
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
	return "LoseEvent";
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
* Constructor for the class LoseEventBaseImpl.
* @param Session session object on which the Business object is created.
* @param boolean if true sets the default values in the data elements
*        as defined in the meta data.
*/
public	LoseEventBaseImpl(Session session, boolean makeDefaults)
{
	super(session, LoseEventBaseImpl.getMetaQuery(), makeDefaults);
	addListeners();
}


/**
*	Read DataObject extended property, set the cache size and expiration time for a perticular Object type
*	This should only be done once per class type, use "initialized" as flag.
*/
public static void initCache() {
	if ( initialized ) return ;
		
	AppObjectImpl appObject = AppObjectImpl.getBusinessObject( deployedFromRepository, "LoseEvent", (Session)ServerEnvironment.getServer().getInternalSession());
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "LoseEvent", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "LoseEvent","", (String)null);
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
		Enumeration e= aSession.getTransactionInfo().getObjects(LoseEventBaseImpl.getMetaQuery(), xdac, searchReq);
		
		// ******************* cache support
		if ( cacheSize!=0 && e.hasMoreElements()) {
			Vector boList = new Vector();
			while ( e.hasMoreElements()) {
				boList.addElement( e.nextElement());
			}
			
			CacheAgent.getCacheAgent().setObjects( LoseEventBaseImpl.getMetaQuery(), boList, expires );
			
			return boList.elements();
		
		} else
			return e;
	
	} else {
		Vector boList = new Vector();
				Connection con = aSession.getConnection( xdac, true );
		ResultSet rs = new VSORBResultSetImpl( LoseEventBaseImpl.getMetaQuery(), searchReq, null, -1 ,DataConst.NONE_LOB, xdac, con );
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
			CacheAgent.getCacheAgent().setObjects( LoseEventBaseImpl.getMetaQuery(), boList, expires );
			
		return boList.elements();
	}
}

public static void raiseBeforeQueryEvent( SearchRequest searchReq, Session aSession )	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"LoseEvent.BeforeQuery");
	}

	beforeQuery( "LoseEvent", searchReq, null , aSession );
	
	if (  tr != null ) tr.end( tr_id );
}

public static void raiseAfterQueryEvent( ResultSet rs)	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"LoseEvent.AfterQuery");
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "LoseEvent", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "LoseEvent","", (String)null);
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
		anObject = CacheAgent.getCacheAgent().getObject( LoseEventBaseImpl.getMetaQuery(),key.parameterList, aSession);
		
		logger.fine(" Cache :"+ anObject);
		
		if (anObject!=null)  {
			//set current session to this cached object, ensure that object has a active session
			anObject.setSession( aSession );
			return anObject;
		}
	}

	if (aSession.isTransactionInProgress()) {
		anObject = aSession.getTransactionInfo().getObjectByKey(LoseEventBaseImpl.getMetaQuery(),key);
	} else {		
		anObject = aSession.getObjectByKey(LoseEventBaseImpl.getMetaQuery(),key);
	}
	
	//**************************** cache support
	if ( cacheSize != 0 )
		 CacheAgent.getCacheAgent().addObject( LoseEventBaseImpl.getMetaQuery(),anObject, expires );
		 
	return anObject;
}





// Factored out duplicate code passage - Val/Paul 03-17-03
protected void tableConditionActions() {
		
	// Generate code for conditional action events.
	
	IVSTrace tr = null;  
	long tr_id = 0;

	
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,"VST_RULE_CONDITIONACTION").set(VST_ACTION_NAME, this.getClass().getName() + "_loseCopy");
	}
	try {
	
		if (  ( isInserted() == true )  )
		{
			getLoan( ).getCopy( ).setCopyState(6);
		}
	} finally {
		if ( tr != null ) tr.end( tr_id );
	}
	
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,"VST_RULE_CONDITIONACTION").set(VST_ACTION_NAME, this.getClass().getName() + "_loseLoan");
	}
	try {
	
		if (  ( isInserted() == true )  )
		{
			getLoan( ).setLoanState(3);
		}
	} finally {
		if ( tr != null ) tr.end( tr_id );
	}
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
		if ( ( counter = ((XDASQLConnector)xdac).getCounter(LoseEventBaseImpl.getMetaQuery().getBaseTable("LoseEvent"), 
		   session.getTransactionInfo().getConnection( xdac), rowData,"PKLoseEvent" ) )!= null )	
		   this.setPKLoseEvent( ((Long)counter).longValue());
	}
	else {
		if ( ( counter = xdac.getCounter(LoseEventBaseImpl.getMetaQuery().getBaseTable("LoseEvent"), 
		   session.getTransactionInfo().getConnection( xdac), rowData ) )!= null )	
		   this.setPKLoseEvent( ((Long)counter).longValue());
	}

	// Do the parent checks.
	this.parentCheckFor_Loan();


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
	if ( getGlobalNestLevel() == 1 && isAltered("FKLoan") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'FKLoan' in Object LoseEvent is not Alterable. Error Column: <LoseEvent>.<FKLoan>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("PKLoseEvent") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'PKLoseEvent' in Object LoseEvent is not Alterable. Error Column: <LoseEvent>.<PKLoseEvent>");
	}

	// Do the parent checks.
	this.parentCheckFor_Loan();

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
	removeMeFromLoanCache();

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
			tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"tableConstraintCheck").set(VST_OBJECT_NAME,"LoseEvent_Copy 1");
		}

		if ( ! (getLoan( ).getCopy( ).getCopyState( ) == 3 || getLoan( ).getCopy( ).getCopyState( ) == 4) ) {
			raiseException("The copy does not exist anymore Error Column: <LoseEvent>.<FKLoan>");
		}
	} catch (Exception ex) {
	 	String dependents[] = { };
		handleConstraintValidationException(ex, "Copy 1", dependents);
	} finally { 
		if (tr != null) tr.end( tr_id );
	}
	
	try {
		if ( VSTrace.IS_ON ) {
			tr = VSTrace.get(); 
			tr_id = tr.beg(logger);
			tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"tableConstraintCheck").set(VST_OBJECT_NAME,"LoseEvent_Copy 2");
		}

		if ( ! (getLoan( ).getCopy( ).getCurrentReservations( ) == 0) ) {
			raiseException("Pending reservations still remain Error Column: <LoseEvent>.<FKLoan>");
		}
	} catch (Exception ex) {
	 	String dependents[] = { };
		handleConstraintValidationException(ex, "Copy 2", dependents);
	} finally { 
		if (tr != null) tr.end( tr_id );
	}
	
	try {
		if ( VSTrace.IS_ON ) {
			tr = VSTrace.get(); 
			tr_id = tr.beg(logger);
			tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"tableConstraintCheck").set(VST_OBJECT_NAME,"LoseEvent_Member 1");
		}

		if ( ! (getLoan( ).getMember( ).getMemberState( ) == 1) ) {
			raiseException("Invalid member Error Column: <LoseEvent>.<FKLoan>");
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
			tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"tableConstraintCheck").set(VST_OBJECT_NAME,"LoseEvent_Loan 1");
		}

		if ( ! (getLoan( ).getLoanState( ) == 1) ) {
			raiseException("This loan is not active. Error Column: <LoseEvent>.<FKLoan>");
		}
	} catch (Exception ex) {
	 	String dependents[] = { };
		handleConstraintValidationException(ex, "Loan 1", dependents);
	} finally { 
		if (tr != null) tr.end( tr_id );
	}
	
	}

public void columnValidationCheck() 
{
}

	protected void parentCheckFor_Loan()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentCheckFor_Loan").set(VST_OBJECT_NAME,"LoseEvent");
	}
	
	try {
	
	LoanImpl	parent = null;
	boolean		ParentKeyChanged = false;
	boolean		OrphanChildParenting;
	if (( isInserted() || isUpdated() ) && ( 
	(isChanged("FKLoan"))
 ) &&
	((!isNull("FKLoan")) ))
	{
		// This would cause the row to be dropped from the old parent if it exist.
		removeMeFromLoanCache();
		parent = this.getLoan();
			
		if ( parent == null )
		{
			raiseException("Loan not found for LoseEvent. Error Column: <LoseEvent>.<FKLoan>");
		}
		else
		{
			// Add myself to the new parent cache.
			parent.updateCacheForLoseEvent(this, false);
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
public void removeMeFromLoanCache()
{
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Loan";
	param.fieldName = "PKLoan";
	param.value = getData("FKLoan").getPreviousString();
	searchReq.add(param);
	if (getSession().getTransactionInfo().isInCache(LoanImpl.getMetaQuery(), searchReq))
	{
		LoanBaseImpl	parent = this.getOldLoan();
		parent.updateCacheForLoseEvent(this, true);
	}
}

/**	  
* <br>
* method to get the Loan object for this LoseEvent
* this method currently does not support additional conditional params.
* @return Object : the  parent object Loan for this LoseEvent.
*/
public	LoanImpl	getLoan()
{
	LoanImpl parent = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Loan";
	param.fieldName = "PKLoan";
	param.value = getData("FKLoan").getString();
	searchReq.add(param);
	parent = (LoanImpl)(LoanBaseImpl.getObjectByKey(searchReq ,getSession()));
	return parent;
}

/**	  
* <br>
* method to set the Loan object for this LoseEvent.
* @param Object : the  parent object Loan for this LoseEvent.
*/
public	void	setLoan(LoanImpl parentObj)
{
	this.setFKLoan(parentObj.getPKLoan());
}


/**	  
* <br>
* method to get the old Loan object for this LoseEvent
* this method currently does not support additional conditional params.
* @return Object : the  old parent object Loan for this LoseEvent.
*/
public	LoanImpl	getOldLoan()
{
	LoanImpl parent = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Loan";
	param.fieldName = "PKLoan";
	param.value = getData("FKLoan").getPreviousString();
	searchReq.add(param);
	parent = (LoanImpl)(LoanBaseImpl.getObjectByKey(searchReq ,getSession()));
	return parent;
}


	/**	  
	* <br>
	* method to get the FKLoan attribute for the LoseEvent
	* @return long : the  value of the attribute FKLoan as long.
	*/
	public long	getFKLoan() 
	{
	return getData("FKLoan").getlong();
	}

	/**	  
	* <br>
	* method to set the FKLoan attribute for the LoseEvent
	* @param long : value of the attribute FKLoan as long.
	* @return nothing
	*/
	public void	setFKLoan(long value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("FKLoan");
	dataVal.setlong(value);
	}

	/**	  
	* <br>
	* method to get the old FKLoan attribute for the LoseEvent
	* @return long : the  value of the old attribute FKLoan as long.
	*/
	public long	getOldFKLoan()
	{
	return getData("FKLoan").getPreviouslong();
	}

	/**	  
	* <br>
	* method to get the TimeStamp attribute for the LoseEvent
	* @return VSDate : the  value of the attribute TimeStamp as VSDate.
	*/
	public VSDate	getTimeStamp() 
	{
	return getData("TimeStamp").getVSDate();
	}

	/**	  
	* <br>
	* method to set the TimeStamp attribute for the LoseEvent
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
	* method to get the old TimeStamp attribute for the LoseEvent
	* @return VSDate : the  value of the old attribute TimeStamp as VSDate.
	*/
	public VSDate	getOldTimeStamp()
	{
	return getData("TimeStamp").getPreviousVSDate();
	}


/**	  
* <br>
* method to get the PKLoseEvent attribute for the LoseEvent
* @return long : the  value of the attribute PKLoseEvent as long.
*/
public long	getPKLoseEvent() 
{
	return getData("PKLoseEvent").getlong();
}

/**	  
* <br>
* method to set the PKLoseEvent attribute for the LoseEvent
* @param long : value of the attribute PKLoseEvent as long.
* @return nothing
*/
public void	setPKLoseEvent(long value)
{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("PKLoseEvent");
	dataVal.setlong(value);
}

/**	  
* <br>
* method to get the old PKLoseEvent attribute for the LoseEvent
* @return long : the  value of the old attribute PKLoseEvent as long.
*/
public long	getOldPKLoseEvent()
{
	return getData("PKLoseEvent").getPreviouslong();
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
		count = aSession.getTransactionInfo().getObjectsCount(LoseEventBaseImpl.getMetaQuery(), xdac, searchReq);
	else
	{
		con = aSession.getConnection( xdac, true );
  	  	count = xdac.getRowCount( LoseEventBaseImpl.getMetaQuery(), searchReq, con );
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
		VSMetaTable table = LoseEventBaseImpl.getMetaQuery().getChildMostTable();
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
		VSMetaTable table = LoseEventBaseImpl.getMetaQuery().getChildMostTable();
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
	Properties props = ((VSORBSessionImpl)aSession).getMyDataServiceLoginForObject("LoseEvent");
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

