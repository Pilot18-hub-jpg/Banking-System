package banking;

import java.math.BigDecimal;

public interface Withdrawable {
    void withdraw(BigDecimal amount);
}