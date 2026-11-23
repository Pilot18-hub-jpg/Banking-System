package banking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private String transactionId;
    private LocalDateTime date;
    private String type;
    private BigDecimal amount;
    private BigDecimal balanceAfter;

    public Transaction(String transactionId, String type,
                       BigDecimal amount, BigDecimal balanceAfter) {

        this.transactionId = transactionId;
        this.date = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }
}