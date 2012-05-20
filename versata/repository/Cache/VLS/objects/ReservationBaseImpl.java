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


public abstract class ReservationBaseImpl extends  DataObject
	
{
	public static Logger logger = Logger.getLogger("LIBs0199831.Reservation");
	
	public ReservationBaseImpl () {
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
		q= new VSQueryDefinition( "Reservation" );	
		q.setPackageName(deployedFromRepository);
		
		VSMetaColumn c = null;
		VSMetaTable t = new VSMetaTable("Reservation");
		//{{META_QUERY_COLUMN_CTOR
		c = new VSMetaColumn("PKReservation", DataConst.BIGINT);	
		c.setAutoIncrement(true);
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("FKMember", DataConst.BIGINT);	
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("FKCopy", DataConst.BIGINT);	
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("StartDate", DataConst.DATE);
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("EndDate", DataConst.DATE);
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("ReservationState", DataConst.INTEGER);
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		
		t.addUniqueKeyColumn( "Reservation Unique Key", "PKReservation" );
		
		// Register a foreign key from  to Copy.
		t.addForeignKeyColumn( "Foreign Key To Copy", "FKCopy" );
	
		// Register a foreign key from  to Member.
		t.addForeignKeyColumn( "Foreign Key To Member", "FKMember" );
	
		
		
	
		t.useQuotedIdentifier(true);
		t.setOptLock( DataConst.OptLockingOnApplicable );
	
		
	
	//END_META_QUERY_COLUMN_CTOR}}
		q.addTable( t );
		q.populateQCArray();
		VSQueryDefinition qTemp = (VSQueryDefinition)getMetaQuery( "Reservation",deployedFromRepository ); 
		if ( qTemp == null ) {			
			addMetaQuery(q, deployedFromRepository);
		}					
		else
			q = qTemp;	// Keep the old query as it has cached object
			
	
		
	
	//END_COMPONENT_META_QRY}}

	//{{COMPONENT_RULES
	














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
	return "Reservation";
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
* Constructor for the class ReservationBaseImpl.
* @param Session session object on which the Business object is created.
* @param boolean if true sets the default values in the data elements
*        as defined in the meta data.
*/
public	ReservationBaseImpl(Session session, boolean makeDefaults)
{
	super(session, ReservationBaseImpl.getMetaQuery(), makeDefaults);
	addListeners();
}


/**
*	Read DataObject extended property, set the cache size and expiration time for a perticular Object type
*	This should only be done once per class type, use "initialized" as flag.
*/
public static void initCache() {
	if ( initialized ) return ;
		
	AppObjectImpl appObject = AppObjectImpl.getBusinessObject( deployedFromRepository, "Reservation", (Session)ServerEnvironment.getServer().getInternalSession());
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "Reservation", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "Reservation","", (String)null);
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
		Enumeration e= aSession.getTransactionInfo().getObjects(ReservationBaseImpl.getMetaQuery(), xdac, searchReq);
		
		// ******************* cache support
		if ( cacheSize!=0 && e.hasMoreElements()) {
			Vector boList = new Vector();
			while ( e.hasMoreElements()) {
				boList.addElement( e.nextElement());
			}
			
			CacheAgent.getCacheAgent().setObjects( ReservationBaseImpl.getMetaQuery(), boList, expires );
			
			return boList.elements();
		
		} else
			return e;
	
	} else {
		Vector boList = new Vector();
				Connection con = aSession.getConnection( xdac, true );
		ResultSet rs = new VSORBResultSetImpl( ReservationBaseImpl.getMetaQuery(), searchReq, null, -1 ,DataConst.NONE_LOB, xdac, con );
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
			CacheAgent.getCacheAgent().setObjects( ReservationBaseImpl.getMetaQuery(), boList, expires );
			
		return boList.elements();
	}
}

public static void raiseBeforeQueryEvent( SearchRequest searchReq, Session aSession )	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"Reservation.BeforeQuery");
	}

	beforeQuery( "Reservation", searchReq, null , aSession );
	
	if (  tr != null ) tr.end( tr_id );
}

public static void raiseAfterQueryEvent( ResultSet rs)	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"Reservation.AfterQuery");
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "Reservation", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "Reservation","", (String)null);
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
		anObject = CacheAgent.getCacheAgent().getObject( ReservationBaseImpl.getMetaQuery(),key.parameterList, aSession);
		
		logger.fine(" Cache :"+ anObject);
		
		if (anObject!=null)  {
			//set current session to this cached object, ensure that object has a active session
			anObject.setSession( aSession );
			return anObject;
		}
	}

	if (aSession.isTransactionInProgress()) {
		anObject = aSession.getTransactionInfo().getObjectByKey(ReservationBaseImpl.getMetaQuery(),key);
	} else {		
		anObject = aSession.getObjectByKey(ReservationBaseImpl.getMetaQuery(),key);
	}
	
	//**************************** cache support
	if ( cacheSize != 0 )
		 CacheAgent.getCacheAgent().addObject( ReservationBaseImpl.getMetaQuery(),anObject, expires );
		 
	return anObject;
}




	/**
	 * Static method to get the where clause for the count column CurrentReservations
	 * @return String the where clause as SQL statement. For Non SQL business objects a
	 * corresponding non sql method has to be replaced.
	 */
	public static String	getWhereClauseForCopyCurrentReservations()
	{
		return ("\"ReservationState\" = 1");
	}
	/**
	 * Static method to get the where clause for the count column CurrentReservations
	 * @return String the where clause as SQL statement. For Non SQL business objects a
	 * corresponding non sql method has to be replaced.
	 */
	public static String	getWhereClauseForMemberCurrentReservations()
	{
		return ("\"ReservationState\" = 1");
	}

