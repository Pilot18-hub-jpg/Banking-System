package banking;

import java.math.BigDecimal;

public class InvestmentAccount extends Account
        implements InterestBearing, Withdrawable {

    private BigDecimal interestRate = new BigDecimal("0.05");
    private final BigDecimal MIN_OPENING = new BigDecimal("500.00");

    public InvestmentAccount(String accountNumber, String branch, Customer owner) {
        super(accountNumber, branch, owner);
    }

    @Override
    public BigDecimal calculateInterest() {
        return balance.multiply(interestRate);
    }

    @Override
    public void payInterest() {
        BigDecimal interest = calculateInterest();

        if (interest.compareTo(BigDecimal.ZERO) > 0) {
            deposit(interest);
            System.out.println("Investment interest paid: " + interest);
        }
    }

    @Override
    public void withdraw(BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid withdrawal amount.");

        } else if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            recordTransaction("WITHDRAW", amount);
            System.out.println("Investment withdrawal successful: " + amount);

        } else {
            System.out.println("Insufficient funds.");
        }
    }

    @Override
    public String getAccountType() {
        return "Investment";
    }
}