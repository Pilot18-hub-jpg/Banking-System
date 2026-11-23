
package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:banking_system.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

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

    public static void saveAccount(Account account) {

        String sql = """
                INSERT OR REPLACE INTO accounts
                (account_number, account_type, customer_id, balance)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, account.getAccountNumber());
            pstmt.setString(2, account.getAccountType());
            pstmt.setString(3, account.owner.getCustomerId());
            pstmt.setString(4, account.getBalance().toString());

            pstmt.executeUpdate();

            System.out.println("Account saved: " + account.getAccountNumber());

        } catch (SQLException e) {
            System.out.println("Error saving account: " + e.getMessage());
        }
    }

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

public static void saveTransactions(Account account) {

    for (Transaction transaction : account.getTransactions()) {
        saveTransaction(transaction, account);
    }
}


public static void displayDatabaseRecords() {

    try (Connection conn = connect();
         Statement stmt = conn.createStatement()) {

        // Display customers
        System.out.println("\n--- CUSTOMERS IN DATABASE ---");

        try (var rs = stmt.executeQuery("SELECT * FROM customers")) {
            while (rs.next()) {
                System.out.println(
                        rs.getString("id") + " | "
                        + rs.getString("name") + " | "
                        + rs.getString("address")
                );
            }
        }

        // Display accounts
        System.out.println("\n--- ACCOUNTS IN DATABASE ---");

        try (var rs = stmt.executeQuery("SELECT * FROM accounts")) {
            while (rs.next()) {
                System.out.println(
                        rs.getString("account_number") + " | "
                        + rs.getString("account_type") + " | "
                        + rs.getString("customer_id") + " | BWP "
                        + rs.getString("balance")
                );
            }
        }

        // Display transactions
        System.out.println("\n--- TRANSACTIONS IN DATABASE ---");

        try (var rs = stmt.executeQuery("SELECT * FROM transactions")) {
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

}
