package com.Atmproject.Functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Atmproject.DatabaseConnection;

public class AccountManager{
	private Connection connection;
	PreparedStatement ps;
	ResultSet result;
	/// QUERIES START
	String checkaccountQuery = "select account_no from accountdetails where account_no=?";
	String fetchPinQuery = "select security_pin from accountdetails where account_no=?";
	String fetchBalanceQuery = "select account_balance from accountdetails where account_no=?";
	String fetchCustomerNameQuery = "select customer_name from accountdetails where account_no=?";
	String creditBalanceQuery = "update accountdetails set account_balance=account_balance+ ? where account_no=?";
	String debitBalanceQuery = "update accountdetails set account_balance=account_balance- ? where account_no=?";

	// END

	protected boolean checkAccountNumber(int accountNumber) throws SQLException {
		connection = DatabaseConnection.getConnection();
		ps = connection.prepareStatement(checkaccountQuery);
		ps.setInt(1, accountNumber);
		result = ps.executeQuery();
		return result.next();
	}

	protected String getCustomerName(int accountNumber) throws SQLException {
		String customerName = null;
		if (checkAccountNumber(accountNumber)) {
			ps = connection.prepareStatement(fetchCustomerNameQuery);
			ps.setInt(1, accountNumber);
			result = ps.executeQuery();
			result.next();
			customerName = result.getString(1);
			ps.close();
		}
		return customerName;
	}

	protected void creditAmount(double amount, int accountNumber) throws SQLException {
		ps = connection.prepareStatement(creditBalanceQuery);
		ps.setDouble(1, amount);
		ps.setInt(2, accountNumber);
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("***Amount will be credited to your Account***");

		}
		
	}
	protected void debitAmount(double amount,int accountNumber) throws SQLException {
		ps=connection.prepareStatement(debitBalanceQuery);
		ps.setDouble(1, amount);
		ps.setInt(2, accountNumber);
		int rowsAffected=ps.executeUpdate();
		if(rowsAffected>0) {
			System.out.println("***Transaction Succesful***");
		}
	}
	
	public double getBalance(int accountno) throws SQLException {
		ps=connection.prepareStatement(fetchBalanceQuery);
		ps.setInt(1, accountno);
		result=ps.executeQuery();
		result.next();
		return result.getDouble(1);
	}
}
