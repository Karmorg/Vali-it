package ee.bcs.valiit.tasks.controller;

import ee.bcs.valiit.tasks.bank_controller.Account;
import ee.bcs.valiit.tasks.bank_controller.History;
import ee.bcs.valiit.tasks.bank_controller.Transaction;
import ee.bcs.valiit.tasks.repository2.AccountRepository2;
import ee.bcs.valiit.tasks.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class BankController {

    @Autowired
    private AccountService accountService;

    //vana (sisaldab isikukoodi)
    @PostMapping("clients/{fName}/{lName}/{idCode}")
    public void createClient(@PathVariable("fName") String fName,
                             @PathVariable("lName") String lName,
                             @PathVariable("idCode") String idCode) {
        accountService.createClient(fName, lName, idCode);
    }

    //uus (testimise aja kulu pärast välja jäetud isikukoodi sisestamine)
    @CrossOrigin
    @PostMapping("clients/{fName}/{lName}")
    public Integer createNewClient(@PathVariable("fName") String fName,
                                   @PathVariable("lName") String lName) {
        return accountService.createNewClient(fName, lName);
    }

    @CrossOrigin
    @PostMapping("accounts/{id}")
    public Integer createAccount(@PathVariable("id") Integer client_id) {
        return accountService.createAccount(client_id);
    }

    @CrossOrigin
    @GetMapping("accounts")
    public List<Account> getAccounts() {
        return accountService.getAccountsList();
    }

    @CrossOrigin
    @GetMapping("myAccounts/{client_id}")
    public List<Account> getMyAccounts(@PathVariable("client_id") Integer client_id) {
        return accountService.getMyAccounts(client_id);
    }

    @CrossOrigin
    @GetMapping("balance/{accountNo}")
    public BigDecimal getBalance(@PathVariable("accountNo") Integer accountNo) {
        return accountService.getBalance(accountNo);
    }

    @CrossOrigin
    @PutMapping("deposit/{accountNo}")
    public String deposit(@PathVariable("accountNo") Integer aNo,
                          @RequestBody BigDecimal amount) {
        return accountService.deposit(aNo, amount, "deposit");
    }

    @CrossOrigin
    @PutMapping("withdraw/{accountNo}")
    public String withdraw(@PathVariable("accountNo") Integer aNo,
                           @RequestParam BigDecimal amount) {
        return accountService.withdraw(aNo, amount, "withdraw");
    }

    @CrossOrigin
    @PutMapping("transfere/{accountNo}/{toAccount}")
    public String transfere(@PathVariable("accountNo") Integer aNo,
                            @PathVariable("toAccount") Integer toNo,
                            @RequestBody BigDecimal amount) {
        return accountService.transfere(aNo, toNo, amount);
    }

    @GetMapping("history/{accountNo}")
    public List<History> getHistory(@PathVariable("accountNo") Integer accountNo) {

        return accountService.getHistory(accountNo);
    }

    @CrossOrigin
    @GetMapping("transactions/{accountNo}")
    public List<Transaction> getTransactions(@PathVariable("accountNo") Integer accountNo) {

        return accountService.transactionsHistory(accountNo);
    }

    //Siin on Hibernate osa
    @Autowired
    private AccountRepository2 accountRepository2;

    @GetMapping("hibernate_test")
    public List<ee.bcs.valiit.tasks.repository2.Account> getAllAccounts(){
        return accountRepository2.findAll();
    }

    @GetMapping("hibernate_test/{accountNo}")
    public Object getOneAccount(@PathVariable ("accountNo") Long accountNo){
        return accountRepository2.getOne(accountNo);
    }

    @PostMapping("hibernate_test_save_account/{clientNo}")
    private void saveAccount (@PathVariable ("clientNo") Long clientNo){
        ee.bcs.valiit.tasks.repository2.Account account = new ee.bcs.valiit.tasks.repository2.Account();
        account.setClientId(clientNo);
        account.setBalance(BigDecimal.ZERO);
        accountRepository2.save(account);
    }

    //Siin lõppeb Hibernate osa

}

