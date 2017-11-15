package com.umg.helpdesk.exception;

@SuppressWarnings("serial")
public class ServiceLocatorException extends Exception {
	public static final String CLASSNAME = "ServiceLocatorException: ";
	
	private String msg;
	private Exception exception;
	
	public ServiceLocatorException() {
		super();

		this.msg = null;
		this.exception = null;
	}
	
	public ServiceLocatorException(String msg) {
		this.msg = msg;
	}
	
	public ServiceLocatorException(Exception exception) {
		this.exception = exception;
	}
	
	public ServiceLocatorException(String msg, Exception exception) {
		this.msg = msg;
		this.exception = exception;
	}
	
	public String getMessage() {
		return CLASSNAME + this.msg;
	}

	public Exception getException() {
		return this.exception;
	}		
}