// Factored out duplicate code passage - Val/Paul 03-17-03
protected void tableConditionActions() {
}


protected void insert() throws ServerException
{
	// Post Rule Event
	postRuleEvent(VLSEvent.BEFORE_INSERT, response.reset());
	
	if (response.rejected()) return;


	
	XDAConnector xdac = getXDAConnector();
	// For the counter field set and get the counter value
	Object counter = null;

	if ( xdac instanceof XDASQLConnector ) {
		if ( ( counter = ((XDASQLConnector)xdac).getCounter(ReservationBaseImpl.getMetaQuery().getBaseTable("Reservation"), 
		   session.getTransactionInfo().getConnection( xdac), rowData,"PKReservation" ) )!= null )	
		   this.setPKReservation( ((Long)counter).longValue());
	}
	else {
		if ( ( counter = xdac.getCounter(ReservationBaseImpl.getMetaQuery().getBaseTable("Reservation"), 
		   session.getTransactionInfo().getConnection( xdac), rowData ) )!= null )	
		   this.setPKReservation( ((Long)counter).longValue());
	}

	// Do the parent checks.
	this.parentCheckFor_Copy();
	this.parentCheckFor_Member();


	// Do Column Validation Checks
	this.columnValidationCheck();

	// Do TableConstraintChecks

	// Now verify that not nullable columns are not null.
	// At this point all the computations for the object attributes have been made,
	// therefore save the object.	
	this.updateRowImmediate();

	// Factored out duplicate code passage - Val/Paul 03-17-03
	this.tableConditionActions();


	// Do Child Cascades.
	// Child Cascade for Role pron(Reservation)-FetchEvent(FetchEvent): Reservation->>FetchEvent
	this.childCascadeFor_FetchEvent();
	// Child Cascade for Role pron(Reservation)-ReserveEvent(ReserveEvent): Reservation->>ReserveEvent
	this.childCascadeFor_ReserveEvent();
	// Child Cascade for Role pron(Reservation)-CancelEvent(CancelEvent): Reservation->>CancelEvent
	this.childCascadeFor_CancelEvent();
	// Do the Parent Adjustment.
	// Parent Adjustment for Role cr)-Reservation(Reservation) : Reservation->> Copy
	this.parentAdjustmentFor_Copy();
	// Parent Adjustment for Role crmber)-Reservation(Reservation) : Reservation->> Member
	this.parentAdjustmentFor_Member();

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
	if ( getGlobalNestLevel() == 1 && isAltered("ReservationState") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'ReservationState' in Object Reservation is not Alterable. Error Column: <Reservation>.<ReservationState>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("FKCopy") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'FKCopy' in Object Reservation is not Alterable. Error Column: <Reservation>.<FKCopy>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("PKReservation") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'PKReservation' in Object Reservation is not Alterable. Error Column: <Reservation>.<PKReservation>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("FKMember") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'FKMember' in Object Reservation is not Alterable. Error Column: <Reservation>.<FKMember>");
	}

	// Do the parent checks.
	this.parentCheckFor_Copy();
	this.parentCheckFor_Member();

	// Set the formulae values at this point
	this.setFormulaValues();


	// Do Column Validation Checks
	this.columnValidationCheck();

	// verify that not nullable columns are not null.

	// Compute stored values if not already done

	if ( this.isCurrentEvent("RECOMPUTE_DERIVATIONS") && (getGlobalNestLevel() == 1) ) this.RecomputeDerivations();

	// At this point all the computations for the object attributes have been made
	// therefore save the object.
	this.updateRowImmediate();

	// Factored out duplicate code passage - Val/Paul 03-17-03
	this.tableConditionActions();

	// Do Child Cascades.
	// Child Cascade for Role pron(Reservation)-FetchEvent(FetchEvent): Reservation->>FetchEvent
	this.childCascadeFor_FetchEvent();
	// Child Cascade for Role pron(Reservation)-ReserveEvent(ReserveEvent): Reservation->>ReserveEvent
	this.childCascadeFor_ReserveEvent();
	// Child Cascade for Role pron(Reservation)-CancelEvent(CancelEvent): Reservation->>CancelEvent
	this.childCascadeFor_CancelEvent();
	// Do the Parent Adjustment.
	// Parent Adjustment for Role cr)-Reservation(Reservation) : Reservation->> Copy
	this.parentAdjustmentFor_Copy();
	// Parent Adjustment for Role crmber)-Reservation(Reservation) : Reservation->> Member
	this.parentAdjustmentFor_Member();
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
	removeMeFromCopyCache();
	removeMeFromMemberCache();

	// Do Column Validation Checks
	this.columnValidationCheck();

	// Do TableConstraintChecks
	if ( !isCascadeDeletedInDB() )
	this.updateRowImmediate();

	// Factored out duplicate code passage - Val/Paul 03-17-03
	this.tableConditionActions();

	// Do Child Cascades.
	// Child Cascade for Role pron(Reservation)-FetchEvent(FetchEvent): Reservation->>FetchEvent
	this.childCascadeFor_FetchEvent();
	// Child Cascade for Role pron(Reservation)-ReserveEvent(ReserveEvent): Reservation->>ReserveEvent
	this.childCascadeFor_ReserveEvent();
	// Child Cascade for Role pron(Reservation)-CancelEvent(CancelEvent): Reservation->>CancelEvent
	this.childCascadeFor_CancelEvent();
 

	// Do the Parent Adjustment.
	// Parent Adjustment for Role cr)-Reservation(Reservation) : Reservation->> Copy
	this.parentAdjustmentFor_Copy();
	// Parent Adjustment for Role crmber)-Reservation(Reservation) : Reservation->> Member
	this.parentAdjustmentFor_Member();

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

	protected void parentCheckFor_Copy()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentCheckFor_Copy").set(VST_OBJECT_NAME,"Reservation");
	}
	
	try {
	
	CopyImpl	parent = null;
	boolean		ParentKeyChanged = false;
	boolean		OrphanChildParenting;
	if (( isInserted() || isUpdated() ) && ( 
	(isChanged("FKCopy"))
 ) &&
	((!isNull("FKCopy")) ))
	{
		// This would cause the row to be dropped from the old parent if it exist.
		removeMeFromCopyCache();
		parent = this.getCopy();
			
		if ( parent == null )
		{
			raiseException("Copy not found for Reservation. Error Column: <Reservation>.<FKCopy>");
		}
		else
		{
			// Add myself to the new parent cache.
			parent.updateCacheForReservation(this, false);
		}
		
	}
		
	
	}
	finally { if (  tr != null ) tr.end( tr_id ); }
	}
	protected void parentCheckFor_Member()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentCheckFor_Member").set(VST_OBJECT_NAME,"Reservation");
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
			raiseException("Member not found for Reservation. Error Column: <Reservation>.<FKMember>");
		}
		else
		{
			// Add myself to the new parent cache.
			parent.updateCacheForReservation(this, false);
		}
		
	}
		
	
	}
	finally { if (  tr != null ) tr.end( tr_id ); }
	}

	protected void childCascadeFor_FetchEvent()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_FetchEvent").set(VST_OBJECT_NAME,"Reservation");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKReservation")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldFetchEvent(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are FetchEvent found for Reservation");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldFetchEvent();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are FetchEvent found for Reservation");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}
	protected void childCascadeFor_ReserveEvent()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_ReserveEvent").set(VST_OBJECT_NAME,"Reservation");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKReservation")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldReserveEvent(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are ReserveEvent found for Reservation");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldReserveEvent();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are ReserveEvent found for Reservation");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}
	protected void childCascadeFor_CancelEvent()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_CancelEvent").set(VST_OBJECT_NAME,"Reservation");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKReservation")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldCancelEvent(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are CancelEvent found for Reservation");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldCancelEvent();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are CancelEvent found for Reservation");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}

	protected void parentAdjustmentFor_Copy()
	{
	
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentAdjustmentFor_Copy").set(VST_OBJECT_NAME,"Reservation");
	}
	
	try {		// make sure to stop trace
	
	DataObjectWrapper	oldParent = new DataObjectWrapper();
	DataObjectWrapper	newParent = new DataObjectWrapper();

	boolean	childCascadeUpdate = false;
	boolean	childCascadeDelete = false;
	boolean	orphanChildParenting = false;
	boolean 	childReparented	= false;

	if ( isUpdated() && (isChanged("FKCopy") ) && ( (!(isOldNull("FKCopy")))) )
	{
		if ( !oldParent.isInitialized() )
			oldParent.setDataObject(this.getOldCopy());
		if ( ! oldParent.isObjNull() ) {
			childCascadeUpdate = false;
			childReparented = true;
		}
		else {
			childCascadeUpdate = true;
			childReparented = false;
		}
	}

	if ( isUpdated() && (isChanged("FKCopy") ) && ( ! ( (!(isOldNull("FKCopy")))) ) )
	{
		if ( !newParent.isInitialized() )
			newParent.setDataObject(this.getCopy());
		if ( ! newParent.isObjNull() ) orphanChildParenting = true;
		else orphanChildParenting = false;
	}

	if ( isDeleted() && ((!(isOldNull("FKCopy"))) ))
	{
		if ( !oldParent.isInitialized() )
			oldParent.setDataObject(this.getOldCopy());
		if ( ! oldParent.isObjNull() ) childCascadeDelete = false;
		else childCascadeDelete = true;
	}
 
	parentAdjustmentFor_CopyCurrentReservations(oldParent, newParent, childCascadeUpdate, childCascadeDelete, orphanChildParenting, childReparented);
	if ( ! oldParent.isObjNull() )
	{
		if ( oldParent.getDataObject().getDirty() )
		{
			oldParent.getDataObject().setUpdate();
			oldParent.getDataObject().executeRules();
		}
	}
	if ( ! newParent.isObjNull() )
	{
		if ( newParent.getDataObject().getDirty() )
		{
			newParent.getDataObject().setUpdate();
			newParent.getDataObject().executeRules();
		}
	}
	
	}
	finally { if ( tr != null ) tr.end( tr_id ); }
	}

	

