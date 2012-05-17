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


public abstract class MemberBaseImpl extends  DataObject
	
{
	public static Logger logger = Logger.getLogger("LIBs0199831.Member");
	
	public MemberBaseImpl () {
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
		q= new VSQueryDefinition( "Member" );	
		q.setPackageName(deployedFromRepository);
		
		VSMetaColumn c = null;
		VSMetaTable t = new VSMetaTable("Member");
		//{{META_QUERY_COLUMN_CTOR
		c = new VSMetaColumn("PKMember", DataConst.BIGINT);	
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
	
		c = new VSMetaColumn("FirstName", DataConst.VARCHAR);
		c.setSize(50);
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("LastName", DataConst.VARCHAR);
		c.setSize(50);
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("Address", DataConst.VARCHAR);
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
	
		c = new VSMetaColumn("Country", DataConst.VARCHAR);
		c.setSize(50);
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNoNulls);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("MobileNumber", DataConst.BIGINT);	
		c.setAlterability(true);
		c.setNullability(VSMetaColumn.columnNullable);
		c.setCaption("");
		t.addColumn( c );
	
		c = new VSMetaColumn("MemberState", DataConst.INTEGER);
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
	
		
		t.addUniqueKeyColumn( "Member Unique Key", "PKMember" );
		
		// Register a foreign key from  to Library.
		t.addForeignKeyColumn( "Foreign Key To Library", "FKLibrary" );
	
		
		
	
		t.useQuotedIdentifier(true);
		t.setOptLock( DataConst.OptLockingOnApplicable );
	
		
	
	//END_META_QUERY_COLUMN_CTOR}}
		q.addTable( t );
		q.populateQCArray();
		VSQueryDefinition qTemp = (VSQueryDefinition)getMetaQuery( "Member",deployedFromRepository ); 
		if ( qTemp == null ) {			
			addMetaQuery(q, deployedFromRepository);
		}					
		else
			q = qTemp;	// Keep the old query as it has cached object
			
	
		
	
	//END_COMPONENT_META_QRY}}

	//{{COMPONENT_RULES
	
























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
	return "Member";
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
* Constructor for the class MemberBaseImpl.
* @param Session session object on which the Business object is created.
* @param boolean if true sets the default values in the data elements
*        as defined in the meta data.
*/
public	MemberBaseImpl(Session session, boolean makeDefaults)
{
	super(session, MemberBaseImpl.getMetaQuery(), makeDefaults);
	addListeners();
}


/**
*	Read DataObject extended property, set the cache size and expiration time for a perticular Object type
*	This should only be done once per class type, use "initialized" as flag.
*/
public static void initCache() {
	if ( initialized ) return ;
		
	AppObjectImpl appObject = AppObjectImpl.getBusinessObject( deployedFromRepository, "Member", (Session)ServerEnvironment.getServer().getInternalSession());
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "Member", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "Member","", (String)null);
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
		Enumeration e= aSession.getTransactionInfo().getObjects(MemberBaseImpl.getMetaQuery(), xdac, searchReq);
		
		// ******************* cache support
		if ( cacheSize!=0 && e.hasMoreElements()) {
			Vector boList = new Vector();
			while ( e.hasMoreElements()) {
				boList.addElement( e.nextElement());
			}
			
			CacheAgent.getCacheAgent().setObjects( MemberBaseImpl.getMetaQuery(), boList, expires );
			
			return boList.elements();
		
		} else
			return e;
	
	} else {
		Vector boList = new Vector();
				Connection con = aSession.getConnection( xdac, true );
		ResultSet rs = new VSORBResultSetImpl( MemberBaseImpl.getMetaQuery(), searchReq, null, -1 ,DataConst.NONE_LOB, xdac, con );
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
			CacheAgent.getCacheAgent().setObjects( MemberBaseImpl.getMetaQuery(), boList, expires );
			
		return boList.elements();
	}
}

public static void raiseBeforeQueryEvent( SearchRequest searchReq, Session aSession )	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"Member.BeforeQuery");
	}

	beforeQuery( "Member", searchReq, null , aSession );
	
	if (  tr != null ) tr.end( tr_id );
}

