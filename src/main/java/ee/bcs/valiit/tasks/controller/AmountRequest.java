package ee.bcs.valiit.tasks.controller;

import java.math.BigDecimal;

public class AmountRequest {
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
