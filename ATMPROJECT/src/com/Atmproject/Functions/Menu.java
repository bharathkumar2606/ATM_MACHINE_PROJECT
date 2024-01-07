package com.Atmproject.Functions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private static int option;
	public static void options() throws Exception {
		Scanner scan=new Scanner(System.in);
		CheckBalance checkbalance = new CheckBalance();
		DepositFund depositfund = new DepositFund();
		WithdrawAmount withdraw=new WithdrawAmount();
			System.out.println("Click 1 - To Check Account Balance.");
			System.out.println("Click 2 - To Deposite Amount.");
			System.out.println("Click 3 - To Withdraw Amount.");
			System.out.println("Click 4 - To Exit.");
			System.out.print("Enter your choics: ");
			
			try {
				option = scan.nextInt();
			}catch(InputMismatchException e) {
			}
			
			switch (option) {
			case 1:
				checkbalance.getBalance();
				break;
			case 2:
				depositfund.depositFund();
				break;
			case 3:
				withdraw.withDraw();
				break;
			case 4:
				System.out.println("***Thank you for using our ATM.***");
				System.exit(1);
			default:
				System.out.println("***Invalid option,Choose Valid one.***");
				options();
				break;

			}
			scan.close();
			
		
	}

}
