package ee.bcs.valiit.tasks.service;

import ee.bcs.valiit.tasks.bank_controller.Account;
import ee.bcs.valiit.tasks.bank_controller.History;
import ee.bcs.valiit.tasks.bank_controller.HistoryRowMapper;
import ee.bcs.valiit.tasks.bank_controller.ObjectRowMapper;
import ee.bcs.valiit.tasks.repository.AccountRpository;
import ee.bcs.valiit.tasks.repository.ClientRepository;
import ee.bcs.valiit.tasks.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private AccountRpository accountRpository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private HistoryRepository historyRepository;

    //vana
    public void createClient(String fName, String lName, String idCode) {
        clientRepository.createClient(fName, lName, idCode);
    }
    //uus
    public int createNewClient(String fName, String lName) {
        clientRepository.addClient(fName, lName);
        return null;
    }

    public void creatAccount(String accountNo, String idCode) {
        accountRpository.creatAccount(accountNo, idCode);
    }

    public List<Account> getAccountsList() {
        return accountRpository.getAccountsList();
    }

    public void deposit(String aNo, BigDecimal amount) {
        amount = amount.abs();
        accountRpository.addToHistory(aNo, amount);
        amount = amount.add(accountRpository.getAccountBalance(aNo));
        accountRpository.updateBalance(aNo, amount);
    }

    public String withdraw(String aNo, BigDecimal amount) {
        amount = amount.abs();
        if (accountRpository.getAccountBalance(aNo).compareTo(amount) > 0) {
            BigDecimal minus = new BigDecimal("-1");
            minus = amount.multiply(minus);
            accountRpository.addToHistory(aNo, minus);

            amount = accountRpository.getAccountBalance(aNo).subtract(amount);
            accountRpository.updateBalance(aNo, amount);

            return "Raha võetud.";
        } else {
            return "Pole piisavalt vahendeid.";
        }
    }

    public String transfere(String aNo, String toNo, BigDecimal amount) {
        amount = amount.abs();
        if (withdraw(aNo,amount).equals("Raha võetud.")){
            deposit(toNo, amount);
            //withdraw(aNo, amount);
            return "Ülekanne sooritatud.";
        } else {
            return "Pole piisavalt vahendeid.";
        }
    }

    public BigDecimal getBalance(String accountNo) {
        return accountRpository.getAccountBalance(accountNo);
    }

    public List<Account> getMyAccounts(String idCode) {
        return accountRpository.getMyAccounts(idCode);
    }

    public List<History> getHistory( String accountNo) {
        return historyRepository.getHistory(accountNo);
    }
}
