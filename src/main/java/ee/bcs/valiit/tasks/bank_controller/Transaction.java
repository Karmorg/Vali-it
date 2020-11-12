package ee.bcs.valiit.tasks.bank_controller;

import java.math.BigDecimal;

public class Transaction {
    int transactionID;
    int accountNo;
    String transactionType;
    BigDecimal amount;

    public Transaction(int transactionID, int accountNo, String transactionType, BigDecimal amount) {
        this.transactionID = transactionID;
        this.accountNo = accountNo;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
