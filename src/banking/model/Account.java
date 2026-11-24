
package banking.model;

import banking.dao.DatabaseManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Account {

    protected String accountNumber;
    protected BigDecimal balance;
    protected String branch;
    protected Customer owner;
    protected List<Transaction> transactions;

    public Account(String accountNumber, String branch, Customer owner) {

        if (owner == null) {
            throw new IllegalArgumentException("An account must have an owner.");
        }

        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
        this.branch = branch;
        this.owner = owner;
        this.transactions = new ArrayList<>();
    }

    // Deposit money
    public void deposit(BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }

        balance = balance.add(amount);
        recordTransaction("DEPOSIT", amount);
    }

    // Withdraw money
    public void withdraw(BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }

        if (amount.compareTo(balance) > 0) {
            System.out.println("Insufficient funds.");
            return;
        }

        balance = balance.subtract(amount);
        recordTransaction("WITHDRAWAL", amount);
    }

    // Get account balance
    public BigDecimal getBalance() {
        return balance;
    }

    // Get account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Get account owner
    public Customer getOwner() {
        return owner;
    }

    // Get branch
    public String getBranch() {
        return branch;
    }

    // Get account type
    public abstract String getAccountType();

    // Get transactions
    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    // Record transactions
    protected void recordTransaction(String type, BigDecimal amount) {

        Transaction t = new Transaction(
                "TXN-" + UUID.randomUUID(),
                type,
                amount,
                balance
        );

        transactions.add(t);

        DatabaseManager.saveTransaction(t, this);
        DatabaseManager.updateAccountBalance(this);

        System.out.println(type + ": " + amount
                + " | New Balance: " + balance);
    }
}