protected void parentAdjustmentFor_CopyCurrentReservations( DataObjectWrapper oldParent, DataObjectWrapper newParent, boolean childCascadeUpdate, boolean childCascadeDelete, boolean orphanChildParenting, boolean childReparented)
{
	
	try { // trap null pointer exceptions and analyze
	if ( isInserted() )
	{
		// For the case of insertion it does not matter If the columns involved
		// for both the where clause and the from column in child are virtual.
		// simply set the deltas to new values and mark them as usable
		if (this.getReservationState() == 1) 
		{
			if ( !newParent.isInitialized() )
				newParent.setDataObject(this.getCopy());
			if ( ! newParent.isObjNull() )
				newParent.getDataObject().setAdjust("CurrentReservations", 1, true);
			else
			{
				// Raise an error here
			}
		}
		return;
	}

	if ( isDeleted() && ( ! childCascadeDelete ) )
	{
		if (this.getOldReservationState() == 1) 
		{
			if ( !oldParent.isInitialized() )
				oldParent.setDataObject(this.getOldCopy());
			if ( ! oldParent.isObjNull() )
				oldParent.getDataObject().setAdjust("CurrentReservations",-1,true);
			else
			{
				// Raise an error here
			}
		}
		return;
	}

	if ( childCascadeUpdate ) 
	{
		int	addValue = 0;
		int	subValue = 0;
		if (this.getReservationState() == 1) 
		{
			addValue = 1;
		}
		if (this.getOldReservationState() == 1)
		{
			subValue = 1;
		}
		int	delta;
		delta = addValue - subValue;
		if ( delta != 0 )
		{
			if ( !newParent.isInitialized() )
				newParent.setDataObject(this.getCopy());
			if ( ! newParent.isObjNull() )
				newParent.getDataObject().setAdjust("CurrentReservations",delta, true);
			else
			{
				// Raise an error here
			}
		}
		return;
	}
	else if ( orphanChildParenting )
	{
		if (this.getReservationState() == 1) 
		{
			if ( !newParent.isInitialized() )
				newParent.setDataObject(this.getCopy());
			if ( ! newParent.isObjNull() )
				newParent.getDataObject().setAdjust("CurrentReservations",1,true);
			else
			{
				// Raise an error here
			}
		}
		return;
	}
	else if ( childReparented )
	{
 
		if (this.getReservationState() == 1) 
		{
			if ( !newParent.isInitialized() )
				newParent.setDataObject(this.getCopy());
			if ( ! newParent.isObjNull() )
				newParent.getDataObject().setAdjust("CurrentReservations",1,true);
			else
			{
				// Raise an error here
			}
		}

		if (this.getOldReservationState() == 1) 
		{
			if ( !oldParent.isInitialized() )
				oldParent.setDataObject(this.getOldCopy());
			if ( ! oldParent.isObjNull() )
				oldParent.getDataObject().setAdjust("CurrentReservations",-1,true);
			else
			{
				// Raise an error here
			}
		}
		return;
	}
	else
	{
		// The regular update with no change in FKey.
		if ( isUpdated())
		{
			int	addValue	= 0;
			int	subValue	= 0;
			if (this.getReservationState() == 1) 
			{
				addValue = 1;
			}
			if (this.getOldReservationState() == 1)
			{
				subValue = 1;
			}
				
			int	delta;
			delta = addValue - subValue;
			if ( delta != 0 ) 
			{
				if ( !oldParent.isInitialized() )
					oldParent.setDataObject(this.getOldCopy());
				if ( ! oldParent.isObjNull() )
					oldParent.getDataObject().setAdjust("CurrentReservations",delta, true);
				else
				{
					// Raise an error here
				}
			}
			return;
		}
	}
	} catch (Exception ex) {
	 	String dependents[] = {		 "ReservationState"
 };
		handleAggregateQualificationException(ex, "CurrentReservations", "Copy", dependents);
	}	

}
	protected void parentAdjustmentFor_Member()
	{
	
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentAdjustmentFor_Member").set(VST_OBJECT_NAME,"Reservation");
	}
	
	try {		// make sure to stop trace
	
	DataObjectWrapper	oldParent = new DataObjectWrapper();
	DataObjectWrapper	newParent = new DataObjectWrapper();

	boolean	childCascadeUpdate = false;
	boolean	childCascadeDelete = false;
	boolean	orphanChildParenting = false;
	boolean 	childReparented	= false;

	if ( isUpdated() && (isChanged("FKMember") ) && ( (!(isOldNull("FKMember")))) )
	{
		if ( !oldParent.isInitialized() )
			oldParent.setDataObject(this.getOldMember());
		if ( ! oldParent.isObjNull() ) {
			childCascadeUpdate = false;
			childReparented = true;
		}
		else {
			childCascadeUpdate = true;
			childReparented = false;
		}
	}

	if ( isUpdated() && (isChanged("FKMember") ) && ( ! ( (!(isOldNull("FKMember")))) ) )
	{
		if ( !newParent.isInitialized() )
			newParent.setDataObject(this.getMember());
		if ( ! newParent.isObjNull() ) orphanChildParenting = true;
		else orphanChildParenting = false;
	}

	if ( isDeleted() && ((!(isOldNull("FKMember"))) ))
	{
		if ( !oldParent.isInitialized() )
			oldParent.setDataObject(this.getOldMember());
		if ( ! oldParent.isObjNull() ) childCascadeDelete = false;
		else childCascadeDelete = true;
	}
 
	parentAdjustmentFor_MemberCurrentReservations(oldParent, newParent, childCascadeUpdate, childCascadeDelete, orphanChildParenting, childReparented);
	if ( ! oldParent.isObjNull() )
	{
		if ( oldParent.getDataObject().getDirty() )
		{
			oldParent.getDataObject().setUpdate();
			oldParent.getDataObject().executeRules();
		}
	}
	if ( ! newParent.isObjNull() )
	{
		if ( newParent.getDataObject().getDirty() )
		{
			newParent.getDataObject().setUpdate();
			newParent.getDataObject().executeRules();
		}
	}
	
	}
	finally { if ( tr != null ) tr.end( tr_id ); }
	}

	

