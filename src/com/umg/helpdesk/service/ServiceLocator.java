package com.umg.helpdesk.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.umg.helpdesk.exception.ServiceLocatorException;

public class ServiceLocator {
	private static ServiceLocator me;
	private InitialContext ctx;
	private Map<String, Object> cache;
	
	static {
		try {
	        me = new ServiceLocator();	        
		} catch(Exception se) {
			System.err.println(se);
	        se.printStackTrace(System.err);
		}
	}
	
	private ServiceLocator() throws ServiceLocatorException {
		try {
			ctx = new InitialContext();
			cache = Collections.synchronizedMap(new HashMap<>());
		} catch (NamingException e) {
			throw new ServiceLocatorException("Error on InitialContext");
		}
	}
	
	static public ServiceLocator getInstance() {
		return me;
	}
	
	public DataSource getDataSource(String dataSourceName) throws ServiceLocatorException {
		DataSource ds = null;
		
		try {
			if (cache.containsKey(dataSourceName)) {
				ds = (DataSource)cache.get(dataSourceName);		
			} else {
				ds = (DataSource)ctx.lookup(dataSourceName);
				cache.put(dataSourceName, ds);
			}
			
		} catch (NamingException e) {
			throw new ServiceLocatorException("JNDI don´t found: " + dataSourceName, e);
		}
		
		return ds;
	}
	
}
