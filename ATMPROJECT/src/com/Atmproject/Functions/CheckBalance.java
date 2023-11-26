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
	private ResultSet pinset;
	private int s_pin;
	ResultSet result;
	PreparedStatement ps;

	AccountManager checkBalance = new AccountManager();

	public int validatePin() {
		try {
			if (checkBalance.checkAccountNumber(accNo)) {

				System.out.print("Enter your 4 digit PIN number: ");
				try {
					pin = scan.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("***Security PIN don't have Aplhabetics Nor Decimals***");
					Menu.options();
				}

				ps = connection.prepareStatement(checkBalance.fetchPinQuery);
				ps.setInt(1, accNo);
				pinset = ps.executeQuery();
				s_pin = pinset.getInt(1);

			}
		} catch (Exception e) {
			
		}
		return s_pin;
	}

	public void getBalance() throws Exception {
		connection = DatabaseConnection.getConnection();
		System.out.print("Enter your Account Number: ");
		try {
			accNo = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("***Account number can only consist of Whole Numericals***");
			Menu.options();
		}

		if (pinset.next()) {

			if (s_pin == pin) {
				ps = connection.prepareStatement(checkBalance.fetchBalanceQuery);
				ps.setInt(1, accNo);
				ResultSet balance = ps.executeQuery();
				if (balance.next()) {
					System.out.println("***ACCOUNT BALANCE IS " + balance.getInt(1) + "***");
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
