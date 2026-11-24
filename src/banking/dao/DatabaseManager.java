
package banking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.math.BigDecimal;

import banking.view.CustomerRow;
import banking.view.AccountRow;
import banking.view.TransactionRow;

import banking.model.Account;
import banking.model.Customer;
import banking.model.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:banking_system.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // INITIALIZE DATABASE
    public static void initializeDatabase() {

        String customersTable = """
                CREATE TABLE IF NOT EXISTS customers (
                    id TEXT PRIMARY KEY,
                    name TEXT NOT NULL,
                    address TEXT NOT NULL
                )
                """;

        String accountsTable = """
                CREATE TABLE IF NOT EXISTS accounts (
                    account_number TEXT PRIMARY KEY,
                    account_type TEXT NOT NULL,
                    customer_id TEXT NOT NULL,
                    balance TEXT NOT NULL,
                    FOREIGN KEY (customer_id) REFERENCES customers(id)
                )
                """;

        String transactionsTable = """
                CREATE TABLE IF NOT EXISTS transactions (
                    transaction_id TEXT PRIMARY KEY,
                    account_number TEXT NOT NULL,
                    transaction_date TEXT NOT NULL,
                    transaction_type TEXT NOT NULL,
                    amount TEXT NOT NULL,
                    balance_after TEXT NOT NULL,
                    FOREIGN KEY (account_number)
                    REFERENCES accounts(account_number)
                )
                """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(customersTable);
            stmt.execute(accountsTable);
            stmt.execute(transactionsTable);

            System.out.println("Database initialized successfully.");

        } catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
        }
    }

    // SAVE CUSTOMER
    public static void saveCustomer(Customer c) {

        String sql = """
                INSERT OR REPLACE INTO customers(id, name, address)
                VALUES (?, ?, ?)
                """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, c.getCustomerId());
            pstmt.setString(2, c.getDisplayName());
            pstmt.setString(3, c.getAddress());

            pstmt.executeUpdate();

            System.out.println("Customer saved successfully.");

        } catch (SQLException e) {
            System.out.println("Error saving customer: " + e.getMessage());
        }
    }

    // SAVE ACCOUNT
    public static boolean saveAccount(Account account) {

        String sql = """
                INSERT INTO accounts
                (account_number, account_type, customer_id, balance)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, account.getAccountNumber());
            pstmt.setString(2, account.getAccountType());
            pstmt.setString(3, account.getOwner().getCustomerId());
            pstmt.setString(4, account.getBalance().toString());

            pstmt.executeUpdate();

            System.out.println("Account saved successfully.");
            return true;

        } catch (SQLException e) {

            System.out.println("Error saving account: " + e.getMessage());
            return false;
        }
    }

    // SAVE TRANSACTION
    public static void saveTransaction(Transaction transaction, Account account) {

        String sql = """
                INSERT OR REPLACE INTO transactions
                (transaction_id, account_number, transaction_date,
                 transaction_type, amount, balance_after)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, transaction.getTransactionId());
            pstmt.setString(2, account.getAccountNumber());
            pstmt.setString(3, transaction.getDate().toString());
            pstmt.setString(4, transaction.getType());
            pstmt.setString(5, transaction.getAmount().toString());
            pstmt.setString(6, transaction.getBalanceAfter().toString());

            pstmt.executeUpdate();

            System.out.println("Transaction saved: " + transaction.getTransactionId());

        } catch (SQLException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // SAVE ALL TRANSACTIONS
    public static void saveTransactions(Account account) {

        for (Transaction transaction : account.getTransactions()) {
            saveTransaction(transaction, account);
        }
    }

    // DISPLAY DATABASE RECORDS
    public static void displayDatabaseRecords() {

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            System.out.println("\n--- CUSTOMERS IN DATABASE ---");

            try (ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {
                while (rs.next()) {
                    System.out.println(
                            rs.getString("id") + " | "
                                    + rs.getString("name") + " | "
                                    + rs.getString("address")
                    );
                }
            }

            System.out.println("\n--- ACCOUNTS IN DATABASE ---");

            try (ResultSet rs = stmt.executeQuery("SELECT * FROM accounts")) {
                while (rs.next()) {
                    System.out.println(
                            rs.getString("account_number") + " | "
                                    + rs.getString("account_type") + " | "
                                    + rs.getString("customer_id") + " | BWP "
                                    + rs.getString("balance")
                    );
                }
            }

            System.out.println("\n--- TRANSACTIONS IN DATABASE ---");

            try (ResultSet rs = stmt.executeQuery("SELECT * FROM transactions")) {
                while (rs.next()) {
                    System.out.println(
                            rs.getString("transaction_id") + " | "
                                    + rs.getString("account_number") + " | "
                                    + rs.getString("transaction_type") + " | "
                                    + rs.getString("amount")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error displaying records: " + e.getMessage());
        }
    }

    // GET CUSTOMERS
    public static ObservableList<CustomerRow> getCustomers() {

        ObservableList<CustomerRow> customers =
                FXCollections.observableArrayList();

        String sql = "SELECT id, name, address FROM customers";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customers.add(new CustomerRow(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("address")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error loading customers: " + e.getMessage());
        }

        return customers;
    }

    // FIND CUSTOMER BY ID
    public static Customer findCustomerById(String customerId) {

        String sql = "SELECT id, name, address FROM customers WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customerId.trim());

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");

                    return new Customer("stored_customer", "",
                            id, address) {

                        @Override
                        public String getDisplayName() {
                            return name;
                        }
                    };
                }
            }

        } catch (SQLException e) {
            System.out.println("Error finding customer: " + e.getMessage());
        }

        return null;
    }

    // GET ACCOUNTS
    public static ObservableList<AccountRow> getAccounts() {

        ObservableList<AccountRow> accounts =
                FXCollections.observableArrayList();

        String sql = """
                SELECT account_number, account_type, customer_id, balance
                FROM accounts
                """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                accounts.add(new AccountRow(
                        rs.getString("account_number"),
                        rs.getString("account_type"),
                        rs.getString("customer_id"),
                        rs.getString("balance")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving accounts: " + e.getMessage());
        }

        return accounts;
    }

    // GET TRANSACTIONS
    public static ObservableList<TransactionRow> getTransactions() {

        ObservableList<TransactionRow> transactions =
                FXCollections.observableArrayList();

        String sql = """
                SELECT transaction_id, account_number, transaction_date,
                       transaction_type, amount, balance_after
                FROM transactions
                """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                transactions.add(new TransactionRow(
                        rs.getString("transaction_id"),
                        rs.getString("account_number"),
                        rs.getString("transaction_date"),
                        rs.getString("transaction_type"),
                        rs.getString("amount"),
                        rs.getString("balance_after")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving transactions: " + e.getMessage());
        }

        return transactions;
    }

    // UPDATE ACCOUNT BALANCE
    public static void updateAccountBalance(Account account) {

        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, account.getBalance().toString());
            pstmt.setString(2, account.getAccountNumber());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating account balance: " + e.getMessage());
        }
    }

    // PROCESS DEPOSIT / WITHDRAWAL
    public static String processTransaction(
            String accountNumber, String type, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return "Amount must be greater than zero.";
        }

        String selectSql =
                "SELECT balance FROM accounts WHERE account_number = ?";

        try (Connection conn = connect()) {

            conn.setAutoCommit(false);

            try (PreparedStatement select = conn.prepareStatement(selectSql)) {

                select.setString(1, accountNumber);

                try (ResultSet rs = select.executeQuery()) {

                    if (!rs.next()) {
                        conn.rollback();
                        return "Account not found.";
                    }

                    BigDecimal balance =
                            new BigDecimal(rs.getString("balance"));

                    BigDecimal newBalance;

                    if ("Deposit".equals(type)) {

                        newBalance = balance.add(amount);

                    } else if ("Withdraw".equals(type)) {

                        if (amount.compareTo(balance) > 0) {
                            conn.rollback();
                            return "Insufficient funds.";
                        }

                        newBalance = balance.subtract(amount);

                    } else {
                        conn.rollback();
                        return "Invalid transaction type.";
                    }

                    String updateSql = """
                            UPDATE accounts
                            SET balance = ?
                            WHERE account_number = ?
                            """;

                    try (PreparedStatement update =
                                 conn.prepareStatement(updateSql)) {

                        update.setString(1, newBalance.toString());
                        update.setString(2, accountNumber);
                        update.executeUpdate();
                    }

                    String insertSql = """
                            INSERT INTO transactions
                            (transaction_id, account_number, transaction_date,
                             transaction_type, amount, balance_after)
                            VALUES (?, ?, ?, ?, ?, ?)
                            """;

                    try (PreparedStatement insert =
                                 conn.prepareStatement(insertSql)) {

                        insert.setString(1,
                                "TXN-" + java.util.UUID.randomUUID());
                        insert.setString(2, accountNumber);
                        insert.setString(3,
                                java.time.LocalDateTime.now().toString());
                        insert.setString(4, type.toUpperCase());
                        insert.setString(5, amount.toString());
                        insert.setString(6, newBalance.toString());

                        insert.executeUpdate();
                    }

                    conn.commit();

                    return type + " successful! New balance: BWP "
                            + newBalance;
                }

            } catch (Exception e) {
                conn.rollback();
                throw e;
            }

        } catch (Exception e) {
            return "Transaction failed: " + e.getMessage();
        }
    }
}
