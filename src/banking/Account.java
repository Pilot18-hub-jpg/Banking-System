
package banking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    // Get account balance
    public BigDecimal getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public abstract String getAccountType();
    public List<Transaction> getTransactions() {
    return new ArrayList<>(transactions);
    }

    // Record transactions
    protected void recordTransaction(String type, BigDecimal amount) {

        Transaction t = new Transaction(
                "TXN-" + java.util.UUID.randomUUID(),
                type,
                amount,
                balance
        );

        transactions.add(t);

        System.out.println(type + ": " + amount
                + " | New Balance: " + balance);
    }
}
