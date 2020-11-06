package ee.bcs.valiit.tasks.bank_controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNo;
    private BigDecimal balance = new BigDecimal("0");
    private List<String> history =new ArrayList<>();

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    public Account(String accountNo) {
        this.accountNo = accountNo;
        //this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