protected void parentAdjustmentFor_MemberCurrentReservations( DataObjectWrapper oldParent, DataObjectWrapper newParent, boolean childCascadeUpdate, boolean childCascadeDelete, boolean orphanChildParenting, boolean childReparented)
{
	
	try { // trap null pointer exceptions and analyze
	if ( isInserted() )
	{
		// For the case of insertion it does not matter If the columns involved
		// for both the where clause and the from column in child are virtual.
		// simply set the deltas to new values and mark them as usable
		if (this.getReservationState() == 1) 
		{
			if ( !newParent.isInitialized() )
				newParent.setDataObject(this.getMember());
			if ( ! newParent.isObjNull() )
				newParent.getDataObject().setAdjust("CurrentReservations", 1, true);
			else
			{
				// Raise an error here
			}
		}
		return;
	}

	if ( isDeleted() && ( ! childCascadeDelete ) )
	{
		if (this.getOldReservationState() == 1) 
		{
			if ( !oldParent.isInitialized() )
				oldParent.setDataObject(this.getOldMember());
			if ( ! oldParent.isObjNull() )
				oldParent.getDataObject().setAdjust("CurrentReservations",-1,true);
			else
			{
				// Raise an error here
			}
		}
		return;
	}

	if ( childCascadeUpdate ) 
	{
		int	addValue = 0;
		int	subValue = 0;
		if (this.getReservationState() == 1) 
		{
			addValue = 1;
		}
		if (this.getOldReservationState() == 1)
		{
			subValue = 1;
		}
		int	delta;
		delta = addValue - subValue;
		if ( delta != 0 )
		{
			if ( !newParent.isInitialized() )
				newParent.setDataObject(this.getMember());
			if ( ! newParent.isObjNull() )
				newParent.getDataObject().setAdjust("CurrentReservations",delta, true);
			else
			{
				// Raise an error here
			}
		}
		return;
	}
	else if ( orphanChildParenting )
	{
		if (this.getReservationState() == 1) 
		{
			if ( !newParent.isInitialized() )
				newParent.setDataObject(this.getMember());
			if ( ! newParent.isObjNull() )
				newParent.getDataObject().setAdjust("CurrentReservations",1,true);
			else
			{
				// Raise an error here
			}
		}
		return;
	}
	else if ( childReparented )
	{
 
		if (this.getReservationState() == 1) 
		{
			if ( !newParent.isInitialized() )
				newParent.setDataObject(this.getMember());
			if ( ! newParent.isObjNull() )
				newParent.getDataObject().setAdjust("CurrentReservations",1,true);
			else
			{
				// Raise an error here
			}
		}

		if (this.getOldReservationState() == 1) 
		{
			if ( !oldParent.isInitialized() )
				oldParent.setDataObject(this.getOldMember());
			if ( ! oldParent.isObjNull() )
				oldParent.getDataObject().setAdjust("CurrentReservations",-1,true);
			else
			{
				// Raise an error here
			}
		}
		return;
	}
	else
	{
		// The regular update with no change in FKey.
		if ( isUpdated())
		{
			int	addValue	= 0;
			int	subValue	= 0;
			if (this.getReservationState() == 1) 
			{
				addValue = 1;
			}
			if (this.getOldReservationState() == 1)
			{
				subValue = 1;
			}
				
			int	delta;
			delta = addValue - subValue;
			if ( delta != 0 ) 
			{
				if ( !oldParent.isInitialized() )
					oldParent.setDataObject(this.getOldMember());
				if ( ! oldParent.isObjNull() )
					oldParent.getDataObject().setAdjust("CurrentReservations",delta, true);
				else
				{
					// Raise an error here
				}
			}
			return;
		}
	}
	} catch (Exception ex) {
	 	String dependents[] = {		 "ReservationState"
 };
		handleAggregateQualificationException(ex, "CurrentReservations", "Member", dependents);
	}	

}


	
	

	

