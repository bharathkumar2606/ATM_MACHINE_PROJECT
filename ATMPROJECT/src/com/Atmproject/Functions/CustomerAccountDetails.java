/*
 * package com.Atmproject.Functions;
 * 
 * import java.util.HashMap; import java.util.Map;
 * 
 * import com.Atmproject.ExceptionClasses.AccountNotFoundException;
 * 
 * public class CustomerAccountDetails {
 * 
 * private Map<Integer, String> accno_name = new HashMap<>(); private
 * Map<Integer, Double> accno_balance = new HashMap<>();
 * 
 * public void customerAccounts() { accno_name.put(39691234, "Bharath");
 * accno_name.put(39695678, "Kumar"); accno_name.put(39699011, "Mukesh");
 * accno_name.put(39692910, "Deepak"); accno_name.put(39695417, "Samuel");
 * customerBalance();
 * 
 * }
 * 
 * public void customerBalance() { accno_balance.put(39691234, 56000.0);
 * accno_balance.put(39695678, 0.0); accno_balance.put(39699011, 60976.0);
 * accno_balance.put(39692910, 0.0); accno_balance.put(39695417, 0.0); }
 * 
 * public String getCustomername(int acc_no) { String cus_name =
 * accno_name.get(acc_no); return cus_name; }
 * 
 * public double getAccBalance(int acc_no) { Double balance =
 * accno_balance.get(acc_no); return balance; }
 * 
 * public void checkAccount(int acc_no) { if (accno_name.containsKey(acc_no)) {
 * CustomerAccountDetails cad = new CustomerAccountDetails();
 * System.out.println("Your Account Balance is " +
 * cad.accno_balance.get(acc_no));
 * 
 * } else { try { throw new AccountNotFoundException(); } catch
 * (AccountNotFoundException e) { System.out.println(e.getMessage()); }
 * 
 * }
 * 
 * }
 * 
 * public double updateAccbalance(int acc_no, double deposite) { double total =
 * accno_balance.get(acc_no); total += deposite; return total; }
 * 
 * }
 */