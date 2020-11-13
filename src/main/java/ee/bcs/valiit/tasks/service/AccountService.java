package ee.bcs.valiit.tasks.service;

import ee.bcs.valiit.tasks.bank_controller.*;
import ee.bcs.valiit.tasks.exeption.ApplicationException;
import ee.bcs.valiit.tasks.repository.AccountRpository;
import ee.bcs.valiit.tasks.repository.ClientRepository;
import ee.bcs.valiit.tasks.repository.HistoryRepository;
import ee.bcs.valiit.tasks.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRpository accountRpository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    //vana
    public void createClient(String fName, String lName, String idCode) {
        clientRepository.createClient(fName, lName, idCode);
    }
    //uus
    public Integer createNewClient(String fName, String lName) {
        return clientRepository.addClient(fName, lName);
    }

    public Integer createAccount(Integer client_id) {
        return accountRpository.createAccount(client_id);
    }

    public List<Account> getAccountsList() {
        return accountRpository.getAccountsList();
    }

    public List<Account> getMyAccounts(Integer client_id) {
        return accountRpository.getMyAccounts(client_id);
    }

    public BigDecimal getBalance(Integer accountNo) {
        return accountRpository.getAccountBalance(accountNo);
    }

    public String deposit(Integer aNo, BigDecimal amount, String tType) {
        amount = amount.abs();
        //accountRpository.addToHistory(aNo, amount);
        BigDecimal newBalance;

        newBalance = amount.add(accountRpository.getAccountBalance(aNo));
        accountRpository.updateBalance(aNo, newBalance);

        if (tType.equals("deposit")){
            transactionHistoryRepository.addTransaction("deposit", aNo, amount);
        }else {
            transactionHistoryRepository.addTransaction("transfere", aNo, amount);
        }

        return "Raha arvele kantud";

    }

    public String withdraw(Integer aNo, BigDecimal amount, String tType) {
        amount = amount.abs();
        if (accountRpository.getAccountBalance(aNo).compareTo(amount) > 0) {
            BigDecimal minus = new BigDecimal("-1");
            minus = amount.multiply(minus);
            //accountRpository.addToHistory(aNo, minus);

            if (tType.equals("withdraw")){
                transactionHistoryRepository.addTransaction("withdraw", aNo, minus);
            }else {
                transactionHistoryRepository.addTransaction("transfere", aNo, minus);
            }

            amount = accountRpository.getAccountBalance(aNo).subtract(amount);
            accountRpository.updateBalance(aNo, amount);

            return "Raha võetud.";
        } else {
            throw new ApplicationException("Pole piisavalt vahendeid.");
        }
    }

    public String transfere(Integer aNo, Integer toNo, BigDecimal amount) {
        amount = amount.abs();
        if (withdraw(aNo,amount, "transfere").equals("Raha võetud.")){
            deposit(toNo, amount, "transfere");
            //withdraw(aNo, amount);
            return "Ülekanne sooritatud.";
        } else {
            return "Pole piisavalt vahendeid.";
        }
    }

    public List<History> getHistory( Integer accountNo) {
        return historyRepository.getHistory(accountNo);
    }

    public List<Transaction> transactionsHistory(Integer accountNo) {
        return transactionHistoryRepository.getTransactionsHistory(accountNo);
    }
}
