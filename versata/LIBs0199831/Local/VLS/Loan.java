












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
	return "Loan";
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
* Constructor for the class LoanBaseImpl.
* @param Session session object on which the Business object is created.
* @param boolean if true sets the default values in the data elements
*        as defined in the meta data.
*/
public	LoanBaseImpl(Session session, boolean makeDefaults)
{
	super(session, LoanBaseImpl.getMetaQuery(), makeDefaults);
	addListeners();
}


/**
*	Read DataObject extended property, set the cache size and expiration time for a perticular Object type
*	This should only be done once per class type, use "initialized" as flag.
*/
public static void initCache() {
	if ( initialized ) return ;
		
	AppObjectImpl appObject = AppObjectImpl.getBusinessObject( deployedFromRepository, "Loan", (Session)ServerEnvironment.getServer().getInternalSession());
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "Loan", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "Loan","", (String)null);
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
		Enumeration e= aSession.getTransactionInfo().getObjects(LoanBaseImpl.getMetaQuery(), xdac, searchReq);
		
		// ******************* cache support
		if ( cacheSize!=0 && e.hasMoreElements()) {
			Vector boList = new Vector();
			while ( e.hasMoreElements()) {
				boList.addElement( e.nextElement());
			}
			
			CacheAgent.getCacheAgent().setObjects( LoanBaseImpl.getMetaQuery(), boList, expires );
			
			return boList.elements();
		
		} else
			return e;
	
	} else {
		Vector boList = new Vector();
				Connection con = aSession.getConnection( xdac, true );
		ResultSet rs = new VSORBResultSetImpl( LoanBaseImpl.getMetaQuery(), searchReq, null, -1 ,DataConst.NONE_LOB, xdac, con );
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
			CacheAgent.getCacheAgent().setObjects( LoanBaseImpl.getMetaQuery(), boList, expires );
			
		return boList.elements();
	}
}

public static void raiseBeforeQueryEvent( SearchRequest searchReq, Session aSession )	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"Loan.BeforeQuery");
	}

	beforeQuery( "Loan", searchReq, null , aSession );
	
	if (  tr != null ) tr.end( tr_id );
}

