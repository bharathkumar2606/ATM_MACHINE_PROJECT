package com.Atmproject.ExceptionClasses;

import com.Atmproject.Functions.DepositFund;

public class DepositeLimitReachedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DepositFund df = new DepositFund();

	public DepositeLimitReachedException() {
		super("You have reached your Deposite Limit for today.");

	}
}
