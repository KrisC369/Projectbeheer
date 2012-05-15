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


public abstract class CopyBaseImpl extends  DataObject
	
{
	public static Logger logger = Logger.getLogger("LIBs0199831.Copy");
	
	public CopyBaseImpl () {
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
		q= new VSQueryDefinition( "Copy" );	
		q.setPackageName(deployedFromRepository);
		
		VSMetaColumn c = null;
		VSMetaTable t = new VSMetaTable("Copy");
		//{{META_QUERY_COLUMN_CTOR
		c = new VSMetaColumn("PKCopy", DataConst.BIGINT);	
		c.setAutoIncrement(true);
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("FKLibrary", DataConst.BIGINT);	
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("numberOfLoans", DataConst.INTEGER);
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNullable);
		c.setComputed (true);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("Title", DataConst.VARCHAR);
		c.setSize(50);
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("Author", DataConst.VARCHAR);
		c.setSize(50);
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("ISBN", DataConst.VARCHAR);
		c.setSize(50);
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNullable);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("CopyState", DataConst.INTEGER);
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("CurrentLoans", DataConst.INTEGER);
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNullable);
		c.setComputed (true);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("CurrentReservations", DataConst.INTEGER);
		c.setAlterability(false);
		c.setNullability(VSMetaColumn.columnNullable);
		c.setComputed (true);
		c.setCaption("");
		t.addColumn( c );
	
		
		t.addUniqueKeyColumn( "Copy Unique Key", "PKCopy" );
		
		// Register a foreign key from  to Library.
		t.addForeignKeyColumn( "Foreign Key To Library", "FKLibrary" );
	
		
		
	
		t.useQuotedIdentifier(true);
		t.setOptLock( DataConst.OptLockingOnApplicable );
	
		
	
	//END_META_QUERY_COLUMN_CTOR}}
		q.addTable( t );
		q.populateQCArray();
		VSQueryDefinition qTemp = (VSQueryDefinition)getMetaQuery( "Copy",deployedFromRepository ); 
		if ( qTemp == null ) {			
			addMetaQuery(q, deployedFromRepository);
		}					
		else
			q = qTemp;	// Keep the old query as it has cached object
			
	
		
	
	//END_COMPONENT_META_QRY}}

	//{{COMPONENT_RULES
	




















	t.setDerivationType("numberOfLoans", VSMetaColumn.derivationCount);
	t.setDerivationType("CurrentReservations", VSMetaColumn.derivationCount);
	t.setDerivationType("CurrentLoans", VSMetaColumn.derivationCount);
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
	return "Copy";
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
* Constructor for the class CopyBaseImpl.
* @param Session session object on which the Business object is created.
* @param boolean if true sets the default values in the data elements
*        as defined in the meta data.
*/
public	CopyBaseImpl(Session session, boolean makeDefaults)
{
	super(session, CopyBaseImpl.getMetaQuery(), makeDefaults);
	addListeners();
}


/**
*	Read DataObject extended property, set the cache size and expiration time for a perticular Object type
*	This should only be done once per class type, use "initialized" as flag.
*/
public static void initCache() {
	if ( initialized ) return ;
		
	AppObjectImpl appObject = AppObjectImpl.getBusinessObject( deployedFromRepository, "Copy", (Session)ServerEnvironment.getServer().getInternalSession());
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "Copy", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "Copy","", (String)null);
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
		Enumeration e= aSession.getTransactionInfo().getObjects(CopyBaseImpl.getMetaQuery(), xdac, searchReq);
		
		// ******************* cache support
		if ( cacheSize!=0 && e.hasMoreElements()) {
			Vector boList = new Vector();
			while ( e.hasMoreElements()) {
				boList.addElement( e.nextElement());
			}
			
			CacheAgent.getCacheAgent().setObjects( CopyBaseImpl.getMetaQuery(), boList, expires );
			
			return boList.elements();
		
		} else
			return e;
	
	} else {
		Vector boList = new Vector();
				Connection con = aSession.getConnection( xdac, true );
		ResultSet rs = new VSORBResultSetImpl( CopyBaseImpl.getMetaQuery(), searchReq, null, -1 ,DataConst.NONE_LOB, xdac, con );
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
			CacheAgent.getCacheAgent().setObjects( CopyBaseImpl.getMetaQuery(), boList, expires );
			
		return boList.elements();
	}
}

public static void raiseBeforeQueryEvent( SearchRequest searchReq, Session aSession )	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"Copy.BeforeQuery");
	}

	beforeQuery( "Copy", searchReq, null , aSession );
	
	if (  tr != null ) tr.end( tr_id );
}

