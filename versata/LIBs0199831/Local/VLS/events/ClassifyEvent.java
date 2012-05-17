








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
	return "ClassifyEvent";
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
* Constructor for the class ClassifyEventBaseImpl.
* @param Session session object on which the Business object is created.
* @param boolean if true sets the default values in the data elements
*        as defined in the meta data.
*/
public	ClassifyEventBaseImpl(Session session, boolean makeDefaults)
{
	super(session, ClassifyEventBaseImpl.getMetaQuery(), makeDefaults);
	addListeners();
}


/**
*	Read DataObject extended property, set the cache size and expiration time for a perticular Object type
*	This should only be done once per class type, use "initialized" as flag.
*/
public static void initCache() {
	if ( initialized ) return ;
		
	AppObjectImpl appObject = AppObjectImpl.getBusinessObject( deployedFromRepository, "ClassifyEvent", (Session)ServerEnvironment.getServer().getInternalSession());
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "ClassifyEvent", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "ClassifyEvent","", (String)null);
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
		Enumeration e= aSession.getTransactionInfo().getObjects(ClassifyEventBaseImpl.getMetaQuery(), xdac, searchReq);
		
		// ******************* cache support
		if ( cacheSize!=0 && e.hasMoreElements()) {
			Vector boList = new Vector();
			while ( e.hasMoreElements()) {
				boList.addElement( e.nextElement());
			}
			
			CacheAgent.getCacheAgent().setObjects( ClassifyEventBaseImpl.getMetaQuery(), boList, expires );
			
			return boList.elements();
		
		} else
			return e;
	
	} else {
		Vector boList = new Vector();
				Connection con = aSession.getConnection( xdac, true );
		ResultSet rs = new VSORBResultSetImpl( ClassifyEventBaseImpl.getMetaQuery(), searchReq, null, -1 ,DataConst.NONE_LOB, xdac, con );
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
			CacheAgent.getCacheAgent().setObjects( ClassifyEventBaseImpl.getMetaQuery(), boList, expires );
			
		return boList.elements();
	}
}

public static void raiseBeforeQueryEvent( SearchRequest searchReq, Session aSession )	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"ClassifyEvent.BeforeQuery");
	}

	beforeQuery( "ClassifyEvent", searchReq, null , aSession );
	
	if (  tr != null ) tr.end( tr_id );
}

public static void raiseAfterQueryEvent( ResultSet rs)	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"ClassifyEvent.AfterQuery");
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "ClassifyEvent", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "ClassifyEvent","", (String)null);
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
		anObject = CacheAgent.getCacheAgent().getObject( ClassifyEventBaseImpl.getMetaQuery(),key.parameterList, aSession);
		
		logger.fine(" Cache :"+ anObject);
		
		if (anObject!=null)  {
			//set current session to this cached object, ensure that object has a active session
			anObject.setSession( aSession );
			return anObject;
		}
	}

	if (aSession.isTransactionInProgress()) {
		anObject = aSession.getTransactionInfo().getObjectByKey(ClassifyEventBaseImpl.getMetaQuery(),key);
	} else {		
		anObject = aSession.getObjectByKey(ClassifyEventBaseImpl.getMetaQuery(),key);
	}
	
	//**************************** cache support
	if ( cacheSize != 0 )
		 CacheAgent.getCacheAgent().addObject( ClassifyEventBaseImpl.getMetaQuery(),anObject, expires );
		 
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
		tr.set(VST_CATEGORY,"VST_RULE_CONDITIONACTION").set(VST_ACTION_NAME, this.getClass().getName() + "_classifyCopy");
	}
	try {
	
		if (  ( isInserted() == true )  )
		{
			getCopy( ).setCopyState(2);
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
		if ( ( counter = ((XDASQLConnector)xdac).getCounter(ClassifyEventBaseImpl.getMetaQuery().getBaseTable("ClassifyEvent"), 
		   session.getTransactionInfo().getConnection( xdac), rowData,"PKClassifyEvent" ) )!= null )	
		   this.setPKClassifyEvent( ((Long)counter).longValue());
	}
	else {
		if ( ( counter = xdac.getCounter(ClassifyEventBaseImpl.getMetaQuery().getBaseTable("ClassifyEvent"), 
		   session.getTransactionInfo().getConnection( xdac), rowData ) )!= null )	
		   this.setPKClassifyEvent( ((Long)counter).longValue());
	}

	// Do the parent checks.
	this.parentCheckFor_Copy();


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
	if ( getGlobalNestLevel() == 1 && isAltered("FKCopy") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'FKCopy' in Object ClassifyEvent is not Alterable. Error Column: <ClassifyEvent>.<FKCopy>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("PKClassifyEvent") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'PKClassifyEvent' in Object ClassifyEvent is not Alterable. Error Column: <ClassifyEvent>.<PKClassifyEvent>");
	}

	// Do the parent checks.
	this.parentCheckFor_Copy();

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
	removeMeFromCopyCache();

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
			tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"tableConstraintCheck").set(VST_OBJECT_NAME,"ClassifyEvent_Copy 1");
		}

		if ( ! (getCopy( ).getCopyState( ) == 1) ) {
			raiseException("Cannot classify active copy Error Column: <ClassifyEvent>.<FKCopy>");
		}
	} catch (Exception ex) {
	 	String dependents[] = { };
		handleConstraintValidationException(ex, "Copy 1", dependents);
	} finally { 
		if (tr != null) tr.end( tr_id );
	}
	
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
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentCheckFor_Copy").set(VST_OBJECT_NAME,"ClassifyEvent");
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
			raiseException("Copy not found for ClassifyEvent. Error Column: <ClassifyEvent>.<FKCopy>");
		}
		else
		{
			// Add myself to the new parent cache.
			parent.updateCacheForClassifyEvent(this, false);
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
		parent.updateCacheForClassifyEvent(this, true);
	}
}

