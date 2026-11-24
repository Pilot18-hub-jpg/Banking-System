package banking.view;

public class AccountRow {

    private String accountNumber;
    private String accountType;
    private String customerId;
    private String balance;

    public AccountRow(String accountNumber, String accountType,
                      String customerId, String balance) {

        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.customerId = customerId;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getBalance() {
        return balance;
    }
}