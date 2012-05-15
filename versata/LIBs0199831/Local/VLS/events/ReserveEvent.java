










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
	return "ReserveEvent";
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
* Constructor for the class ReserveEventBaseImpl.
* @param Session session object on which the Business object is created.
* @param boolean if true sets the default values in the data elements
*        as defined in the meta data.
*/
public	ReserveEventBaseImpl(Session session, boolean makeDefaults)
{
	super(session, ReserveEventBaseImpl.getMetaQuery(), makeDefaults);
	addListeners();
}


/**
*	Read DataObject extended property, set the cache size and expiration time for a perticular Object type
*	This should only be done once per class type, use "initialized" as flag.
*/
public static void initCache() {
	if ( initialized ) return ;
		
	AppObjectImpl appObject = AppObjectImpl.getBusinessObject( deployedFromRepository, "ReserveEvent", (Session)ServerEnvironment.getServer().getInternalSession());
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "ReserveEvent", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "ReserveEvent","", (String)null);
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
		Enumeration e= aSession.getTransactionInfo().getObjects(ReserveEventBaseImpl.getMetaQuery(), xdac, searchReq);
		
		// ******************* cache support
		if ( cacheSize!=0 && e.hasMoreElements()) {
			Vector boList = new Vector();
			while ( e.hasMoreElements()) {
				boList.addElement( e.nextElement());
			}
			
			CacheAgent.getCacheAgent().setObjects( ReserveEventBaseImpl.getMetaQuery(), boList, expires );
			
			return boList.elements();
		
		} else
			return e;
	
	} else {
		Vector boList = new Vector();
				Connection con = aSession.getConnection( xdac, true );
		ResultSet rs = new VSORBResultSetImpl( ReserveEventBaseImpl.getMetaQuery(), searchReq, null, -1 ,DataConst.NONE_LOB, xdac, con );
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
			CacheAgent.getCacheAgent().setObjects( ReserveEventBaseImpl.getMetaQuery(), boList, expires );
			
		return boList.elements();
	}
}

public static void raiseBeforeQueryEvent( SearchRequest searchReq, Session aSession )	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"ReserveEvent.BeforeQuery");
	}

	beforeQuery( "ReserveEvent", searchReq, null , aSession );
	
	if (  tr != null ) tr.end( tr_id );
}