public static void raiseAfterQueryEvent( ResultSet rs)	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"Copy.AfterQuery");
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "Copy", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "Copy","", (String)null);
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
		anObject = CacheAgent.getCacheAgent().getObject( CopyBaseImpl.getMetaQuery(),key.parameterList, aSession);
		
		logger.fine(" Cache :"+ anObject);
		
		if (anObject!=null)  {
			//set current session to this cached object, ensure that object has a active session
			anObject.setSession( aSession );
			return anObject;
		}
	}

	if (aSession.isTransactionInProgress()) {
		anObject = aSession.getTransactionInfo().getObjectByKey(CopyBaseImpl.getMetaQuery(),key);
	} else {		
		anObject = aSession.getObjectByKey(CopyBaseImpl.getMetaQuery(),key);
	}
	
	//**************************** cache support
	if ( cacheSize != 0 )
		 CacheAgent.getCacheAgent().addObject( CopyBaseImpl.getMetaQuery(),anObject, expires );
		 
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
	setnumberOfLoans((int)0);
	setCurrentReservations((int)0);
	setCurrentLoans((int)0);
	
	XDAConnector xdac = getXDAConnector();
	// For the counter field set and get the counter value
	Object counter = null;

	if ( xdac instanceof XDASQLConnector ) {
		if ( ( counter = ((XDASQLConnector)xdac).getCounter(CopyBaseImpl.getMetaQuery().getBaseTable("Copy"), 
		   session.getTransactionInfo().getConnection( xdac), rowData,"PKCopy" ) )!= null )	
		   this.setPKCopy( ((Long)counter).longValue());
	}
	else {
		if ( ( counter = xdac.getCounter(CopyBaseImpl.getMetaQuery().getBaseTable("Copy"), 
		   session.getTransactionInfo().getConnection( xdac), rowData ) )!= null )	
		   this.setPKCopy( ((Long)counter).longValue());
	}

	// Do the parent checks.
	this.parentCheckFor_Library();


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
	// Child Cascade for Role pr)-Reservation(Reservation): Copy->>Reservation
	this.childCascadeFor_Reservation();
	// Child Cascade for Role pr)-SellEvent(SellEvent): Copy->>SellEvent
	this.childCascadeFor_SellEvent();
	// Child Cascade for Role pr)-ReserveEvent(ReserveEvent): Copy->>ReserveEvent
	this.childCascadeFor_ReserveEvent();
	// Child Cascade for Role pr)-AcquireEvent(AcquireEvent): Copy->>AcquireEvent
	this.childCascadeFor_AcquireEvent();
	// Child Cascade for Role pr)-ClassifyEvent(ClassifyEvent): Copy->>ClassifyEvent
	this.childCascadeFor_ClassifyEvent();
	// Child Cascade for Role pr)-Loan(Loan): Copy->>Loan
	this.childCascadeFor_Loan();
	// Do the Parent Adjustment.
	// Parent Adjustment for Role cribrary)-Copy(Copy) : Copy->> Library
	this.parentAdjustmentFor_Library();

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
	if ( getGlobalNestLevel() == 1 && isAltered("CopyState") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'CopyState' in Object Copy is not Alterable. Error Column: <Copy>.<CopyState>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("numberOfLoans") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'numberOfLoans' in Object Copy is not Alterable. Error Column: <Copy>.<numberOfLoans>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("FKLibrary") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'FKLibrary' in Object Copy is not Alterable. Error Column: <Copy>.<FKLibrary>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("PKCopy") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'PKCopy' in Object Copy is not Alterable. Error Column: <Copy>.<PKCopy>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("CurrentReservations") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'CurrentReservations' in Object Copy is not Alterable. Error Column: <Copy>.<CurrentReservations>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("CurrentLoans") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'CurrentLoans' in Object Copy is not Alterable. Error Column: <Copy>.<CurrentLoans>");
	}

	// Do the parent checks.
	this.parentCheckFor_Library();

	// Set the formulae values at this point
	this.setFormulaValues();


	// Do Column Validation Checks
	this.columnValidationCheck();

	// verify that not nullable columns are not null.




	// Compute stored values if not already done
	if ( isChanged("numberOfLoans") )
		getnumberOfLoans();
	if ( isChanged("CurrentReservations") )
		getCurrentReservations();
	if ( isChanged("CurrentLoans") )
		getCurrentLoans();

	if ( this.isCurrentEvent("RECOMPUTE_DERIVATIONS") && (getGlobalNestLevel() == 1) ) this.RecomputeDerivations();

	// At this point all the computations for the object attributes have been made
	// therefore save the object.
	this.updateRowImmediate();

	// Factored out duplicate code passage - Val/Paul 03-17-03
	this.tableConditionActions();

	// Do Child Cascades.
	// Child Cascade for Role pr)-Reservation(Reservation): Copy->>Reservation
	this.childCascadeFor_Reservation();
	// Child Cascade for Role pr)-SellEvent(SellEvent): Copy->>SellEvent
	this.childCascadeFor_SellEvent();
	// Child Cascade for Role pr)-ReserveEvent(ReserveEvent): Copy->>ReserveEvent
	this.childCascadeFor_ReserveEvent();
	// Child Cascade for Role pr)-AcquireEvent(AcquireEvent): Copy->>AcquireEvent
	this.childCascadeFor_AcquireEvent();
	// Child Cascade for Role pr)-ClassifyEvent(ClassifyEvent): Copy->>ClassifyEvent
	this.childCascadeFor_ClassifyEvent();
	// Child Cascade for Role pr)-Loan(Loan): Copy->>Loan
	this.childCascadeFor_Loan();
	// Do the Parent Adjustment.
	// Parent Adjustment for Role cribrary)-Copy(Copy) : Copy->> Library
	this.parentAdjustmentFor_Library();
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
	removeMeFromLibraryCache();

	// Do Column Validation Checks
	this.columnValidationCheck();

	// Do TableConstraintChecks
	if ( !isCascadeDeletedInDB() )
	this.updateRowImmediate();

	// Factored out duplicate code passage - Val/Paul 03-17-03
	this.tableConditionActions();

	// Do Child Cascades.
	// Child Cascade for Role pr)-Reservation(Reservation): Copy->>Reservation
	this.childCascadeFor_Reservation();
	// Child Cascade for Role pr)-SellEvent(SellEvent): Copy->>SellEvent
	this.childCascadeFor_SellEvent();
	// Child Cascade for Role pr)-ReserveEvent(ReserveEvent): Copy->>ReserveEvent
	this.childCascadeFor_ReserveEvent();
	// Child Cascade for Role pr)-AcquireEvent(AcquireEvent): Copy->>AcquireEvent
	this.childCascadeFor_AcquireEvent();
	// Child Cascade for Role pr)-ClassifyEvent(ClassifyEvent): Copy->>ClassifyEvent
	this.childCascadeFor_ClassifyEvent();
	// Child Cascade for Role pr)-Loan(Loan): Copy->>Loan
	this.childCascadeFor_Loan();
 

	// Do the Parent Adjustment.
	// Parent Adjustment for Role cribrary)-Copy(Copy) : Copy->> Library
	this.parentAdjustmentFor_Library();

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

	protected void parentCheckFor_Library()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentCheckFor_Library").set(VST_OBJECT_NAME,"Copy");
	}
	
	try {
	
	LibraryImpl	parent = null;
	boolean		ParentKeyChanged = false;
	boolean		OrphanChildParenting;
	if (( isInserted() || isUpdated() ) && ( 
	(isChanged("FKLibrary"))
 ) &&
	((!isNull("FKLibrary")) ))
	{
		// This would cause the row to be dropped from the old parent if it exist.
		removeMeFromLibraryCache();
		parent = this.getLibrary();
			
		if ( parent == null )
		{
			raiseException("Library not found for Copy. Error Column: <Copy>.<FKLibrary>");
		}
		else
		{
			// Add myself to the new parent cache.
			parent.updateCacheForCopy(this, false);
		}
		
	}
		
	
	}
	finally { if (  tr != null ) tr.end( tr_id ); }
	}

	protected void childCascadeFor_Reservation()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_Reservation").set(VST_OBJECT_NAME,"Copy");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKCopy")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldReservation(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are Reservation found for Copy");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldReservation();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are Reservation found for Copy");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}
	protected void childCascadeFor_SellEvent()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_SellEvent").set(VST_OBJECT_NAME,"Copy");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKCopy")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldSellEvent(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are SellEvent found for Copy");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldSellEvent();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are SellEvent found for Copy");
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
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_ReserveEvent").set(VST_OBJECT_NAME,"Copy");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKCopy")) ))
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
				raiseException("Delete Rejected because there are ReserveEvent found for Copy");
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
					raiseException("Update Rejected because there are ReserveEvent found for Copy");
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
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_AcquireEvent").set(VST_OBJECT_NAME,"Copy");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKCopy")) ))
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
				raiseException("Delete Rejected because there are AcquireEvent found for Copy");
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
					raiseException("Update Rejected because there are AcquireEvent found for Copy");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}
	protected void childCascadeFor_ClassifyEvent()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_ClassifyEvent").set(VST_OBJECT_NAME,"Copy");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKCopy")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldClassifyEvent(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are ClassifyEvent found for Copy");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldClassifyEvent();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are ClassifyEvent found for Copy");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}
	protected void childCascadeFor_Loan()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_Loan").set(VST_OBJECT_NAME,"Copy");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKCopy")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldLoan(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are Loan found for Copy");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldLoan();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are Loan found for Copy");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}

	protected void parentAdjustmentFor_Library()
	{
	
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentAdjustmentFor_Library").set(VST_OBJECT_NAME,"Copy");
	}
	
	try {		// make sure to stop trace
	
	DataObjectWrapper	oldParent = new DataObjectWrapper();
	DataObjectWrapper	newParent = new DataObjectWrapper();

	boolean	childCascadeUpdate = false;
	boolean	childCascadeDelete = false;
	boolean	orphanChildParenting = false;
	boolean 	childReparented	= false;

	if ( isUpdated() && (isChanged("FKLibrary") ) && ( (!(isOldNull("FKLibrary")))) )
	{
		if ( !oldParent.isInitialized() )
			oldParent.setDataObject(this.getOldLibrary());
		if ( ! oldParent.isObjNull() ) {
			childCascadeUpdate = false;
			childReparented = true;
		}
		else {
			childCascadeUpdate = true;
			childReparented = false;
		}
	}

	if ( isUpdated() && (isChanged("FKLibrary") ) && ( ! ( (!(isOldNull("FKLibrary")))) ) )
	{
		if ( !newParent.isInitialized() )
			newParent.setDataObject(this.getLibrary());
		if ( ! newParent.isObjNull() ) orphanChildParenting = true;
		else orphanChildParenting = false;
	}

	if ( isDeleted() && ((!(isOldNull("FKLibrary"))) ))
	{
		if ( !oldParent.isInitialized() )
			oldParent.setDataObject(this.getOldLibrary());
		if ( ! oldParent.isObjNull() ) childCascadeDelete = false;
		else childCascadeDelete = true;
	}
 
	parentAdjustmentFor_LibraryamountOfCopies(oldParent, newParent, childCascadeUpdate, childCascadeDelete, orphanChildParenting, childReparented);
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

	

protected void parentAdjustmentFor_LibraryamountOfCopies( DataObjectWrapper oldParent, DataObjectWrapper newParent, boolean childCascadeUpdate, boolean childCascadeDelete, boolean orphanChildParenting, boolean childReparented)
{
	
	try { // trap null pointer exceptions and analyze
	if ( isInserted() )
	{
		// For the case of insertion it does not matter If the columns involved
		// for both the where clause and the from column in child are virtual.
		// simply set the deltas to new values and mark them as usable
		if ( !newParent.isInitialized() )
			newParent.setDataObject(this.getLibrary());
		if ( ! newParent.isObjNull() )
			newParent.getDataObject().setAdjust("amountOfCopies", 1, true);
		else
		{
			// Raise an error here
		}
		return;
	}

	if ( isDeleted() && ( ! childCascadeDelete ) )
	{
		if ( !oldParent.isInitialized() )
			oldParent.setDataObject(this.getOldLibrary());
		if ( ! oldParent.isObjNull() )
			oldParent.getDataObject().setAdjust("amountOfCopies",-1,true);
		else
		{
			// Raise an error here
		}
		return;
	}

	if ( childCascadeUpdate ) 
	{
		int	addValue = 0;
		int	subValue = 0;
		return;
	}
	else if ( orphanChildParenting )
	{
		if ( !newParent.isInitialized() )
			newParent.setDataObject(this.getLibrary());
		if ( ! newParent.isObjNull() )
			newParent.getDataObject().setAdjust("amountOfCopies",1,true);
		else
		{
			// Raise an error here
		}
		return;
	}
	else if ( childReparented )
	{
 
		if ( !newParent.isInitialized() )
			newParent.setDataObject(this.getLibrary());
		if ( ! newParent.isObjNull() )
			newParent.getDataObject().setAdjust("amountOfCopies",1,true);
		else
		{
			// Raise an error here
		}

		if ( !oldParent.isInitialized() )
			oldParent.setDataObject(this.getOldLibrary());
		if ( ! oldParent.isObjNull() )
			oldParent.getDataObject().setAdjust("amountOfCopies",-1,true);
		else
		{
			// Raise an error here
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
			return;
		}
	}
	} catch (Exception ex) {
	 	String dependents[] = { };
		handleAggregateQualificationException(ex, "amountOfCopies", "Library", dependents);
	}	

}


	

	

private ObjectHashtable ReservationCache = null;

/**	  
* <br>
* method to retrieve the Reservation objects for this Copy
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of Reservation objects.
*/
public Enumeration getReservation()
{
	if (!getSession().getProperty("NoCacheReservation").equals("true"))
	{
		if ( ReservationCache != null ) return ReservationCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "Reservation";
	param.fieldName = "FKCopy";
	param.value = getData("PKCopy").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheReservation").equals("true"))
	{
		ReservationCache = new ObjectHashtable();
		for (Enumeration e = (ReservationBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			ReservationCache.put(pkey,cacheBO);								
		}
		return (ReservationCache.elements());
	}
	else
	{
		return (ReservationBaseImpl.getObjects(searchReq ,getSession()));
	}
}
private ObjectHashtable SellEventCache = null;

/**	  
* <br>
* method to retrieve the SellEvent objects for this Copy
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of SellEvent objects.
*/
public Enumeration getSellEvent()
{
	if (!getSession().getProperty("NoCacheSellEvent").equals("true"))
	{
		if ( SellEventCache != null ) return SellEventCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "SellEvent";
	param.fieldName = "FKCopy";
	param.value = getData("PKCopy").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheSellEvent").equals("true"))
	{
		SellEventCache = new ObjectHashtable();
		for (Enumeration e = (SellEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			SellEventCache.put(pkey,cacheBO);								
		}
		return (SellEventCache.elements());
	}
	else
	{
		return (SellEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}
private ObjectHashtable ReserveEventCache = null;

/**	  
* <br>
* method to retrieve the ReserveEvent objects for this Copy
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
	param.fieldName = "FKCopy";
	param.value = getData("PKCopy").getString();
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
private ObjectHashtable AcquireEventCache = null;

/**	  
* <br>
* method to retrieve the AcquireEvent objects for this Copy
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
	param.fieldName = "FKCopy";
	param.value = getData("PKCopy").getString();
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
private ObjectHashtable ClassifyEventCache = null;

/**	  
* <br>
* method to retrieve the ClassifyEvent objects for this Copy
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of ClassifyEvent objects.
*/
public Enumeration getClassifyEvent()
{
	if (!getSession().getProperty("NoCacheClassifyEvent").equals("true"))
	{
		if ( ClassifyEventCache != null ) return ClassifyEventCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "ClassifyEvent";
	param.fieldName = "FKCopy";
	param.value = getData("PKCopy").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheClassifyEvent").equals("true"))
	{
		ClassifyEventCache = new ObjectHashtable();
		for (Enumeration e = (ClassifyEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			ClassifyEventCache.put(pkey,cacheBO);								
		}
		return (ClassifyEventCache.elements());
	}
	else
	{
		return (ClassifyEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}
private ObjectHashtable LoanCache = null;

/**	  
* <br>
* method to retrieve the Loan objects for this Copy
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of Loan objects.
*/
public Enumeration getLoan()
{
	if (!getSession().getProperty("NoCacheLoan").equals("true"))
	{
		if ( LoanCache != null ) return LoanCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "Loan";
	param.fieldName = "FKCopy";
	param.value = getData("PKCopy").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheLoan").equals("true"))
	{
		LoanCache = new ObjectHashtable();
		for (Enumeration e = (LoanBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			LoanCache.put(pkey,cacheBO);								
		}
		return (LoanCache.elements());
	}
	else
	{
		return (LoanBaseImpl.getObjects(searchReq ,getSession()));
	}
}



/**	  
* <br>
* method to retrieve the old Reservation objects for this Copy
* old Reservation objects would be different from the new ones usualy if
* the Copy has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old Reservation objects.
*/
  public Enumeration getOldReservation(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheReservation");
    if (cache)
      getSession().setProperty("NoCacheReservation", "false");
    else
      getSession().setProperty("NoCacheReservation", "true");   
    
    try {
      return getOldReservation();
    } finally {                     
        getSession().setProperty("NoCacheReservation", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old Reservation objects for this Copy
* old Reservation objects would be different from the new ones usualy if
* the Copy has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old Reservation objects.
*/
public Enumeration	getOldReservation()
{
	if (!getSession().getProperty("NoCacheReservation").equals("true"))
	{
		if ( ReservationCache != null ) return ReservationCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "Reservation";
	param.fieldName = "FKCopy";
	param.value = getData("PKCopy").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheReservation").equals("true"))
	{
		ReservationCache = new ObjectHashtable();
		for (Enumeration e = (ReservationBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			ReservationCache.put(pkey,cacheBO);								
		}
		return (ReservationCache.elements());
	}
	else
	{
		return (ReservationBaseImpl.getObjects(searchReq ,getSession()));
	}
}


/**	  
* <br>
* method to retrieve the old SellEvent objects for this Copy
* old SellEvent objects would be different from the new ones usualy if
* the Copy has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old SellEvent objects.
*/
  public Enumeration getOldSellEvent(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheSellEvent");
    if (cache)
      getSession().setProperty("NoCacheSellEvent", "false");
    else
      getSession().setProperty("NoCacheSellEvent", "true");   
    
    try {
      return getOldSellEvent();
    } finally {                     
        getSession().setProperty("NoCacheSellEvent", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old SellEvent objects for this Copy
* old SellEvent objects would be different from the new ones usualy if
* the Copy has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old SellEvent objects.
*/
public Enumeration	getOldSellEvent()
{
	if (!getSession().getProperty("NoCacheSellEvent").equals("true"))
	{
		if ( SellEventCache != null ) return SellEventCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "SellEvent";
	param.fieldName = "FKCopy";
	param.value = getData("PKCopy").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheSellEvent").equals("true"))
	{
		SellEventCache = new ObjectHashtable();
		for (Enumeration e = (SellEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			SellEventCache.put(pkey,cacheBO);								
		}
		return (SellEventCache.elements());
	}
	else
	{
		return (SellEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}


/**	  
* <br>
* method to retrieve the old ReserveEvent objects for this Copy
* old ReserveEvent objects would be different from the new ones usualy if
* the Copy has a primary key change.
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
* method to retrieve the old ReserveEvent objects for this Copy
* old ReserveEvent objects would be different from the new ones usualy if
* the Copy has a primary key change.
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
	param.fieldName = "FKCopy";
	param.value = getData("PKCopy").getPreviousString();
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
* method to retrieve the old AcquireEvent objects for this Copy
* old AcquireEvent objects would be different from the new ones usualy if
* the Copy has a primary key change.
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
* method to retrieve the old AcquireEvent objects for this Copy
* old AcquireEvent objects would be different from the new ones usualy if
* the Copy has a primary key change.
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
	param.fieldName = "FKCopy";
	param.value = getData("PKCopy").getPreviousString();
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
* method to retrieve the old ClassifyEvent objects for this Copy
* old ClassifyEvent objects would be different from the new ones usualy if
* the Copy has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old ClassifyEvent objects.
*/
  public Enumeration getOldClassifyEvent(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheClassifyEvent");
    if (cache)
      getSession().setProperty("NoCacheClassifyEvent", "false");
    else
      getSession().setProperty("NoCacheClassifyEvent", "true");   
    
    try {
      return getOldClassifyEvent();
    } finally {                     
        getSession().setProperty("NoCacheClassifyEvent", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old ClassifyEvent objects for this Copy
* old ClassifyEvent objects would be different from the new ones usualy if
* the Copy has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old ClassifyEvent objects.
*/
public Enumeration	getOldClassifyEvent()
{
	if (!getSession().getProperty("NoCacheClassifyEvent").equals("true"))
	{
		if ( ClassifyEventCache != null ) return ClassifyEventCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "ClassifyEvent";
	param.fieldName = "FKCopy";
	param.value = getData("PKCopy").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheClassifyEvent").equals("true"))
	{
		ClassifyEventCache = new ObjectHashtable();
		for (Enumeration e = (ClassifyEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			ClassifyEventCache.put(pkey,cacheBO);								
		}
		return (ClassifyEventCache.elements());
	}
	else
	{
		return (ClassifyEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}


/**	  
* <br>
* method to retrieve the old Loan objects for this Copy
* old Loan objects would be different from the new ones usualy if
* the Copy has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old Loan objects.
*/
  public Enumeration getOldLoan(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheLoan");
    if (cache)
      getSession().setProperty("NoCacheLoan", "false");
    else
      getSession().setProperty("NoCacheLoan", "true");   
    
    try {
      return getOldLoan();
    } finally {                     
        getSession().setProperty("NoCacheLoan", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old Loan objects for this Copy
* old Loan objects would be different from the new ones usualy if
* the Copy has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old Loan objects.
*/
public Enumeration	getOldLoan()
{
	if (!getSession().getProperty("NoCacheLoan").equals("true"))
	{
		if ( LoanCache != null ) return LoanCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "Loan";
	param.fieldName = "FKCopy";
	param.value = getData("PKCopy").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheLoan").equals("true"))
	{
		LoanCache = new ObjectHashtable();
		for (Enumeration e = (LoanBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			LoanCache.put(pkey,cacheBO);								
		}
		return (LoanCache.elements());
	}
	else
	{
		return (LoanBaseImpl.getObjects(searchReq ,getSession()));
	}
}

public void updateCacheForReservation(ReservationBaseImpl child, boolean remove)
{
	if ( ReservationCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( ReservationCache.containsKey(child.getRow().getPkeyParams()) )
				ReservationCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! ReservationCache.containsKey(child.getRow().getPkeyParams()) )
				ReservationCache.put(child.getRow().getPkeyParams(), child);
		}
	}
}
public void updateCacheForSellEvent(SellEventBaseImpl child, boolean remove)
{
	if ( SellEventCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( SellEventCache.containsKey(child.getRow().getPkeyParams()) )
				SellEventCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! SellEventCache.containsKey(child.getRow().getPkeyParams()) )
				SellEventCache.put(child.getRow().getPkeyParams(), child);
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
public void updateCacheForClassifyEvent(ClassifyEventBaseImpl child, boolean remove)
{
	if ( ClassifyEventCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( ClassifyEventCache.containsKey(child.getRow().getPkeyParams()) )
				ClassifyEventCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! ClassifyEventCache.containsKey(child.getRow().getPkeyParams()) )
				ClassifyEventCache.put(child.getRow().getPkeyParams(), child);
		}
	}
}
public void updateCacheForLoan(LoanBaseImpl child, boolean remove)
{
	if ( LoanCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( LoanCache.containsKey(child.getRow().getPkeyParams()) )
				LoanCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! LoanCache.containsKey(child.getRow().getPkeyParams()) )
				LoanCache.put(child.getRow().getPkeyParams(), child);
		}
	}
}

/**	
* Invalidates the relationship cache. Called by the system on transaction begin.
*/
public void invalidateNonTransactionCaches() 
{
 
  ReservationCache = null;
  SellEventCache = null;
  ReserveEventCache = null;
  AcquireEventCache = null;
  ClassifyEventCache = null;
  LoanCache = null;
}

	// Do the parent checks.
public void removeMeFromLibraryCache()
{
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Library";
	param.fieldName = "PKLibrary";
	param.value = getData("FKLibrary").getPreviousString();
	searchReq.add(param);
	if (getSession().getTransactionInfo().isInCache(LibraryImpl.getMetaQuery(), searchReq))
	{
		LibraryBaseImpl	parent = this.getOldLibrary();
		parent.updateCacheForCopy(this, true);
	}
}

/**	  
* <br>
* method to get the Library object for this Copy
* this method currently does not support additional conditional params.
* @return Object : the  parent object Library for this Copy.
*/
public	LibraryImpl	getLibrary()
{
	LibraryImpl parent = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Library";
	param.fieldName = "PKLibrary";
	param.value = getData("FKLibrary").getString();
	searchReq.add(param);
	parent = (LibraryImpl)(LibraryBaseImpl.getObjectByKey(searchReq ,getSession()));
	return parent;
}

/**	  
* <br>
* method to set the Library object for this Copy.
* @param Object : the  parent object Library for this Copy.
*/
public	void	setLibrary(LibraryImpl parentObj)
{
	this.setFKLibrary(parentObj.getPKLibrary());
}


/**	  
* <br>
* method to get the old Library object for this Copy
* this method currently does not support additional conditional params.
* @return Object : the  old parent object Library for this Copy.
*/
public	LibraryImpl	getOldLibrary()
{
	LibraryImpl parent = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Library";
	param.fieldName = "PKLibrary";
	param.value = getData("FKLibrary").getPreviousString();
	searchReq.add(param);
	parent = (LibraryImpl)(LibraryBaseImpl.getObjectByKey(searchReq ,getSession()));
	return parent;
}


	/**	  
	* <br>
	* method to get the Title attribute for the Copy
	* @return String : the  value of the attribute Title as String.
	*/
	public String	getTitle() 
	{
	return getData("Title").getString();
	}

	/**	  
	* <br>
	* method to set the Title attribute for the Copy
	* @param String : value of the attribute Title as String.
	* @return nothing
	*/
	public void	setTitle(String value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("Title");
	dataVal.setString(value);
	}

	/**	  
	* <br>
	* method to get the old Title attribute for the Copy
	* @return String : the  value of the old attribute Title as String.
	*/
	public String	getOldTitle()
	{
	return getData("Title").getPreviousString();
	}

	/**	  
	* <br>
	* method to get the ISBN attribute for the Copy
	* @return String : the  value of the attribute ISBN as String.
	*/
	public String	getISBN() 
	{
	return getData("ISBN").getString();
	}

	/**	  
	* <br>
	* method to set the ISBN attribute for the Copy
	* @param String : value of the attribute ISBN as String.
	* @return nothing
	*/
	public void	setISBN(String value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("ISBN");
	dataVal.setString(value);
	}

	/**	  
	* <br>
	* method to get the old ISBN attribute for the Copy
	* @return String : the  value of the old attribute ISBN as String.
	*/
	public String	getOldISBN()
	{
	return getData("ISBN").getPreviousString();
	}

	/**	  
	* <br>
	* method to get the CopyState attribute for the Copy
	* @return int : the  value of the attribute CopyState as int.
	*/
	public int	getCopyState() 
	{
	return getData("CopyState").getint();
	}

	/**	  
	* <br>
	* method to set the CopyState attribute for the Copy
	* @param int : value of the attribute CopyState as int.
	* @return nothing
	*/
	public void	setCopyState(int value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("CopyState");
	dataVal.setint(value);
	}

	/**	  
	* <br>
	* method to get the old CopyState attribute for the Copy
	* @return int : the  value of the old attribute CopyState as int.
	*/
	public int	getOldCopyState()
	{
	return getData("CopyState").getPreviousint();
	}

	/**	  
	* <br>
	* method to get the Author attribute for the Copy
	* @return String : the  value of the attribute Author as String.
	*/
	public String	getAuthor() 
	{
	return getData("Author").getString();
	}

	/**	  
	* <br>
	* method to set the Author attribute for the Copy
	* @param String : value of the attribute Author as String.
	* @return nothing
	*/
	public void	setAuthor(String value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("Author");
	dataVal.setString(value);
	}

	/**	  
	* <br>
	* method to get the old Author attribute for the Copy
	* @return String : the  value of the old attribute Author as String.
	*/
	public String	getOldAuthor()
	{
	return getData("Author").getPreviousString();
	}

	/**	  
	* <br>
	* method to get the FKLibrary attribute for the Copy
	* @return long : the  value of the attribute FKLibrary as long.
	*/
	public long	getFKLibrary() 
	{
	return getData("FKLibrary").getlong();
	}

	/**	  
	* <br>
	* method to set the FKLibrary attribute for the Copy
	* @param long : value of the attribute FKLibrary as long.
	* @return nothing
	*/
	public void	setFKLibrary(long value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("FKLibrary");
	dataVal.setlong(value);
	}

	/**	  
	* <br>
	* method to get the old FKLibrary attribute for the Copy
	* @return long : the  value of the old attribute FKLibrary as long.
	*/
	public long	getOldFKLibrary()
	{
	return getData("FKLibrary").getPreviouslong();
	}


/**	  
* <br>
* method to get the PKCopy attribute for the Copy
* @return long : the  value of the attribute PKCopy as long.
*/
public long	getPKCopy() 
{
	return getData("PKCopy").getlong();
}

/**	  
* <br>
* method to set the PKCopy attribute for the Copy
* @param long : value of the attribute PKCopy as long.
* @return nothing
*/
public void	setPKCopy(long value)
{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("PKCopy");
	dataVal.setlong(value);
}

/**	  
* <br>
* method to get the old PKCopy attribute for the Copy
* @return long : the  value of the old attribute PKCopy as long.
*/
public long	getOldPKCopy()
{
	return getData("PKCopy").getPreviouslong();
}


	/**	  
	* <br>
	* method to get the count attribute numberOfLoans for the Copy
	* @return int : the  value of the attribute numberOfLoans as int.
	*/
	public int	getnumberOfLoans() 
	{
	Data dataVal = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Loan";
	param.fieldName = "FKCopy";
	if (getSession().isTransactionInProgress())
	{
		if ( isUpdated() && (getGlobalNestLevel() == 1) && (isChanged("PKCopy")))
			param.value = getData("PKCopy").getOldString();
		else
			param.value = getData("PKCopy").getString();
	}
	else
		param.value = getData("PKCopy").getString();
	searchReq.add(param);

		dataVal = getData("numberOfLoans");											
	if (dataVal.isComputationPending() ||  (this.isCurrentEvent("RECOMPUTE_DERIVATIONS") && (getGlobalNestLevel() == 1)) )
	{
		if (dataVal.isDeltaValid() && !(this.isCurrentEvent("RECOMPUTE_DERIVATIONS") && (getGlobalNestLevel() == 1)) )
		{
			dataVal.setint((int)(dataVal.getint() + dataVal.getDeltaint()));
			dataVal.setDeltaValid(false);
		}
		else
		{
			dataVal.setint((LoanBaseImpl.getCopynumberOfLoans(searchReq, getSession())));
		}

		dataVal.setInitialized(true);
		dataVal.setPushPending(false);
	}
	return dataVal.getint();
	}

/**	  
* <br>
* method to get the value before data change for the count attribute numberOfLoans
* in the data object Copy
* @return int : the  value of the attribute numberOfLoans as int before change.
*/
public int	getOldnumberOfLoans()
{
	return getData("numberOfLoans").getPreviousint();
}

/**	  
* <br>
* method to set the value of the count attribute numberOfLoans in the data object Copy
* @param int : value of the attribute numberOfLoans as int.
* @return nothing
*/
public void setnumberOfLoans(int value)
{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("numberOfLoans");
	dataVal.setint(value);
}


	/**	  
	* <br>
	* method to get the count attribute CurrentReservations for the Copy
	* @return int : the  value of the attribute CurrentReservations as int.
	*/
	public int	getCurrentReservations() 
	{
	Data dataVal = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Reservation";
	param.fieldName = "FKCopy";
	if (getSession().isTransactionInProgress())
	{
		if ( isUpdated() && (getGlobalNestLevel() == 1) && (isChanged("PKCopy")))
			param.value = getData("PKCopy").getOldString();
		else
			param.value = getData("PKCopy").getString();
	}
	else
		param.value = getData("PKCopy").getString();
	searchReq.add(param);

		dataVal = getData("CurrentReservations");											
	if (dataVal.isComputationPending() ||  (this.isCurrentEvent("RECOMPUTE_DERIVATIONS") && (getGlobalNestLevel() == 1)) )
	{
		if (dataVal.isDeltaValid() && !(this.isCurrentEvent("RECOMPUTE_DERIVATIONS") && (getGlobalNestLevel() == 1)) )
		{
			dataVal.setint((int)(dataVal.getint() + dataVal.getDeltaint()));
			dataVal.setDeltaValid(false);
		}
		else
		{
			dataVal.setint((ReservationBaseImpl.getCopyCurrentReservations(searchReq, getSession())));
		}

		dataVal.setInitialized(true);
		dataVal.setPushPending(false);
	}
	return dataVal.getint();
	}

/**	  
* <br>
* method to get the value before data change for the count attribute CurrentReservations
* in the data object Copy
* @return int : the  value of the attribute CurrentReservations as int before change.
*/
public int	getOldCurrentReservations()
{
	return getData("CurrentReservations").getPreviousint();
}

/**	  
* <br>
* method to set the value of the count attribute CurrentReservations in the data object Copy
* @param int : value of the attribute CurrentReservations as int.
* @return nothing
*/
public void setCurrentReservations(int value)
{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("CurrentReservations");
	dataVal.setint(value);
}


	/**	  
	* <br>
	* method to get the count attribute CurrentLoans for the Copy
	* @return int : the  value of the attribute CurrentLoans as int.
	*/
	public int	getCurrentLoans() 
	{
	Data dataVal = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Loan";
	param.fieldName = "FKCopy";
	if (getSession().isTransactionInProgress())
	{
		if ( isUpdated() && (getGlobalNestLevel() == 1) && (isChanged("PKCopy")))
			param.value = getData("PKCopy").getOldString();
		else
			param.value = getData("PKCopy").getString();
	}
	else
		param.value = getData("PKCopy").getString();
	searchReq.add(param);

		dataVal = getData("CurrentLoans");											
	if (dataVal.isComputationPending() ||  (this.isCurrentEvent("RECOMPUTE_DERIVATIONS") && (getGlobalNestLevel() == 1)) )
	{
		if (dataVal.isDeltaValid() && !(this.isCurrentEvent("RECOMPUTE_DERIVATIONS") && (getGlobalNestLevel() == 1)) )
		{
			dataVal.setint((int)(dataVal.getint() + dataVal.getDeltaint()));
			dataVal.setDeltaValid(false);
		}
		else
		{
			dataVal.setint((LoanBaseImpl.getCopyCurrentLoans(searchReq, getSession())));
		}

		dataVal.setInitialized(true);
		dataVal.setPushPending(false);
	}
	return dataVal.getint();
	}

/**	  
* <br>
* method to get the value before data change for the count attribute CurrentLoans
* in the data object Copy
* @return int : the  value of the attribute CurrentLoans as int before change.
*/
public int	getOldCurrentLoans()
{
	return getData("CurrentLoans").getPreviousint();
}

/**	  
* <br>
* method to set the value of the count attribute CurrentLoans in the data object Copy
* @param int : value of the attribute CurrentLoans as int.
* @return nothing
*/
public void setCurrentLoans(int value)
{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("CurrentLoans");
	dataVal.setint(value);
}





	


protected static int getLibraryamountOfCopies(SearchRequest searchReq, Session aSession)
{
	Enumeration dbrows = null;
	CopyBaseImpl row = null;
	int count;
	count = 0;
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
		count = aSession.getTransactionInfo().getObjectsCount(CopyBaseImpl.getMetaQuery(), xdac, searchReq);
	else
	{
		con = aSession.getConnection( xdac, true );
  	  	count = xdac.getRowCount( CopyBaseImpl.getMetaQuery(), searchReq, con );
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
		if (parentColumn.equals("Library.amountOfCopies"))
	{
		return( true );
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
		VSMetaTable table = CopyBaseImpl.getMetaQuery().getChildMostTable();
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
		VSMetaTable table = CopyBaseImpl.getMetaQuery().getChildMostTable();
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
	Properties props = ((VSORBSessionImpl)aSession).getMyDataServiceLoginForObject("Copy");
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
	this.setnumberOfLoans(getnumberOfLoans());
	this.setCurrentReservations(getCurrentReservations());
	this.setCurrentLoans(getCurrentLoans());

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

