package banking;

import java.math.BigDecimal;

public class ChequeAccount extends Account implements Withdrawable {

    private String employerName;
    private String employerAddress;

    public ChequeAccount(String accountNumber, String branch, Customer owner,
                         String employerName, String employerAddress) {

        super(accountNumber, branch, owner);
        this.employerName = employerName;
        this.employerAddress = employerAddress;
    }

    @Override
    public void withdraw(BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid withdrawal amount.");

        } else if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            recordTransaction("WITHDRAW", amount);
            System.out.println("Cheque withdrawal successful.");

        } else {
            System.out.println("Insufficient funds.");
        }
    }

    @Override
    public String getAccountType() {
        return "Cheque";
    }
}