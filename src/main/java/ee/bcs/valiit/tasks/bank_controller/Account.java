package ee.bcs.valiit.tasks.bank_controller;

import java.math.BigDecimal;

public class Account {
    private String accountNo;
    private String idCode;
    private BigDecimal balance = new BigDecimal("0");
//    private List<String> history =new ArrayList<>();
//
//    public List<String> getHistory() {
//        return history;
//    }
//
//    public void setHistory(List<String> history) {
//        this.history = history;
//    }


    public Account(String accountNo, String idCode) {
        this.accountNo = accountNo;
        this.idCode = idCode;
        this.balance = BigDecimal.ZERO;
    }

    public Account(String accountNo, BigDecimal balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
