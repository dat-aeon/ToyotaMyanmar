package com.mttl.vlms.common;

import com.mttl.vlms.exception.DAOException;

/**
 * BasicDAO
 * 
 *  
 */
public class BasicDAO {

	public DAOException translate(String message, RuntimeException e) {
		return new DAOException("", message, e);
	}
}
