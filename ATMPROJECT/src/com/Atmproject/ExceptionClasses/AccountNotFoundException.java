package com.Atmproject.ExceptionClasses;
public class AccountNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "!!Sorry,You entered an Incorrect Account number!!";
	}
}