/**	  
* <br>
* method to get the Copy object for this ClassifyEvent
* this method currently does not support additional conditional params.
* @return Object : the  parent object Copy for this ClassifyEvent.
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
* method to set the Copy object for this ClassifyEvent.
* @param Object : the  parent object Copy for this ClassifyEvent.
*/
public	void	setCopy(CopyImpl parentObj)
{
	this.setFKCopy(parentObj.getPKCopy());
}


/**	  
* <br>
* method to get the old Copy object for this ClassifyEvent
* this method currently does not support additional conditional params.
* @return Object : the  old parent object Copy for this ClassifyEvent.
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
	* method to get the FKCopy attribute for the ClassifyEvent
	* @return long : the  value of the attribute FKCopy as long.
	*/
	public long	getFKCopy() 
	{
	return getData("FKCopy").getlong();
	}

	/**	  
	* <br>
	* method to set the FKCopy attribute for the ClassifyEvent
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
	* method to get the old FKCopy attribute for the ClassifyEvent
	* @return long : the  value of the old attribute FKCopy as long.
	*/
	public long	getOldFKCopy()
	{
	return getData("FKCopy").getPreviouslong();
	}

	/**	  
	* <br>
	* method to get the TimeStamp attribute for the ClassifyEvent
	* @return VSDate : the  value of the attribute TimeStamp as VSDate.
	*/
	public VSDate	getTimeStamp() 
	{
	return getData("TimeStamp").getVSDate();
	}

	/**	  
	* <br>
	* method to set the TimeStamp attribute for the ClassifyEvent
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
	* method to get the old TimeStamp attribute for the ClassifyEvent
	* @return VSDate : the  value of the old attribute TimeStamp as VSDate.
	*/
	public VSDate	getOldTimeStamp()
	{
	return getData("TimeStamp").getPreviousVSDate();
	}


/**	  
* <br>
* method to get the PKClassifyEvent attribute for the ClassifyEvent
* @return long : the  value of the attribute PKClassifyEvent as long.
*/
public long	getPKClassifyEvent() 
{
	return getData("PKClassifyEvent").getlong();
}

/**	  
* <br>
* method to set the PKClassifyEvent attribute for the ClassifyEvent
* @param long : value of the attribute PKClassifyEvent as long.
* @return nothing
*/
public void	setPKClassifyEvent(long value)
{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("PKClassifyEvent");
	dataVal.setlong(value);
}

/**	  
* <br>
* method to get the old PKClassifyEvent attribute for the ClassifyEvent
* @return long : the  value of the old attribute PKClassifyEvent as long.
*/
public long	getOldPKClassifyEvent()
{
	return getData("PKClassifyEvent").getPreviouslong();
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
		count = aSession.getTransactionInfo().getObjectsCount(ClassifyEventBaseImpl.getMetaQuery(), xdac, searchReq);
	else
	{
		con = aSession.getConnection( xdac, true );
  	  	count = xdac.getRowCount( ClassifyEventBaseImpl.getMetaQuery(), searchReq, con );
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
		VSMetaTable table = ClassifyEventBaseImpl.getMetaQuery().getChildMostTable();
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
		VSMetaTable table = ClassifyEventBaseImpl.getMetaQuery().getChildMostTable();
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
	Properties props = ((VSORBSessionImpl)aSession).getMyDataServiceLoginForObject("ClassifyEvent");
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