public static void raiseAfterQueryEvent( ResultSet rs)	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"ReserveEvent.AfterQuery");
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "ReserveEvent", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "ReserveEvent","", (String)null);
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
		anObject = CacheAgent.getCacheAgent().getObject( ReserveEventBaseImpl.getMetaQuery(),key.parameterList, aSession);
		
		logger.fine(" Cache :"+ anObject);
		
		if (anObject!=null)  {
			//set current session to this cached object, ensure that object has a active session
			anObject.setSession( aSession );
			return anObject;
		}
	}

	if (aSession.isTransactionInProgress()) {
		anObject = aSession.getTransactionInfo().getObjectByKey(ReserveEventBaseImpl.getMetaQuery(),key);
	} else {		
		anObject = aSession.getObjectByKey(ReserveEventBaseImpl.getMetaQuery(),key);
	}
	
	//**************************** cache support
	if ( cacheSize != 0 )
		 CacheAgent.getCacheAgent().addObject( ReserveEventBaseImpl.getMetaQuery(),anObject, expires );
		 
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
		tr.set(VST_CATEGORY,"VST_RULE_CONDITIONACTION").set(VST_ACTION_NAME, this.getClass().getName() + "_reserveCopy");
	}
	try {
	
		if (  ( isInserted() == true )  )
		{
			getCopy( ).setCopyState(4);
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


	
	XDAConnector xdac = getXDAConnector();
	// For the counter field set and get the counter value
	Object counter = null;

	if ( xdac instanceof XDASQLConnector ) {
		if ( ( counter = ((XDASQLConnector)xdac).getCounter(ReserveEventBaseImpl.getMetaQuery().getBaseTable("ReserveEvent"), 
		   session.getTransactionInfo().getConnection( xdac), rowData,"PKReserveEvent" ) )!= null )	
		   this.setPKReserveEvent( ((Long)counter).longValue());
	}
	else {
		if ( ( counter = xdac.getCounter(ReserveEventBaseImpl.getMetaQuery().getBaseTable("ReserveEvent"), 
		   session.getTransactionInfo().getConnection( xdac), rowData ) )!= null )	
		   this.setPKReserveEvent( ((Long)counter).longValue());
	}

	// Do the parent checks.
	this.parentCheckFor_Copy();
	this.parentCheckFor_Reservation();
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
	if ( getGlobalNestLevel() == 1 && isAltered("FKReservation") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'FKReservation' in Object ReserveEvent is not Alterable. Error Column: <ReserveEvent>.<FKReservation>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("PKReserveEvent") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'PKReserveEvent' in Object ReserveEvent is not Alterable. Error Column: <ReserveEvent>.<PKReserveEvent>");
	}

	// Do the parent checks.
	this.parentCheckFor_Copy();
	this.parentCheckFor_Reservation();
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
	removeMeFromCopyCache();
	removeMeFromReservationCache();
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
			tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"tableConstraintCheck").set(VST_OBJECT_NAME,"ReserveEvent_Copy 1");
		}

		if ( ! (getCopy( ).getCopyState( ) == 3) ) {
			raiseException("Illegal state copy Error Column: <ReserveEvent>.<FKCopy>");
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
			tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"tableConstraintCheck").set(VST_OBJECT_NAME,"ReserveEvent_Copy 2");
		}

		if (getCopy( ).getCurrentLoans( ) == 0) {
			raiseException("Illegal state copy Error Column: <ReserveEvent>.<FKCopy>");
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
			tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"tableConstraintCheck").set(VST_OBJECT_NAME,"ReserveEvent_Member 1 ");
		}

		if ( ! (getMember( ).getMemberState( ) == 1) ) {
			raiseException("Illegal state Member Error Column: <ReserveEvent>.<FKMember>");
		}
	} catch (Exception ex) {
	 	String dependents[] = { };
		handleConstraintValidationException(ex, "Member 1 ", dependents);
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
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentCheckFor_Copy").set(VST_OBJECT_NAME,"ReserveEvent");
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
			raiseException("Copy not found for ReserveEvent. Error Column: <ReserveEvent>.<FKCopy>");
		}
		else
		{
			// Add myself to the new parent cache.
			parent.updateCacheForReserveEvent(this, false);
		}
		
	}
		
	
	}
	finally { if (  tr != null ) tr.end( tr_id ); }
	}
	protected void parentCheckFor_Reservation()
	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentCheckFor_Reservation").set(VST_OBJECT_NAME,"ReserveEvent");
	}
	
	try {
	
	ReservationImpl	parent = null;
	boolean		ParentKeyChanged = false;
	boolean		OrphanChildParenting;
	if (( isInserted() || isUpdated() ) && ( 
	(isChanged("FKReservation"))
 ) &&
	((!isNull("FKReservation")) ))
	{
		// This would cause the row to be dropped from the old parent if it exist.
		removeMeFromReservationCache();
		parent = this.getReservation();
			
		if ( parent == null )
		{
			raiseException("Reservation not found for ReserveEvent. Error Column: <ReserveEvent>.<FKReservation>");
		}
		else
		{
			// Add myself to the new parent cache.
			parent.updateCacheForReserveEvent(this, false);
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
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentCheckFor_Member").set(VST_OBJECT_NAME,"ReserveEvent");
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
			raiseException("Member not found for ReserveEvent. Error Column: <ReserveEvent>.<FKMember>");
		}
		else
		{
			// Add myself to the new parent cache.
			parent.updateCacheForReserveEvent(this, false);
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
		parent.updateCacheForReserveEvent(this, true);
	}
}
public void removeMeFromReservationCache()
{
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Reservation";
	param.fieldName = "PKReservation";
	param.value = getData("FKReservation").getPreviousString();
	searchReq.add(param);
	if (getSession().getTransactionInfo().isInCache(ReservationImpl.getMetaQuery(), searchReq))
	{
		ReservationBaseImpl	parent = this.getOldReservation();
		parent.updateCacheForReserveEvent(this, true);
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
		parent.updateCacheForReserveEvent(this, true);
	}
}

/**	  
* <br>
* method to get the Copy object for this ReserveEvent
* this method currently does not support additional conditional params.
* @return Object : the  parent object Copy for this ReserveEvent.
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
* method to set the Copy object for this ReserveEvent.
* @param Object : the  parent object Copy for this ReserveEvent.
*/
public	void	setCopy(CopyImpl parentObj)
{
	this.setFKCopy(parentObj.getPKCopy());
}

/**	  
* <br>
* method to get the Reservation object for this ReserveEvent
* this method currently does not support additional conditional params.
* @return Object : the  parent object Reservation for this ReserveEvent.
*/
public	ReservationImpl	getReservation()
{
	ReservationImpl parent = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Reservation";
	param.fieldName = "PKReservation";
	param.value = getData("FKReservation").getString();
	searchReq.add(param);
	parent = (ReservationImpl)(ReservationBaseImpl.getObjectByKey(searchReq ,getSession()));
	return parent;
}

/**	  
* <br>
* method to set the Reservation object for this ReserveEvent.
* @param Object : the  parent object Reservation for this ReserveEvent.
*/
public	void	setReservation(ReservationImpl parentObj)
{
	this.setFKReservation(parentObj.getPKReservation());
}

/**	  
* <br>
* method to get the Member object for this ReserveEvent
* this method currently does not support additional conditional params.
* @return Object : the  parent object Member for this ReserveEvent.
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
* method to set the Member object for this ReserveEvent.
* @param Object : the  parent object Member for this ReserveEvent.
*/
public	void	setMember(MemberImpl parentObj)
{
	this.setFKMember(parentObj.getPKMember());
}


/**	  
* <br>
* method to get the old Copy object for this ReserveEvent
* this method currently does not support additional conditional params.
* @return Object : the  old parent object Copy for this ReserveEvent.
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
* method to get the old Reservation object for this ReserveEvent
* this method currently does not support additional conditional params.
* @return Object : the  old parent object Reservation for this ReserveEvent.
*/
public	ReservationImpl	getOldReservation()
{
	ReservationImpl parent = null;
	SearchRequest searchReq = new SearchRequest();
	Parameter param = null;
	param = new Parameter();
	param.objName = "Reservation";
	param.fieldName = "PKReservation";
	param.value = getData("FKReservation").getPreviousString();
	searchReq.add(param);
	parent = (ReservationImpl)(ReservationBaseImpl.getObjectByKey(searchReq ,getSession()));
	return parent;
}
/**	  
* <br>
* method to get the old Member object for this ReserveEvent
* this method currently does not support additional conditional params.
* @return Object : the  old parent object Member for this ReserveEvent.
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
	* method to get the FKReservation attribute for the ReserveEvent
	* @return long : the  value of the attribute FKReservation as long.
	*/
	public long	getFKReservation() 
	{
	return getData("FKReservation").getlong();
	}

	/**	  
	* <br>
	* method to set the FKReservation attribute for the ReserveEvent
	* @param long : value of the attribute FKReservation as long.
	* @return nothing
	*/
	public void	setFKReservation(long value)
	{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("FKReservation");
	dataVal.setlong(value);
	}

	/**	  
	* <br>
	* method to get the old FKReservation attribute for the ReserveEvent
	* @return long : the  value of the old attribute FKReservation as long.
	*/
	public long	getOldFKReservation()
	{
	return getData("FKReservation").getPreviouslong();
	}

	/**	  
	* <br>
	* method to get the FKCopy attribute for the ReserveEvent
	* @return long : the  value of the attribute FKCopy as long.
	*/
	public long	getFKCopy() 
	{
	return getData("FKCopy").getlong();
	}

	/**	  
	* <br>
	* method to set the FKCopy attribute for the ReserveEvent
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
	* method to get the old FKCopy attribute for the ReserveEvent
	* @return long : the  value of the old attribute FKCopy as long.
	*/
	public long	getOldFKCopy()
	{
	return getData("FKCopy").getPreviouslong();
	}

	/**	  
	* <br>
	* method to get the FKMember attribute for the ReserveEvent
	* @return long : the  value of the attribute FKMember as long.
	*/
	public long	getFKMember() 
	{
	return getData("FKMember").getlong();
	}

	/**	  
	* <br>
	* method to set the FKMember attribute for the ReserveEvent
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
	* method to get the old FKMember attribute for the ReserveEvent
	* @return long : the  value of the old attribute FKMember as long.
	*/
	public long	getOldFKMember()
	{
	return getData("FKMember").getPreviouslong();
	}


/**	  
* <br>
* method to get the PKReserveEvent attribute for the ReserveEvent
* @return long : the  value of the attribute PKReserveEvent as long.
*/
public long	getPKReserveEvent() 
{
	return getData("PKReserveEvent").getlong();
}

/**	  
* <br>
* method to set the PKReserveEvent attribute for the ReserveEvent
* @param long : value of the attribute PKReserveEvent as long.
* @return nothing
*/
public void	setPKReserveEvent(long value)
{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("PKReserveEvent");
	dataVal.setlong(value);
}

/**	  
* <br>
* method to get the old PKReserveEvent attribute for the ReserveEvent
* @return long : the  value of the old attribute PKReserveEvent as long.
*/
public long	getOldPKReserveEvent()
{
	return getData("PKReserveEvent").getPreviouslong();
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
		count = aSession.getTransactionInfo().getObjectsCount(ReserveEventBaseImpl.getMetaQuery(), xdac, searchReq);
	else
	{
		con = aSession.getConnection( xdac, true );
  	  	count = xdac.getRowCount( ReserveEventBaseImpl.getMetaQuery(), searchReq, con );
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
		VSMetaTable table = ReserveEventBaseImpl.getMetaQuery().getChildMostTable();
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
		VSMetaTable table = ReserveEventBaseImpl.getMetaQuery().getChildMostTable();
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
	Properties props = ((VSORBSessionImpl)aSession).getMyDataServiceLoginForObject("ReserveEvent");
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
