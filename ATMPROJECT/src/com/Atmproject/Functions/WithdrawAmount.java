package com.Atmproject.Functions;

import java.sql.SQLException;
import java.util.Scanner;

import com.Atmproject.ExceptionClasses.InsufficientFundException;

public class WithdrawAmount {
	AccountManager am = new AccountManager();
	CheckBalance cb = new CheckBalance();
	private Scanner scan = new Scanner(System.in);
	private int accNo;

	protected void withDraw() throws SQLException {
		System.out.print("Enter Account Number: ");
		accNo=scan.nextInt();
		
			if (am.checkAccountNumber(accNo)) {
				System.out.println("Welcome " + am.getCustomerName(accNo) + "\n" + "Account Number: " + accNo);
		
		System.out.print("Enter Amount to withdraw: ");
		double amount = scan.nextDouble();
		try {
			if (amount > am.getBalance(accNo)) {
				throw new InsufficientFundException();
			} else {
				cb.validatePin();
				am.debitAmount(amount, accNo);
				System.out.println("Withdrawed Amount: "+amount);
				System.out.println("**Available Balance is " + am.getBalance(accNo) + ".**");
			}
		} catch (InsufficientFundException e) {
			e.getMessage();

		} catch (SQLException e) {
			System.out.println("***Sorry Some Error occured.***");
		}
	}

}}
