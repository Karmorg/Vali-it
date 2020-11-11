package ee.bcs.valiit.tasks.bank_controller;

import java.math.BigDecimal;

public class History {
    String accountNo;
    BigDecimal amount;

    public History(String accountNo, BigDecimal amount) {
        this.accountNo = accountNo;
        this.amount = amount;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
