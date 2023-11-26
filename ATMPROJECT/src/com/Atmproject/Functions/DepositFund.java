package com.Atmproject.Functions;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.Atmproject.DatabaseConnection;
import com.Atmproject.ExceptionClasses.AccountNotFoundException;

public class DepositFund extends AccountManager {
	private int depositamount;
	private int accNo;
	final int maxDepositeLimit = 50000;
	Scanner scan = new Scanner(System.in);

	protected void depositFund() throws Exception {
		AccountManager accountManager = new AccountManager();
		DatabaseConnection.getConnection();
		System.out.print("Enter Account Number: ");
		try {
			accNo = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("***Account number can only consist of Whole Numericals***");
			Menu.options();
		}

		if (accountManager.checkAccountNumber(accNo)) {
			String name = accountManager.getCustomerName(accNo);
			System.out.println("Name :" + name + "\n" + "Account no :" + accNo + ".");
			System.out.print("Enter Amount: ");
			depositamount = scan.nextInt();
			if (depositamount >= 100) {
				/*
				 * if (depositamount == maxDepositeLimit) { try { throw new
				 * DepositeLimitReachedException(); } catch (DepositeLimitReachedException dlre)
				 * { System.out.println(dlre.getMessage()); } finally { scan.close(); } } else
				 * if (depositamount > maxDepositeLimit) {
				 * System.out.println("Maximum amount allowed is " + "Rs." + maxDepositeLimit);
				 * 
				 * }
				 */
				accountManager.creditAmount(depositamount, accNo);
				System.out.println("**Available Balance is " + accountManager.getBalance(accNo) + ".**");

			} else {
				System.out.println("Minimum Rs.100 is Allowed.");
			}
		} else {
			try {
				throw new AccountNotFoundException();
			} catch (AccountNotFoundException anfe) {
				System.out.println(anfe.getMessage());
			}
		}
		Menu.options();
		DatabaseConnection.closeConnection();
	}

}
