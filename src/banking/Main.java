
package banking;

import banking.dao.DatabaseManager;
import banking.model.*;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== STARTING BANKING SYSTEM ===");

        // 1. Initialize Database
        DatabaseManager.initializeDatabase();

        // 2. Create Users
        BankAdministrator admin = new BankAdministrator(
                "admin1", "adminpass", "S001", "Downtown");

        Customer john = new Individual(
                "john_doe", "pass123", "C001",
                "123 Main St", "John", "Doe", "ID999", true);

        Customer techCorp = new Company(
                "tech_admin", "corppass", "C002",
                "456 Corp Blvd", "TechCorp", "REG123", "Jane Smith");

        // 3. Register Customers
        admin.registerCustomer(john);
        admin.registerCustomer(techCorp);

        DatabaseManager.saveCustomer(john);
        DatabaseManager.saveCustomer(techCorp);

        // 4. Open Accounts
        Account johnsSavings = new SavingsAccount(
                "ACC-1001", "Downtown", john);

        Account johnsCheque = new ChequeAccount(
                "ACC-1002", "Downtown", john,
                "TechCorp", "456 Corp Blvd");

        Account corpsInvestment = new InvestmentAccount(
                "ACC-2001", "Downtown", techCorp);

        admin.openAccountFor(john, johnsSavings);
        admin.openAccountFor(john, johnsCheque);
        admin.openAccountFor(techCorp, corpsInvestment);

        // 5. Perform Transactions
        System.out.println("\n--- Performing Transactions ---");

        johnsSavings.deposit(new BigDecimal("1000.00"));

        johnsCheque.deposit(new BigDecimal("5000.00"));

        ((Withdrawable) johnsCheque).withdraw(
                new BigDecimal("200.00"));

        corpsInvestment.deposit(new BigDecimal("10000.00"));

        ((Withdrawable) corpsInvestment).withdraw(
                new BigDecimal("500.00"));

        // 6. Monthly Interest Processing
        System.out.println("\n--- Interest Calculations ---");

        InterestScheduler scheduler =
                new InterestScheduler("Monthly");

        scheduler.runMonthlyInterest(
                Arrays.asList(
                        johnsSavings,
                        johnsCheque,
                        corpsInvestment
                )
        );
        // Save accounts and transactions
        System.out.println("\n--- Saving Accounts and Transactions ---");


        Account[] accounts = {
                johnsSavings,
                johnsCheque,
                corpsInvestment
        };

        for (Account account : accounts) {

                // Save account details
                DatabaseManager.saveAccount(account);

                //Save all transaction belonging to this account
                for (Transaction transaction : account.getTransactions()){
                        DatabaseManager.saveTransaction(transaction, account);
                }
        }

        // Display saved customers
        DatabaseManager.displayDatabaseRecords();

        // 7. Authentication
        System.out.println("\n--- Authentication ---");

        System.out.println(
                "Is John's password correct? "
                + john.authenticate("pass123"));

        System.out.println(
                "Is John's password correct? "
                + john.authenticate("wrongpass"));

        john.login("john_doe", "pass123");

        admin.logout();

        System.out.println("\n=== BANKING SYSTEM FINISHED ===");
    }
}
