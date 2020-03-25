package com.mttl.vlms.exception;

/**
 * DAOException
 * 
 *  
 */
public class DAOException extends Exception {

	private static final long serialVersionUID = 6324814954860327772L;

	private String errorCode;

	public DAOException(String errorCode, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
