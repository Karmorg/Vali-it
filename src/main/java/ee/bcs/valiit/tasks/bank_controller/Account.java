package ee.bcs.valiit.tasks.bank_controller;

import java.math.BigDecimal;

public class Account {
    private Integer accountNo;
    private Integer clientId;
    private BigDecimal balance = new BigDecimal("0");

    public Account(Integer accountNo, Integer clientId, BigDecimal balance) {
        this.accountNo = accountNo;
        this.clientId = clientId;
        this.balance = balance;
    }

    public Integer getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Integer accountNo) {
        this.accountNo = accountNo;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