public static void raiseAfterQueryEvent( ResultSet rs)	{
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_VLS_EVENT_QUERY).set(VST_ACTION_NAME,"Loan.AfterQuery");
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
		if (!aSession.getMyPrivilegeToObjectName(DataConst.AppObjectPrivilegeImpl_READ, "Loan", DataConst.AppObjectTypeCodeImpl_BUSINESS_OBJECT)) {
			throw new ServerException("", VSErrors.VSMSG_SecurityNoReadAccess, "business", "Loan","", (String)null);
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
		anObject = CacheAgent.getCacheAgent().getObject( LoanBaseImpl.getMetaQuery(),key.parameterList, aSession);
		
		logger.fine(" Cache :"+ anObject);
		
		if (anObject!=null)  {
			//set current session to this cached object, ensure that object has a active session
			anObject.setSession( aSession );
			return anObject;
		}
	}

	if (aSession.isTransactionInProgress()) {
		anObject = aSession.getTransactionInfo().getObjectByKey(LoanBaseImpl.getMetaQuery(),key);
	} else {		
		anObject = aSession.getObjectByKey(LoanBaseImpl.getMetaQuery(),key);
	}
	
	//**************************** cache support
	if ( cacheSize != 0 )
		 CacheAgent.getCacheAgent().addObject( LoanBaseImpl.getMetaQuery(),anObject, expires );
		 
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


	
	XDAConnector xdac = getXDAConnector();
	// For the counter field set and get the counter value
	Object counter = null;

	if ( xdac instanceof XDASQLConnector ) {
		if ( ( counter = ((XDASQLConnector)xdac).getCounter(LoanBaseImpl.getMetaQuery().getBaseTable("Loan"), 
		   session.getTransactionInfo().getConnection( xdac), rowData,"PKLoan" ) )!= null )	
		   this.setPKLoan( ((Long)counter).longValue());
	}
	else {
		if ( ( counter = xdac.getCounter(LoanBaseImpl.getMetaQuery().getBaseTable("Loan"), 
		   session.getTransactionInfo().getConnection( xdac), rowData ) )!= null )	
		   this.setPKLoan( ((Long)counter).longValue());
	}

	// Do the parent checks.
	this.parentCheckFor_Copy();
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


	// Do the Parent Adjustment.
	// Parent Adjustment for Role cr)-Loan(Loan) : Loan->> Copy
	this.parentAdjustmentFor_Copy();

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
		raiseException("Attribute 'FKCopy' in Object Loan is not Alterable. Error Column: <Loan>.<FKCopy>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("PKLoan") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'PKLoan' in Object Loan is not Alterable. Error Column: <Loan>.<PKLoan>");
	}
	if ( getGlobalNestLevel() == 1 && isAltered("FKMember") && !isUpdatedAfterInsert() )
	{		
		raiseException("Attribute 'FKMember' in Object Loan is not Alterable. Error Column: <Loan>.<FKMember>");
	}

	// Do the parent checks.
	this.parentCheckFor_Copy();
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

	// Do the Parent Adjustment.
	// Parent Adjustment for Role cr)-Loan(Loan) : Loan->> Copy
	this.parentAdjustmentFor_Copy();
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
	this.tableConstraintCheck();
	if ( !isCascadeDeletedInDB() )
	this.updateRowImmediate();

	// Factored out duplicate code passage - Val/Paul 03-17-03
	this.tableConditionActions();

	// Do Child Cascades.
 

	// Do the Parent Adjustment.
	// Parent Adjustment for Role cr)-Loan(Loan) : Loan->> Copy
	this.parentAdjustmentFor_Copy();

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
			tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"tableConstraintCheck").set(VST_OBJECT_NAME,"Loan_cnstrAlreadyLoanedOut");
		}

		if (getCopy( ).getnumberOfLoans( ) <= 1) {
			raiseException("Copy has already been loaned out Error Column: <Loan>.<PKLoan>");
		}
	} catch (Exception ex) {
	 	String dependents[] = { };
		handleConstraintValidationException(ex, "cnstrAlreadyLoanedOut", dependents);
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
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentCheckFor_Copy").set(VST_OBJECT_NAME,"Loan");
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
			raiseException("Copy not found for Loan. Error Column: <Loan>.<FKCopy>");
		}
		else
		{
			// Add myself to the new parent cache.
			parent.updateCacheForLoan(this, false);
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
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentCheckFor_Member").set(VST_OBJECT_NAME,"Loan");
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
			raiseException("Member not found for Loan. Error Column: <Loan>.<FKMember>");
		}
		else
		{
			// Add myself to the new parent cache.
			parent.updateCacheForLoan(this, false);
		}
		
	}
		
	
	}
	finally { if (  tr != null ) tr.end( tr_id ); }
	}


	protected void parentAdjustmentFor_Copy()
	{
	
	IVSTrace tr = null;  long tr_id = 0;
	if ( VSTrace.IS_ON ) {
		tr = VSTrace.get(); 
		tr_id = tr.beg(logger);
		tr.set(VST_CATEGORY,VST_RULE).set(VST_ACTION_NAME,"parentAdjustmentFor_Copy").set(VST_OBJECT_NAME,"Loan");
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
 
	parentAdjustmentFor_CopynumberOfLoans(oldParent, newParent, childCascadeUpdate, childCascadeDelete, orphanChildParenting, childReparented);
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

	

protected void parentAdjustmentFor_CopynumberOfLoans( DataObjectWrapper oldParent, DataObjectWrapper newParent, boolean childCascadeUpdate, boolean childCascadeDelete, boolean orphanChildParenting, boolean childReparented)
{
	
	try { // trap null pointer exceptions and analyze
	if ( isInserted() )
	{
		// For the case of insertion it does not matter If the columns involved
		// for both the where clause and the from column in child are virtual.
		// simply set the deltas to new values and mark them as usable
		if ( !newParent.isInitialized() )
			newParent.setDataObject(this.getCopy());
		if ( ! newParent.isObjNull() )
			newParent.getDataObject().setAdjust("numberOfLoans", 1, true);
		else
		{
			// Raise an error here
		}
		return;
	}

	if ( isDeleted() && ( ! childCascadeDelete ) )
	{
		if ( !oldParent.isInitialized() )
			oldParent.setDataObject(this.getOldCopy());
		if ( ! oldParent.isObjNull() )
			oldParent.getDataObject().setAdjust("numberOfLoans",-1,true);
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
			newParent.setDataObject(this.getCopy());
		if ( ! newParent.isObjNull() )
			newParent.getDataObject().setAdjust("numberOfLoans",1,true);
		else
		{
			// Raise an error here
		}
		return;
	}
	else if ( childReparented )
	{
 
		if ( !newParent.isInitialized() )
			newParent.setDataObject(this.getCopy());
		if ( ! newParent.isObjNull() )
			newParent.getDataObject().setAdjust("numberOfLoans",1,true);
		else
		{
			// Raise an error here
		}

		if ( !oldParent.isInitialized() )
			oldParent.setDataObject(this.getOldCopy());
		if ( ! oldParent.isObjNull() )
			oldParent.getDataObject().setAdjust("numberOfLoans",-1,true);
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
		handleAggregateQualificationException(ex, "numberOfLoans", "Copy", dependents);
	}	

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
		parent.updateCacheForLoan(this, true);
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
		parent.updateCacheForLoan(this, true);
	}
}

/**	  
* <br>
* method to get the Copy object for this Loan
* this method currently does not support additional conditional params.
* @return Object : the  parent object Copy for this Loan.
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
* method to set the Copy object for this Loan.
* @param Object : the  parent object Copy for this Loan.
*/
public	void	setCopy(CopyImpl parentObj)
{
	this.setFKCopy(parentObj.getPKCopy());
}

/**	  
* <br>
* method to get the Member object for this Loan
* this method currently does not support additional conditional params.
* @return Object : the  parent object Member for this Loan.
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
* method to set the Member object for this Loan.
* @param Object : the  parent object Member for this Loan.
*/
public	void	setMember(MemberImpl parentObj)
{
	this.setFKMember(parentObj.getPKMember());
}


/**	  
* <br>
* method to get the old Copy object for this Loan
* this method currently does not support additional conditional params.
* @return Object : the  old parent object Copy for this Loan.
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
* method to get the old Member object for this Loan
* this method currently does not support additional conditional params.
* @return Object : the  old parent object Member for this Loan.
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
	* method to get the EndDate attribute for the Loan
	* @return VSDate : the  value of the attribute EndDate as VSDate.
	*/
	public VSDate	getEndDate() 
	{
	return getData("EndDate").getVSDate();
	}

	/**	  
	* <br>
	* method to set the EndDate attribute for the Loan
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
	* method to get the old EndDate attribute for the Loan
	* @return VSDate : the  value of the old attribute EndDate as VSDate.
	*/
	public VSDate	getOldEndDate()
	{
	return getData("EndDate").getPreviousVSDate();
	}

	/**	  
	* <br>
	* method to get the FKCopy attribute for the Loan
	* @return long : the  value of the attribute FKCopy as long.
	*/
	public long	getFKCopy() 
	{
	return getData("FKCopy").getlong();
	}

	/**	  
	* <br>
	* method to set the FKCopy attribute for the Loan
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
	* method to get the old FKCopy attribute for the Loan
	* @return long : the  value of the old attribute FKCopy as long.
	*/
	public long	getOldFKCopy()
	{
	return getData("FKCopy").getPreviouslong();
	}

	/**	  
	* <br>
	* method to get the StartDate attribute for the Loan
	* @return VSDate : the  value of the attribute StartDate as VSDate.
	*/
	public VSDate	getStartDate() 
	{
	return getData("StartDate").getVSDate();
	}

	/**	  
	* <br>
	* method to set the StartDate attribute for the Loan
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
	* method to get the old StartDate attribute for the Loan
	* @return VSDate : the  value of the old attribute StartDate as VSDate.
	*/
	public VSDate	getOldStartDate()
	{
	return getData("StartDate").getPreviousVSDate();
	}

	/**	  
	* <br>
	* method to get the FKMember attribute for the Loan
	* @return long : the  value of the attribute FKMember as long.
	*/
	public long	getFKMember() 
	{
	return getData("FKMember").getlong();
	}

	/**	  
	* <br>
	* method to set the FKMember attribute for the Loan
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
	* method to get the old FKMember attribute for the Loan
	* @return long : the  value of the old attribute FKMember as long.
	*/
	public long	getOldFKMember()
	{
	return getData("FKMember").getPreviouslong();
	}


/**	  
* <br>
* method to get the PKLoan attribute for the Loan
* @return long : the  value of the attribute PKLoan as long.
*/
public long	getPKLoan() 
{
	return getData("PKLoan").getlong();
}

/**	  
* <br>
* method to set the PKLoan attribute for the Loan
* @param long : value of the attribute PKLoan as long.
* @return nothing
*/
public void	setPKLoan(long value)
{
	// The code to do convertion from the primitive data
	// to the one which can be stored goes here.
	Data dataVal = getData("PKLoan");
	dataVal.setlong(value);
}

/**	  
* <br>
* method to get the old PKLoan attribute for the Loan
* @return long : the  value of the old attribute PKLoan as long.
*/
public long	getOldPKLoan()
{
	return getData("PKLoan").getPreviouslong();
}





	


protected static int getCopynumberOfLoans(SearchRequest searchReq, Session aSession)
{
	Enumeration dbrows = null;
	LoanBaseImpl row = null;
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
		count = aSession.getTransactionInfo().getObjectsCount(LoanBaseImpl.getMetaQuery(), xdac, searchReq);
	else
	{
		con = aSession.getConnection( xdac, true );
  	  	count = xdac.getRowCount( LoanBaseImpl.getMetaQuery(), searchReq, con );
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
		if (parentColumn.equals("Copy.numberOfLoans"))
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
		VSMetaTable table = LoanBaseImpl.getMetaQuery().getChildMostTable();
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
		VSMetaTable table = LoanBaseImpl.getMetaQuery().getChildMostTable();
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
	Properties props = ((VSORBSessionImpl)aSession).getMyDataServiceLoginForObject("Loan");
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
