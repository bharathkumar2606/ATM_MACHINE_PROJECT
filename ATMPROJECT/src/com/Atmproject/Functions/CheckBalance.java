package com.Atmproject.Functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.Atmproject.DatabaseConnection;
import com.Atmproject.ExceptionClasses.AccountNotFoundException;

public class CheckBalance {
	private Connection connection;
	private Scanner scan = new Scanner(System.in);
	private int accNo;
	private int pin;
	private ResultSet pinSet;
	private int s_pin;
	ResultSet result;
	PreparedStatement ps;

	void CheckBalance(int accNo) {
		this.accNo = accNo;
	}

	AccountManager checkBalance = new AccountManager();

	public int validatePin() {

		try {
			if (checkBalance.checkAccountNumber(accNo) > 0) {
				System.out.print("Enter four Digit PIN: ");
				
				try {
					pin = scan.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("***Security PIN don't have Aplhabetics Nor Decimals***");
					Menu.options();
				}

				ps = connection.prepareStatement(checkBalance.fetchPinQuery);
				ps.setInt(1, accNo);
				pinSet = ps.executeQuery();
				pinSet.next();
				s_pin = pinSet.getInt(1);

			} else {
				s_pin = 0;

			}
		} catch (Exception e) {

		}
		return s_pin;
	}

	public int getPin() {
		pin = validatePin();
		return pin;
	}

	public void getBalance() throws Exception {
		connection = DatabaseConnection.getConnection();
		System.out.print("Enter your Account Number: ");
		try {
			accNo = scan.nextInt();
			validatePin();
		} catch (InputMismatchException e) {
			System.out.println("***Account number can only consist of Whole Numericals***");
			Menu.options();
		}

		if (pinSet != null) {

			if (s_pin == pin) {
				ps = connection.prepareStatement(checkBalance.fetchBalanceQuery);
				ps.setInt(1, accNo);
				ResultSet balance = ps.executeQuery();
				if (balance.next()) {
					System.out.println();
					System.out.println("***ACCOUNT BALANCE IS Rs." + balance.getInt(1) + "***");
					System.out.println();
					System.out.println("-----------------------------------------------------------------------");
					Menu.options();
				}

			} else {
				System.out.println("Incorrect PIN.");
				Menu.options();
			}
		} else {
			try {
				throw new AccountNotFoundException();
			} catch (AccountNotFoundException e) {
				System.out.println(e.getMessage());
			}

		}
		Menu.options();

	}

	/*
	 * ps.close(); scan.close(); connection.close();
	 */

}
