package banking.view;

public class TransactionRow {

    private String transactionId;
    private String accountNumber;
    private String date;
    private String type;
    private String amount;
    private String balance;

    public TransactionRow(String transactionId, String accountNumber,
                          String date, String type, String amount,
                          String balance) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    public String getTransactionId() { return transactionId; }
    public String getAccountNumber() { return accountNumber; }
    public String getDate() { return date; }
    public String getType() { return type; }
    public String getAmount() { return amount; }
    public String getBalance() { return balance; }
}