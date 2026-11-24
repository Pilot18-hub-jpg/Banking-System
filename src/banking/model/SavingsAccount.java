
package banking.model;

import java.math.BigDecimal;

public class SavingsAccount extends Account implements InterestBearing {

    private BigDecimal interestRate = new BigDecimal("0.0005");

    public SavingsAccount(String accountNumber, String branch, Customer owner) {
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
            System.out.println("Savings interest paid: " + interest);
        }
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}
