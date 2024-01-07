package com.Atmproject.Functions;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.Atmproject.ExceptionClasses.AccountNotFoundException;
import com.Atmproject.ExceptionClasses.InsufficientFundException;

public class WithdrawAmount {
	AccountManager am = new AccountManager();
	CheckBalance cb = new CheckBalance();
	private Scanner scan = new Scanner(System.in);
	private int accNo;
	private int pin;
	private int pinNo;

	protected void withDraw() throws Exception {
		System.out.print("Enter Account Number: ");
		try {
			accNo = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("***Enter Numericals Only.***");
		}

		if (am.checkAccountNumber(accNo) > 0) {
			System.out.println("Welcome " + am.getCustomerName(accNo) + "\n" + "Account Number: " + accNo);
			cb.CheckBalance(accNo);
			pinNo = cb.validatePin();
			if (pinNo > 0) {

				if (pinNo == cb.getPin()) {
					System.out.print("Enter Amount to withdraw: ");
					double amount = 0;
					try {
						amount = scan.nextDouble();
					} catch (InputMismatchException e) {
						System.out.println("***Enter Numericals Only.***");
					}

					try {
						if (amount > am.getBalance(accNo)) {
							throw new InsufficientFundException();
						} else {
							am.debitAmount(amount, accNo);
							System.out.println("Withdrawed Amount: " + amount);
							System.out.println("**Available Balance is " + am.getBalance(accNo) + ".**");
						}
					} catch (InsufficientFundException e) {
						e.getMessage();

					} catch (SQLException e) {
						System.out.println("***Sorry Some Error occured.***");
					}
					Menu.options();
				}

			} else {
				System.out.println("!!Incorrect PIN.!!");
			}

		} else {
			try {
				throw new AccountNotFoundException();
			} catch (AccountNotFoundException anfe) {
				System.out.println(anfe.getMessage());
				Menu.options();
			}
		}

	}
}