private ObjectHashtable FetchEventCache = null;

/**	  
* <br>
* method to retrieve the FetchEvent objects for this Reservation
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of FetchEvent objects.
*/
public Enumeration getFetchEvent()
{
	if (!getSession().getProperty("NoCacheFetchEvent").equals("true"))
	{
		if ( FetchEventCache != null ) return FetchEventCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "FetchEvent";
	param.fieldName = "FKReservation";
	param.value = getData("PKReservation").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheFetchEvent").equals("true"))
	{
		FetchEventCache = new ObjectHashtable();
		for (Enumeration e = (FetchEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			FetchEventCache.put(pkey,cacheBO);								
		}
		return (FetchEventCache.elements());
	}
	else
	{
		return (FetchEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}
private ObjectHashtable ReserveEventCache = null;

/**	  
* <br>
* method to retrieve the ReserveEvent objects for this Reservation
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of ReserveEvent objects.
*/
public Enumeration getReserveEvent()
{
	if (!getSession().getProperty("NoCacheReserveEvent").equals("true"))
	{
		if ( ReserveEventCache != null ) return ReserveEventCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "ReserveEvent";
	param.fieldName = "FKReservation";
	param.value = getData("PKReservation").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheReserveEvent").equals("true"))
	{
		ReserveEventCache = new ObjectHashtable();
		for (Enumeration e = (ReserveEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			ReserveEventCache.put(pkey,cacheBO);								
		}
		return (ReserveEventCache.elements());
	}
	else
	{
		return (ReserveEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}
private ObjectHashtable CancelEventCache = null;

/**	  
* <br>
* method to retrieve the CancelEvent objects for this Reservation
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of CancelEvent objects.
*/
public Enumeration getCancelEvent()
{
	if (!getSession().getProperty("NoCacheCancelEvent").equals("true"))
	{
		if ( CancelEventCache != null ) return CancelEventCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "CancelEvent";
	param.fieldName = "FKReservation";
	param.value = getData("PKReservation").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheCancelEvent").equals("true"))
	{
		CancelEventCache = new ObjectHashtable();
		for (Enumeration e = (CancelEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			CancelEventCache.put(pkey,cacheBO);								
		}
		return (CancelEventCache.elements());
	}
	else
	{
		return (CancelEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}



/**	  
* <br>
* method to retrieve the old FetchEvent objects for this Reservation
* old FetchEvent objects would be different from the new ones usualy if
* the Reservation has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old FetchEvent objects.
*/
  public Enumeration getOldFetchEvent(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheFetchEvent");
    if (cache)
      getSession().setProperty("NoCacheFetchEvent", "false");
    else
      getSession().setProperty("NoCacheFetchEvent", "true");   
    
    try {
      return getOldFetchEvent();
    } finally {                     
        getSession().setProperty("NoCacheFetchEvent", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old FetchEvent objects for this Reservation
* old FetchEvent objects would be different from the new ones usualy if
* the Reservation has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old FetchEvent objects.
*/
public Enumeration	getOldFetchEvent()
{
	if (!getSession().getProperty("NoCacheFetchEvent").equals("true"))
	{
		if ( FetchEventCache != null ) return FetchEventCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "FetchEvent";
	param.fieldName = "FKReservation";
	param.value = getData("PKReservation").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheFetchEvent").equals("true"))
	{
		FetchEventCache = new ObjectHashtable();
		for (Enumeration e = (FetchEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			FetchEventCache.put(pkey,cacheBO);								
		}
		return (FetchEventCache.elements());
	}
	else
	{
		return (FetchEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}


/**	  
* <br>
* method to retrieve the old ReserveEvent objects for this Reservation
* old ReserveEvent objects would be different from the new ones usualy if
* the Reservation has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old ReserveEvent objects.
*/
  public Enumeration getOldReserveEvent(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheReserveEvent");
    if (cache)
      getSession().setProperty("NoCacheReserveEvent", "false");
    else
      getSession().setProperty("NoCacheReserveEvent", "true");   
    
    try {
      return getOldReserveEvent();
    } finally {                     
        getSession().setProperty("NoCacheReserveEvent", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old ReserveEvent objects for this Reservation
* old ReserveEvent objects would be different from the new ones usualy if
* the Reservation has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old ReserveEvent objects.
*/
public Enumeration	getOldReserveEvent()
{
	if (!getSession().getProperty("NoCacheReserveEvent").equals("true"))
	{
		if ( ReserveEventCache != null ) return ReserveEventCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "ReserveEvent";
	param.fieldName = "FKReservation";
	param.value = getData("PKReservation").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheReserveEvent").equals("true"))
	{
		ReserveEventCache = new ObjectHashtable();
		for (Enumeration e = (ReserveEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			ReserveEventCache.put(pkey,cacheBO);								
		}
		return (ReserveEventCache.elements());
	}
	else
	{
		return (ReserveEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}


/**	  
* <br>
* method to retrieve the old CancelEvent objects for this Reservation
* old CancelEvent objects would be different from the new ones usualy if
* the Reservation has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old CancelEvent objects.
*/
  public Enumeration getOldCancelEvent(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheCancelEvent");
    if (cache)
      getSession().setProperty("NoCacheCancelEvent", "false");
    else
      getSession().setProperty("NoCacheCancelEvent", "true");   
    
    try {
      return getOldCancelEvent();
    } finally {                     
        getSession().setProperty("NoCacheCancelEvent", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old CancelEvent objects for this Reservation
* old CancelEvent objects would be different from the new ones usualy if
* the Reservation has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old CancelEvent objects.
*/
public Enumeration	getOldCancelEvent()
{
	if (!getSession().getProperty("NoCacheCancelEvent").equals("true"))
	{
		if ( CancelEventCache != null ) return CancelEventCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "CancelEvent";
	param.fieldName = "FKReservation";
	param.value = getData("PKReservation").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheCancelEvent").equals("true"))
	{
		CancelEventCache = new ObjectHashtable();
		for (Enumeration e = (CancelEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			CancelEventCache.put(pkey,cacheBO);								
		}
		return (CancelEventCache.elements());
	}
	else
	{
		return (CancelEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}

public void updateCacheForFetchEvent(FetchEventBaseImpl child, boolean remove)
{
	if ( FetchEventCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( FetchEventCache.containsKey(child.getRow().getPkeyParams()) )
				FetchEventCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! FetchEventCache.containsKey(child.getRow().getPkeyParams()) )
				FetchEventCache.put(child.getRow().getPkeyParams(), child);
		}
	}
}
public void updateCacheForReserveEvent(ReserveEventBaseImpl child, boolean remove)
{
	if ( ReserveEventCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( ReserveEventCache.containsKey(child.getRow().getPkeyParams()) )
				ReserveEventCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! ReserveEventCache.containsKey(child.getRow().getPkeyParams()) )
				ReserveEventCache.put(child.getRow().getPkeyParams(), child);
		}
	}
}
public void updateCacheForCancelEvent(CancelEventBaseImpl child, boolean remove)
{
	if ( CancelEventCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( CancelEventCache.containsKey(child.getRow().getPkeyParams()) )
				CancelEventCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! CancelEventCache.containsKey(child.getRow().getPkeyParams()) )
				CancelEventCache.put(child.getRow().getPkeyParams(), child);
		}
	}
}

/**	
* Invalidates the relationship cache. Called by the system on transaction begin.
*/
public void invalidateNonTransactionCaches() 
{
 
  FetchEventCache = null;
  ReserveEventCache = null;
  CancelEventCache = null;
}

	// Do the parent checks.
public void removeMeFromCopyCache()
{
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Copy";
	param.fieldName = "PKCopy";
	param.value = getData("FKCopy").getPreviousString();
	searchReq.add(param);
	if (getSession().getTransactionInfo().isInCache(CopyImpl.getMetaQuery(), searchReq))
	{
		CopyBaseImpl	parent = this.getOldCopy();
		parent.updateCacheForReservation(this, true);
	}
}
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
		parent.updateCacheForReservation(this, true);
	}
}

/**	  
* <br>
* method to get the Copy object for this Reservation
* this method currently does not support additional conditional params.
* @return Object : the  parent object Copy for this Reservation.
*/
public	CopyImpl	getCopy()
{
	CopyImpl parent = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Copy";
	param.fieldName = "PKCopy";
	param.value = getData("FKCopy").getString();
	searchReq.add(param);
	parent = (CopyImpl)(CopyBaseImpl.getObjectByKey(searchReq ,getSession()));
	return parent;
}

/**	  
* <br>
* method to set the Copy object for this Reservation.
* @param Object : the  parent object Copy for this Reservation.
*/
public	void	setCopy(CopyImpl parentObj)
{
	this.setFKCopy(parentObj.getPKCopy());
}

/**	  
* <br>
* method to get the Member object for this Reservation
* this method currently does not support additional conditional params.
* @return Object : the  parent object Member for this Reservation.
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
* method to set the Member object for this Reservation.
* @param Object : the  parent object Member for this Reservation.
*/
public	void	setMember(MemberImpl parentObj)
{
	this.setFKMember(parentObj.getPKMember());
}


/**	  
* <br>
* method to get the old Copy object for this Reservation
* this method currently does not support additional conditional params.
* @return Object : the  old parent object Copy for this Reservation.
*/
public	CopyImpl	getOldCopy()
{
	CopyImpl parent = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Copy";
	param.fieldName = "PKCopy";
	param.value = getData("FKCopy").getPreviousString();
	searchReq.add(param);
	parent = (CopyImpl)(CopyBaseImpl.getObjectByKey(searchReq ,getSession()));
	return parent;
}
/**	  
* <br>
* method to get the old Member object for this Reservation
* this method currently does not support additional conditional params.
* @return Object : the  old parent object Member for this Reservation.
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
	* method to get the ReservationState attribute for the Reservation
	* @return int : the  value of the attribute ReservationState as int.
	*/
	public int	getReservationState() 
	{
	return getData("ReservationState").getint();
	}

	/**	  
	* <br>
	* method to set the ReservationState attribute for the Reservation
	* @param int : value of the attribute ReservationState as int.
	* @return nothing
	*/
	public void	setReservationState(int value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("ReservationState");
	dataVal.setint(value);
	}

	/**	  
	* <br>
	* method to get the old ReservationState attribute for the Reservation
	* @return int : the  value of the old attribute ReservationState as int.
	*/
	public int	getOldReservationState()
	{
	return getData("ReservationState").getPreviousint();
	}

	/**	  
	* <br>
	* method to get the EndDate attribute for the Reservation
	* @return VSDate : the  value of the attribute EndDate as VSDate.
	*/
	public VSDate	getEndDate() 
	{
	return getData("EndDate").getVSDate();
	}

	/**	  
	* <br>
	* method to set the EndDate attribute for the Reservation
	* @param VSDate : value of the attribute EndDate as VSDate.
	* @return nothing
	*/
	public void	setEndDate(VSDate value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("EndDate");
	dataVal.setVSDate(value);
	}

	/**	  
	* <br>
	* method to get the old EndDate attribute for the Reservation
	* @return VSDate : the  value of the old attribute EndDate as VSDate.
	*/
	public VSDate	getOldEndDate()
	{
	return getData("EndDate").getPreviousVSDate();
	}

	/**	  
	* <br>
	* method to get the FKCopy attribute for the Reservation
	* @return long : the  value of the attribute FKCopy as long.
	*/
	public long	getFKCopy() 
	{
	return getData("FKCopy").getlong();
	}

	/**	  
	* <br>
	* method to set the FKCopy attribute for the Reservation
	* @param long : value of the attribute FKCopy as long.
	* @return nothing
	*/
	public void	setFKCopy(long value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("FKCopy");
	dataVal.setlong(value);
	}

	/**	  
	* <br>
	* method to get the old FKCopy attribute for the Reservation
	* @return long : the  value of the old attribute FKCopy as long.
	*/
	public long	getOldFKCopy()
	{
	return getData("FKCopy").getPreviouslong();
	}

	/**	  
	* <br>
	* method to get the StartDate attribute for the Reservation
	* @return VSDate : the  value of the attribute StartDate as VSDate.
	*/
	public VSDate	getStartDate() 
	{
	return getData("StartDate").getVSDate();
	}

	/**	  
	* <br>
	* method to set the StartDate attribute for the Reservation
	* @param VSDate : value of the attribute StartDate as VSDate.
	* @return nothing
	*/
	public void	setStartDate(VSDate value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("StartDate");
	dataVal.setVSDate(value);
	}

	/**	  
	* <br>
	* method to get the old StartDate attribute for the Reservation
	* @return VSDate : the  value of the old attribute StartDate as VSDate.
	*/
	public VSDate	getOldStartDate()
	{
	return getData("StartDate").getPreviousVSDate();
	}

	/**	  
	* <br>
	* method to get the FKMember attribute for the Reservation
	* @return long : the  value of the attribute FKMember as long.
	*/
	public long	getFKMember() 
	{
	return getData("FKMember").getlong();
	}

	/**	  
	* <br>
	* method to set the FKMember attribute for the Reservation
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
	* method to get the old FKMember attribute for the Reservation
	* @return long : the  value of the old attribute FKMember as long.
	*/
	public long	getOldFKMember()
	{
	return getData("FKMember").getPreviouslong();
	}


/**	  
* <br>
* method to get the PKReservation attribute for the Reservation
* @return long : the  value of the attribute PKReservation as long.
*/
public long	getPKReservation() 
{
	return getData("PKReservation").getlong();
}

/**	  
* <br>
* method to set the PKReservation attribute for the Reservation
* @param long : value of the attribute PKReservation as long.
* @return nothing
*/
public void	setPKReservation(long value)
{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("PKReservation");
	dataVal.setlong(value);
}

/**	  
* <br>
* method to get the old PKReservation attribute for the Reservation
* @return long : the  value of the old attribute PKReservation as long.
*/
public long	getOldPKReservation()
{
	return getData("PKReservation").getPreviouslong();
}





	


protected static int getCopyCurrentReservations(SearchRequest searchReq, Session aSession)
{
	Enumeration dbrows = null;
	ReservationBaseImpl row = null;
	int count;
	count = 0;
		String whereClause = getWhereClauseForCopyCurrentReservations();
		searchReq.add(whereClause);
		searchReq.add((Object)"Copy.CurrentReservations");
		count = getObjectCount(searchReq, aSession);
	return (count);
}	
protected static int getMemberCurrentReservations(SearchRequest searchReq, Session aSession)
{
	Enumeration dbrows = null;
	ReservationBaseImpl row = null;
	int count;
	count = 0;
		String whereClause = getWhereClauseForMemberCurrentReservations();
		searchReq.add(whereClause);
		searchReq.add((Object)"Member.CurrentReservations");
		count = getObjectCount(searchReq, aSession);
	return (count);
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
		count = aSession.getTransactionInfo().getObjectsCount(ReservationBaseImpl.getMetaQuery(), xdac, searchReq);
	else
	{
		con = aSession.getConnection( xdac, true );
  	  	count = xdac.getRowCount( ReservationBaseImpl.getMetaQuery(), searchReq, con );
	}

  	return count;
  
}

	/**	  
	* <br>
	* Method returning true if the column in question has a where clause used in parent table.
	* during the computation of the column.
	*/
	public boolean passesWhere(String parentColumn)
	{
			if (parentColumn.equals("Copy.CurrentReservations"))
	{
		try {
		if (this.getReservationState() == 1)
		{
			return( true );
		}
		} catch (Exception ex) {
 			String dependents[] = {			 "ReservationState"
 };
			handleAggregateQualificationException(ex, "CurrentReservations", "Copy", dependents);
		}	
		return( false );
	}
	if (parentColumn.equals("Member.CurrentReservations"))
	{
		try {
		if (this.getReservationState() == 1)
		{
			return( true );
		}
		} catch (Exception ex) {
 			String dependents[] = {			 "ReservationState"
 };
			handleAggregateQualificationException(ex, "CurrentReservations", "Member", dependents);
		}	
		return( false );
	}
	return( false );
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
		VSMetaTable table = ReservationBaseImpl.getMetaQuery().getChildMostTable();
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
		VSMetaTable table = ReservationBaseImpl.getMetaQuery().getChildMostTable();
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
	Properties props = ((VSORBSessionImpl)aSession).getMyDataServiceLoginForObject("Reservation");
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

