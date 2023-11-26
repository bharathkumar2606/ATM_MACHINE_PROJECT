package com.Atmproject.Functions;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.Atmproject.ExceptionClasses.InsufficientFundException;

public class WithdrawAmount {
	AccountManager am = new AccountManager();
	CheckBalance cb = new CheckBalance();
	private Scanner scan = new Scanner(System.in);
	private int accNo;

	protected void withDraw() throws SQLException {
		System.out.print("Enter Account Number: ");
		try {
			accNo=scan.nextInt();	
		}catch(InputMismatchException e) {
			System.out.println("***Enter Numericals Only.***");
		}
		
		
			if (am.checkAccountNumber(accNo)) {
				System.out.println("Welcome " + am.getCustomerName(accNo) + "\n" + "Account Number: " + accNo);
		
		System.out.print("Enter Amount to withdraw: ");
		double amount=0;
		try {
			 amount= scan.nextDouble();	
		}catch(InputMismatchException e) {
			System.out.println("***Enter Numericals Only.***");
		}
		
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
