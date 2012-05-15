
















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


	// At this point all the computations for the object attributes have been made,
	// therefore save the object.	
	this.updateRowImmediate();

	// Factored out duplicate code passage - Val/Paul 03-17-03
	this.tableConditionActions();


	// Do Child Cascades.
	// Child Cascade for Role pribrary)-Member(Member): Library->>Member
	this.childCascadeFor_Member();
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
  CopyCache = null;
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