public static void raiseAfterQueryEvent( ResultSet rs)	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"Member.AfterQuery");
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "Member", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "Member","", (String)null);
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
		anObject = CacheAgent.getCacheAgent().getObject( MemberBaseImpl.getMetaQuery(),key.parameterList, aSession);
		
		logger.fine(" Cache :"+ anObject);
		
		if (anObject!=null)  {
			//set current session to this cached object, ensure that object has a active session
			anObject.setSession( aSession );
			return anObject;
		}
	}

	if (aSession.isTransactionInProgress()) {
		anObject = aSession.getTransactionInfo().getObjectByKey(MemberBaseImpl.getMetaQuery(),key);
	} else {		
		anObject = aSession.getObjectByKey(MemberBaseImpl.getMetaQuery(),key);
	}
	
	//**************************** cache support
	if ( cacheSize != 0 )
		 CacheAgent.getCacheAgent().addObject( MemberBaseImpl.getMetaQuery(),anObject, expires );
		 
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
	setCurrentReservations((int)0);
	setCurrentLoans((int)0);
	
	XDAConnector xdac = getXDAConnector();
	// For the counter field set and get the counter value
	Object counter = null;

	if ( xdac instanceof XDASQLConnector ) {
		if ( ( counter = ((XDASQLConnector)xdac).getCounter(MemberBaseImpl.getMetaQuery().getBaseTable("Member"), 
		   session.getTransactionInfo().getConnection( xdac), rowData,"PKMember" ) )!= null )	
		   this.setPKMember( ((Long)counter).longValue());
	}
	else {
		if ( ( counter = xdac.getCounter(MemberBaseImpl.getMetaQuery().getBaseTable("Member"), 
		   session.getTransactionInfo().getConnection( xdac), rowData ) )!= null )	
		   this.setPKMember( ((Long)counter).longValue());
	}

	// Do the parent checks.
	this.parentCheckFor_Library();


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


	// Do Child Cascades.
	// Child Cascade for Role prmber)-Reservation(Reservation): Member->>Reservation
	this.childCascadeFor_Reservation();
	// Child Cascade for Role prmber)-Loan(Loan): Member->>Loan
	this.childCascadeFor_Loan();
	// Child Cascade for Role prmber)-EnterEvent(EnterEvent): Member->>EnterEvent
	this.childCascadeFor_EnterEvent();
	// Child Cascade for Role prmber)-BorrowEvent(BorrowEvent): Member->>BorrowEvent
	this.childCascadeFor_BorrowEvent();
	// Child Cascade for Role prmber)-ReserveEvent(ReserveEvent): Member->>ReserveEvent
	this.childCascadeFor_ReserveEvent();
	// Child Cascade for Role prmber)-LeaveEvent(LeaveEvent): Member->>LeaveEvent
	this.childCascadeFor_LeaveEvent();
	// Do the Parent Adjustment.
	// Parent Adjustment for Role cribrary)-Member(Member) : Member->> Library
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
	if ( getGlobalNestLevel() == 1 && isAltered("PKMember") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'PKMember' in Object Member is not Alterable. Error Column: <Member>.<PKMember>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("FKLibrary") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'FKLibrary' in Object Member is not Alterable. Error Column: <Member>.<FKLibrary>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("MemberState") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'MemberState' in Object Member is not Alterable. Error Column: <Member>.<MemberState>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("CurrentReservations") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'CurrentReservations' in Object Member is not Alterable. Error Column: <Member>.<CurrentReservations>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("CurrentLoans") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'CurrentLoans' in Object Member is not Alterable. Error Column: <Member>.<CurrentLoans>");
	}

	// Do the parent checks.
	this.parentCheckFor_Library();

	// Set the formulae values at this point
	this.setFormulaValues();


	// Do Column Validation Checks
	this.columnValidationCheck();

	// Do TableConstraintChecks
	this.tableConstraintCheck();
	// verify that not nullable columns are not null.



	// Compute stored values if not already done
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
	// Child Cascade for Role prmber)-Reservation(Reservation): Member->>Reservation
	this.childCascadeFor_Reservation();
	// Child Cascade for Role prmber)-Loan(Loan): Member->>Loan
	this.childCascadeFor_Loan();
	// Child Cascade for Role prmber)-EnterEvent(EnterEvent): Member->>EnterEvent
	this.childCascadeFor_EnterEvent();
	// Child Cascade for Role prmber)-BorrowEvent(BorrowEvent): Member->>BorrowEvent
	this.childCascadeFor_BorrowEvent();
	// Child Cascade for Role prmber)-ReserveEvent(ReserveEvent): Member->>ReserveEvent
	this.childCascadeFor_ReserveEvent();
	// Child Cascade for Role prmber)-LeaveEvent(LeaveEvent): Member->>LeaveEvent
	this.childCascadeFor_LeaveEvent();
	// Do the Parent Adjustment.
	// Parent Adjustment for Role cribrary)-Member(Member) : Member->> Library
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
	this.tableConstraintCheck();
	if ( !isCascadeDeletedInDB() )
	this.updateRowImmediate();

	// Factored out duplicate code passage - Val/Paul 03-17-03
	this.tableConditionActions();

	// Do Child Cascades.
	// Child Cascade for Role prmber)-Reservation(Reservation): Member->>Reservation
	this.childCascadeFor_Reservation();
	// Child Cascade for Role prmber)-Loan(Loan): Member->>Loan
	this.childCascadeFor_Loan();
	// Child Cascade for Role prmber)-EnterEvent(EnterEvent): Member->>EnterEvent
	this.childCascadeFor_EnterEvent();
	// Child Cascade for Role prmber)-BorrowEvent(BorrowEvent): Member->>BorrowEvent
	this.childCascadeFor_BorrowEvent();
	// Child Cascade for Role prmber)-ReserveEvent(ReserveEvent): Member->>ReserveEvent
	this.childCascadeFor_ReserveEvent();
	// Child Cascade for Role prmber)-LeaveEvent(LeaveEvent): Member->>LeaveEvent
	this.childCascadeFor_LeaveEvent();
 

	// Do the Parent Adjustment.
	// Parent Adjustment for Role cribrary)-Member(Member) : Member->> Library
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

	public void tableConstraintCheck() 
	{
	IVSTrace tr = null;  long tr_id = 0;

	try {
		if ( VSTrace.IS_ON ) {
			tr = VSTrace.get(); 
			tr_id = tr.beg(logger);
			tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"tableConstraintCheck").set(VST_OBJECT_NAME,"Member_constrAtLeast1Copy");
		}

		if ( ! (getLibrary( ).getamountOfCopies( ) >= 1) ) {
			raiseException("No copies in library yet. Error Column: <Member>.<PKMember>");
		}
	} catch (Exception ex) {
	 	String dependents[] = { };
		handleConstraintValidationException(ex, "constrAtLeast1Copy", dependents);
	} finally { 
		if (tr != null) tr.end( tr_id );
	}
	
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
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentCheckFor_Library").set(VST_OBJECT_NAME,"Member");
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
			raiseException("Library not found for Member. Error Column: <Member>.<FKLibrary>");
		}
		else
		{
			// Add myself to the new parent cache.
			parent.updateCacheForMember(this, false);
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
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_Reservation").set(VST_OBJECT_NAME,"Member");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKMember")) ))
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
				raiseException("Delete Rejected because there are Reservation found for Member");
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
					raiseException("Update Rejected because there are Reservation found for Member");
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
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_Loan").set(VST_OBJECT_NAME,"Member");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKMember")) ))
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
				raiseException("Delete Rejected because there are Loan found for Member");
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
					raiseException("Update Rejected because there are Loan found for Member");
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
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_EnterEvent").set(VST_OBJECT_NAME,"Member");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKMember")) ))
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
				raiseException("Delete Rejected because there are EnterEvent found for Member");
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
					raiseException("Update Rejected because there are EnterEvent found for Member");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}
	protected void childCascadeFor_BorrowEvent()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_BorrowEvent").set(VST_OBJECT_NAME,"Member");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKMember")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldBorrowEvent(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are BorrowEvent found for Member");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldBorrowEvent();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are BorrowEvent found for Member");
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
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_ReserveEvent").set(VST_OBJECT_NAME,"Member");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKMember")) ))
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
				raiseException("Delete Rejected because there are ReserveEvent found for Member");
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
					raiseException("Update Rejected because there are ReserveEvent found for Member");
				}
				
			}
		}
	}				
	finally { if (  tr != null ) tr.end( tr_id );}
	}
	protected void childCascadeFor_LeaveEvent()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"childCascadeFor_LeaveEvent").set(VST_OBJECT_NAME,"Member");
	}
	
	try 
	{		
	boolean PKeyChanged = false;
	boolean ReplChanged = false;
	Enumeration	children;
	// First find out if the Primary key has changed.
	if (( isUpdated() == true ) &&
	( (isChanged("PKMember")) ))
		PKeyChanged = true;
	else
		PKeyChanged = false;
	

    // Now do the Cascade.
    if (isDeleted())
    {
      // no need to load all children into cache for a delete
      children = getOldLeaveEvent(false);
      if ( children.hasMoreElements() ) // There are children found
      {
				raiseException("Delete Rejected because there are LeaveEvent found for Member");
      }       
    }
    else if ( PKeyChanged || ReplChanged )
	{
		children = getOldLeaveEvent();
		if ( children.hasMoreElements() ) // There are children found
		{
				// This is the case for an Update.
				if ( PKeyChanged )
				{
					raiseException("Update Rejected because there are LeaveEvent found for Member");
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
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentAdjustmentFor_Library").set(VST_OBJECT_NAME,"Member");
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
 
	parentAdjustmentFor_LibraryamountOfMembers(oldParent, newParent, childCascadeUpdate, childCascadeDelete, orphanChildParenting, childReparented);
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

	

protected void parentAdjustmentFor_LibraryamountOfMembers( DataObjectWrapper oldParent, DataObjectWrapper newParent, boolean childCascadeUpdate, boolean childCascadeDelete, boolean orphanChildParenting, boolean childReparented)
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
			newParent.getDataObject().setAdjust("amountOfMembers", 1, true);
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
			oldParent.getDataObject().setAdjust("amountOfMembers",-1,true);
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
			newParent.getDataObject().setAdjust("amountOfMembers",1,true);
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
			newParent.getDataObject().setAdjust("amountOfMembers",1,true);
		else
		{
			// Raise an error here
		}

		if ( !oldParent.isInitialized() )
			oldParent.setDataObject(this.getOldLibrary());
		if ( ! oldParent.isObjNull() )
			oldParent.getDataObject().setAdjust("amountOfMembers",-1,true);
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
		handleAggregateQualificationException(ex, "amountOfMembers", "Library", dependents);
	}	

}


	

	

private ObjectHashtable ReservationCache = null;

/**	  
* <br>
* method to retrieve the Reservation objects for this Member
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
	param.fieldName = "FKMember";
	param.value = getData("PKMember").getString();
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
private ObjectHashtable LoanCache = null;

/**	  
* <br>
* method to retrieve the Loan objects for this Member
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
	param.fieldName = "FKMember";
	param.value = getData("PKMember").getString();
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
private ObjectHashtable EnterEventCache = null;

/**	  
* <br>
* method to retrieve the EnterEvent objects for this Member
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
	param.fieldName = "FKMember";
	param.value = getData("PKMember").getString();
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
private ObjectHashtable BorrowEventCache = null;

/**	  
* <br>
* method to retrieve the BorrowEvent objects for this Member
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of BorrowEvent objects.
*/
public Enumeration getBorrowEvent()
{
	if (!getSession().getProperty("NoCacheBorrowEvent").equals("true"))
	{
		if ( BorrowEventCache != null ) return BorrowEventCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "BorrowEvent";
	param.fieldName = "FKMember";
	param.value = getData("PKMember").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheBorrowEvent").equals("true"))
	{
		BorrowEventCache = new ObjectHashtable();
		for (Enumeration e = (BorrowEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			BorrowEventCache.put(pkey,cacheBO);								
		}
		return (BorrowEventCache.elements());
	}
	else
	{
		return (BorrowEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}
private ObjectHashtable ReserveEventCache = null;

/**	  
* <br>
* method to retrieve the ReserveEvent objects for this Member
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
	param.fieldName = "FKMember";
	param.value = getData("PKMember").getString();
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
private ObjectHashtable LeaveEventCache = null;

/**	  
* <br>
* method to retrieve the LeaveEvent objects for this Member
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of LeaveEvent objects.
*/
public Enumeration getLeaveEvent()
{
	if (!getSession().getProperty("NoCacheLeaveEvent").equals("true"))
	{
		if ( LeaveEventCache != null ) return LeaveEventCache.elements();
	}
	
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "LeaveEvent";
	param.fieldName = "FKMember";
	param.value = getData("PKMember").getString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheLeaveEvent").equals("true"))
	{
		LeaveEventCache = new ObjectHashtable();
		for (Enumeration e = (LeaveEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			LeaveEventCache.put(pkey,cacheBO);								
		}
		return (LeaveEventCache.elements());
	}
	else
	{
		return (LeaveEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}



/**	  
* <br>
* method to retrieve the old Reservation objects for this Member
* old Reservation objects would be different from the new ones usualy if
* the Member has a primary key change.
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
* method to retrieve the old Reservation objects for this Member
* old Reservation objects would be different from the new ones usualy if
* the Member has a primary key change.
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
	param.fieldName = "FKMember";
	param.value = getData("PKMember").getPreviousString();
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
* method to retrieve the old Loan objects for this Member
* old Loan objects would be different from the new ones usualy if
* the Member has a primary key change.
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
* method to retrieve the old Loan objects for this Member
* old Loan objects would be different from the new ones usualy if
* the Member has a primary key change.
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
	param.fieldName = "FKMember";
	param.value = getData("PKMember").getPreviousString();
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
* method to retrieve the old EnterEvent objects for this Member
* old EnterEvent objects would be different from the new ones usualy if
* the Member has a primary key change.
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
* method to retrieve the old EnterEvent objects for this Member
* old EnterEvent objects would be different from the new ones usualy if
* the Member has a primary key change.
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
	param.fieldName = "FKMember";
	param.value = getData("PKMember").getPreviousString();
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
* method to retrieve the old BorrowEvent objects for this Member
* old BorrowEvent objects would be different from the new ones usualy if
* the Member has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old BorrowEvent objects.
*/
  public Enumeration getOldBorrowEvent(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheBorrowEvent");
    if (cache)
      getSession().setProperty("NoCacheBorrowEvent", "false");
    else
      getSession().setProperty("NoCacheBorrowEvent", "true");   
    
    try {
      return getOldBorrowEvent();
    } finally {                     
        getSession().setProperty("NoCacheBorrowEvent", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old BorrowEvent objects for this Member
* old BorrowEvent objects would be different from the new ones usualy if
* the Member has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old BorrowEvent objects.
*/
public Enumeration	getOldBorrowEvent()
{
	if (!getSession().getProperty("NoCacheBorrowEvent").equals("true"))
	{
		if ( BorrowEventCache != null ) return BorrowEventCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "BorrowEvent";
	param.fieldName = "FKMember";
	param.value = getData("PKMember").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheBorrowEvent").equals("true"))
	{
		BorrowEventCache = new ObjectHashtable();
		for (Enumeration e = (BorrowEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			BorrowEventCache.put(pkey,cacheBO);								
		}
		return (BorrowEventCache.elements());
	}
	else
	{
		return (BorrowEventBaseImpl.getObjects(searchReq ,getSession()));
	}
}


/**	  
* <br>
* method to retrieve the old ReserveEvent objects for this Member
* old ReserveEvent objects would be different from the new ones usualy if
* the Member has a primary key change.
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
* method to retrieve the old ReserveEvent objects for this Member
* old ReserveEvent objects would be different from the new ones usualy if
* the Member has a primary key change.
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
	param.fieldName = "FKMember";
	param.value = getData("PKMember").getPreviousString();
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
* method to retrieve the old LeaveEvent objects for this Member
* old LeaveEvent objects would be different from the new ones usualy if
* the Member has a primary key change.
* @param cache     : true if the relationship cache is to be used.
                *                    it will result in all objects being fetched into
                *                    memory (if they have not been cached yet).
* @return Enumeration : the Enumeration of old LeaveEvent objects.
*/
  public Enumeration getOldLeaveEvent(boolean cache)
  {
    String oldCacheProperty = getSession().getProperty("NoCacheLeaveEvent");
    if (cache)
      getSession().setProperty("NoCacheLeaveEvent", "false");
    else
      getSession().setProperty("NoCacheLeaveEvent", "true");   
    
    try {
      return getOldLeaveEvent();
    } finally {                     
        getSession().setProperty("NoCacheLeaveEvent", oldCacheProperty);                    
    }			
  }


    /**	  
* <br>
* method to retrieve the old LeaveEvent objects for this Member
* old LeaveEvent objects would be different from the new ones usualy if
* the Member has a primary key change.
* this method currently does not support additional conditional params.
* @return Enumeration : the Enumeration of old LeaveEvent objects.
*/
public Enumeration	getOldLeaveEvent()
{
	if (!getSession().getProperty("NoCacheLeaveEvent").equals("true"))
	{
		if ( LeaveEventCache != null ) return LeaveEventCache.elements();
	}

	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;

	param = new Parameter();
	param.objName = "LeaveEvent";
	param.fieldName = "FKMember";
	param.value = getData("PKMember").getPreviousString();
	searchReq.add(param);
	if (!getSession().getProperty("NoCacheLeaveEvent").equals("true"))
	{
		LeaveEventCache = new ObjectHashtable();
		for (Enumeration e = (LeaveEventBaseImpl.getObjects(searchReq ,getSession())); e.hasMoreElements();)
		{
			DataObject cacheBO = (DataObject)e.nextElement();
			DataRow row = cacheBO.getRow();
			Vector pkey = row.getPkeyParams();
			LeaveEventCache.put(pkey,cacheBO);								
		}
		return (LeaveEventCache.elements());
	}
	else
	{
		return (LeaveEventBaseImpl.getObjects(searchReq ,getSession()));
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
public void updateCacheForBorrowEvent(BorrowEventBaseImpl child, boolean remove)
{
	if ( BorrowEventCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( BorrowEventCache.containsKey(child.getRow().getPkeyParams()) )
				BorrowEventCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! BorrowEventCache.containsKey(child.getRow().getPkeyParams()) )
				BorrowEventCache.put(child.getRow().getPkeyParams(), child);
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
public void updateCacheForLeaveEvent(LeaveEventBaseImpl child, boolean remove)
{
	if ( LeaveEventCache == null ) return;
	if ( child != null ) {
		if ( remove ) {
			if ( LeaveEventCache.containsKey(child.getRow().getPkeyParams()) )
				LeaveEventCache.remove(child.getRow().getPkeyParams());
		}
		else {
			// Add only if it is not in the cache.
			if ( ! LeaveEventCache.containsKey(child.getRow().getPkeyParams()) )
				LeaveEventCache.put(child.getRow().getPkeyParams(), child);
		}
	}
}

/**	
* Invalidates the relationship cache. Called by the system on transaction begin.
*/
public void invalidateNonTransactionCaches() 
{
 
  ReservationCache = null;
  LoanCache = null;
  EnterEventCache = null;
  BorrowEventCache = null;
  ReserveEventCache = null;
  LeaveEventCache = null;
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
		parent.updateCacheForMember(this, true);
	}
}

/**	  
* <br>
* method to get the Library object for this Member
* this method currently does not support additional conditional params.
* @return Object : the  parent object Library for this Member.
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
* method to set the Library object for this Member.
* @param Object : the  parent object Library for this Member.
*/
public	void	setLibrary(LibraryImpl parentObj)
{
	this.setFKLibrary(parentObj.getPKLibrary());
}


/**	  
* <br>
* method to get the old Library object for this Member
* this method currently does not support additional conditional params.
* @return Object : the  old parent object Library for this Member.
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
	* method to get the MobileNumber attribute for the Member
	* @return long : the  value of the attribute MobileNumber as long.
	*/
	public long	getMobileNumber() 
	{
	return getData("MobileNumber").getlong();
	}

	/**	  
	* <br>
	* method to set the MobileNumber attribute for the Member
	* @param long : value of the attribute MobileNumber as long.
	* @return nothing
	*/
	public void	setMobileNumber(long value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("MobileNumber");
	dataVal.setlong(value);
	}

	/**	  
	* <br>
	* method to get the old MobileNumber attribute for the Member
	* @return long : the  value of the old attribute MobileNumber as long.
	*/
	public long	getOldMobileNumber()
	{
	return getData("MobileNumber").getPreviouslong();
	}

	/**	  
	* <br>
	* method to get the City attribute for the Member
	* @return String : the  value of the attribute City as String.
	*/
	public String	getCity() 
	{
	return getData("City").getString();
	}

	/**	  
	* <br>
	* method to set the City attribute for the Member
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
	* method to get the old City attribute for the Member
	* @return String : the  value of the old attribute City as String.
	*/
	public String	getOldCity()
	{
	return getData("City").getPreviousString();
	}

	/**	  
	* <br>
	* method to get the FirstName attribute for the Member
	* @return String : the  value of the attribute FirstName as String.
	*/
	public String	getFirstName() 
	{
	return getData("FirstName").getString();
	}

	/**	  
	* <br>
	* method to set the FirstName attribute for the Member
	* @param String : value of the attribute FirstName as String.
	* @return nothing
	*/
	public void	setFirstName(String value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("FirstName");
	dataVal.setString(value);
	}

	/**	  
	* <br>
	* method to get the old FirstName attribute for the Member
	* @return String : the  value of the old attribute FirstName as String.
	*/
	public String	getOldFirstName()
	{
	return getData("FirstName").getPreviousString();
	}

	/**	  
	* <br>
	* method to get the FKLibrary attribute for the Member
	* @return long : the  value of the attribute FKLibrary as long.
	*/
	public long	getFKLibrary() 
	{
	return getData("FKLibrary").getlong();
	}

	/**	  
	* <br>
	* method to set the FKLibrary attribute for the Member
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
	* method to get the old FKLibrary attribute for the Member
	* @return long : the  value of the old attribute FKLibrary as long.
	*/
	public long	getOldFKLibrary()
	{
	return getData("FKLibrary").getPreviouslong();
	}

	/**	  
	* <br>
	* method to get the MemberState attribute for the Member
	* @return int : the  value of the attribute MemberState as int.
	*/
	public int	getMemberState() 
	{
	return getData("MemberState").getint();
	}

	/**	  
	* <br>
	* method to set the MemberState attribute for the Member
	* @param int : value of the attribute MemberState as int.
	* @return nothing
	*/
	public void	setMemberState(int value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("MemberState");
	dataVal.setint(value);
	}

	/**	  
	* <br>
	* method to get the old MemberState attribute for the Member
	* @return int : the  value of the old attribute MemberState as int.
	*/
	public int	getOldMemberState()
	{
	return getData("MemberState").getPreviousint();
	}

	/**	  
	* <br>
	* method to get the Country attribute for the Member
	* @return String : the  value of the attribute Country as String.
	*/
	public String	getCountry() 
	{
	return getData("Country").getString();
	}

	/**	  
	* <br>
	* method to set the Country attribute for the Member
	* @param String : value of the attribute Country as String.
	* @return nothing
	*/
	public void	setCountry(String value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("Country");
	dataVal.setString(value);
	}

	/**	  
	* <br>
	* method to get the old Country attribute for the Member
	* @return String : the  value of the old attribute Country as String.
	*/
	public String	getOldCountry()
	{
	return getData("Country").getPreviousString();
	}

	/**	  
	* <br>
	* method to get the LastName attribute for the Member
	* @return String : the  value of the attribute LastName as String.
	*/
	public String	getLastName() 
	{
	return getData("LastName").getString();
	}

	/**	  
	* <br>
	* method to set the LastName attribute for the Member
	* @param String : value of the attribute LastName as String.
	* @return nothing
	*/
	public void	setLastName(String value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("LastName");
	dataVal.setString(value);
	}

	/**	  
	* <br>
	* method to get the old LastName attribute for the Member
	* @return String : the  value of the old attribute LastName as String.
	*/
	public String	getOldLastName()
	{
	return getData("LastName").getPreviousString();
	}

	/**	  
	* <br>
	* method to get the Address attribute for the Member
	* @return String : the  value of the attribute Address as String.
	*/
	public String	getAddress() 
	{
	return getData("Address").getString();
	}

	/**	  
	* <br>
	* method to set the Address attribute for the Member
	* @param String : value of the attribute Address as String.
	* @return nothing
	*/
	public void	setAddress(String value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("Address");
	dataVal.setString(value);
	}

	/**	  
	* <br>
	* method to get the old Address attribute for the Member
	* @return String : the  value of the old attribute Address as String.
	*/
	public String	getOldAddress()
	{
	return getData("Address").getPreviousString();
	}


/**	  
* <br>
* method to get the PKMember attribute for the Member
* @return long : the  value of the attribute PKMember as long.
*/
public long	getPKMember() 
{
	return getData("PKMember").getlong();
}

/**	  
* <br>
* method to set the PKMember attribute for the Member
* @param long : value of the attribute PKMember as long.
* @return nothing
*/
public void	setPKMember(long value)
{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("PKMember");
	dataVal.setlong(value);
}

/**	  
* <br>
* method to get the old PKMember attribute for the Member
* @return long : the  value of the old attribute PKMember as long.
*/
public long	getOldPKMember()
{
	return getData("PKMember").getPreviouslong();
}


	/**	  
	* <br>
	* method to get the count attribute CurrentReservations for the Member
	* @return int : the  value of the attribute CurrentReservations as int.
	*/
	public int	getCurrentReservations() 
	{
	Data dataVal = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Reservation";
	param.fieldName = "FKMember";
	if (getSession().isTransactionInProgress())
	{
		if ( isUpdated() && (getGlobalNestLevel() == 1) && (isChanged("PKMember")))
			param.value = getData("PKMember").getOldString();
		else
			param.value = getData("PKMember").getString();
	}
	else
		param.value = getData("PKMember").getString();
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
			dataVal.setint((ReservationBaseImpl.getMemberCurrentReservations(searchReq, getSession())));
		}

		dataVal.setInitialized(true);
		dataVal.setPushPending(false);
	}
	return dataVal.getint();
	}

/**	  
* <br>
* method to get the value before data change for the count attribute CurrentReservations
* in the data object Member
* @return int : the  value of the attribute CurrentReservations as int before change.
*/
public int	getOldCurrentReservations()
{
	return getData("CurrentReservations").getPreviousint();
}

/**	  
* <br>
* method to set the value of the count attribute CurrentReservations in the data object Member
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
	* method to get the count attribute CurrentLoans for the Member
	* @return int : the  value of the attribute CurrentLoans as int.
	*/
	public int	getCurrentLoans() 
	{
	Data dataVal = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Loan";
	param.fieldName = "FKMember";
	if (getSession().isTransactionInProgress())
	{
		if ( isUpdated() && (getGlobalNestLevel() == 1) && (isChanged("PKMember")))
			param.value = getData("PKMember").getOldString();
		else
			param.value = getData("PKMember").getString();
	}
	else
		param.value = getData("PKMember").getString();
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
			dataVal.setint((LoanBaseImpl.getMemberCurrentLoans(searchReq, getSession())));
		}

		dataVal.setInitialized(true);
		dataVal.setPushPending(false);
	}
	return dataVal.getint();
	}

/**	  
* <br>
* method to get the value before data change for the count attribute CurrentLoans
* in the data object Member
* @return int : the  value of the attribute CurrentLoans as int before change.
*/
public int	getOldCurrentLoans()
{
	return getData("CurrentLoans").getPreviousint();
}

/**	  
* <br>
* method to set the value of the count attribute CurrentLoans in the data object Member
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





	


protected static int getLibraryamountOfMembers(SearchRequest searchReq, Session aSession)
{
	Enumeration dbrows = null;
	MemberBaseImpl row = null;
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
		count = aSession.getTransactionInfo().getObjectsCount(MemberBaseImpl.getMetaQuery(), xdac, searchReq);
	else
	{
		con = aSession.getConnection( xdac, true );
  	  	count = xdac.getRowCount( MemberBaseImpl.getMetaQuery(), searchReq, con );
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
		if (parentColumn.equals("Library.amountOfMembers"))
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
		VSMetaTable table = MemberBaseImpl.getMetaQuery().getChildMostTable();
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
		VSMetaTable table = MemberBaseImpl.getMetaQuery().getChildMostTable();
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
	Properties props = ((VSORBSessionImpl)aSession).getMyDataServiceLoginForObject("Member");
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

