package com.mttl.vlms.web.common;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory exceptionHandlerFactory;

	/**
	 * CustomExceptionHandlerFactory
	 * 
	 */
	public CustomExceptionHandlerFactory() {
	}

	/**
	 * CustomExceptionHandlerFactory
	 * 
	 * @param exceptionHandlerFactory
	 *            ExceptionHandlerFactory
	 */
	public CustomExceptionHandlerFactory(ExceptionHandlerFactory exceptionHandlerFactory) {
		this.exceptionHandlerFactory = exceptionHandlerFactory;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		return new CustomExceptionHandler(exceptionHandlerFactory.getExceptionHandler());
	}
}
