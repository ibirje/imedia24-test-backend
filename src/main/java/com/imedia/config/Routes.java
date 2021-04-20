package com.imedia.config;

/**
 * rest endpoints.
 */
public interface Routes {

	
	
	
	
	/* ----------------------------------------------------------------------------------------------------
	 * product related routes
	 * ----------------------------------------------------------------------------------------------------*/

	public static final String PRODUCTS = "/product";
	
	public static final String CATEGORIES = "/category";
	
	/* --------------------------------------------------------------------------------------------------- */

	

	/* ----------------------------------------------------------------------------------------------------
	 * currency route
	 * ----------------------------------------------------------------------------------------------------*/

	public static final String CURRENCY = "/currency";
	
	public static final String CONVERT = "/convert";
	

	/* ----------------------------------------------------------------------------------------------------
	 * common sub-routes
	 * ----------------------------------------------------------------------------------------------------*/
	

	
	public static final String PING   = "/ping";
	
	public static final String REF = "/{ref}";
	
	public static final String DETAILS = "/details";
	
	public static final String INFOS = "/infos";
	
	public static final String FIRST = "/first";

	public static final String LATEST = "/latest";

	public static final String LIST   = "/list";
	
	/* ----------------------------------------------------------------------------------------------------*/

	public static final String SIGNUP = "/signup";
	

	

	
}