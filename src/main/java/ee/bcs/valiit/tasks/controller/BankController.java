package ee.bcs.valiit.tasks.controller;

import ee.bcs.valiit.tasks.bank_controller.*;
import ee.bcs.valiit.tasks.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BankController {

    @Autowired
    private AccountService accountService;

    //vana
    @PostMapping("clients/{fName}/{lName}/{idCode}")
    public void createClient(@PathVariable("fName") String fName,
                             @PathVariable("lName") String lName,
                             @PathVariable("idCode") String idCode) {
        accountService.createClient(fName,lName,idCode);
    }
    //uus
    @PostMapping("clients/{fName}/{lName}")
    public Integer createNewClient(@PathVariable("fName") String fName,
                             @PathVariable("lName") String lName) {
        accountService.createNewClient(fName,lName);
    }

    @PostMapping("accounts/{accountNo}/{idCode}")
    public void createAccount(@PathVariable("accountNo") String accountNo,
                              @PathVariable("idCode") String idCode) {
        accountService.creatAccount(accountNo,idCode);
    }

    @GetMapping("accounts")
    public List<Account> getAccounts() {
        return accountService.getAccountsList();
    }

    @PutMapping("deposit/{accountNo}")
    public void deposit(@PathVariable("accountNo") String aNo,
                        @RequestBody BigDecimal amount) {
    accountService.deposit(aNo, amount);
    }

    @PutMapping("withdraw/{accountNo}")
    public String withdraw(@PathVariable("accountNo") String aNo,
                           @RequestParam BigDecimal amount) {
        return accountService.withdraw(aNo, amount);
    }

    @PutMapping("transfere/{accountNo}/{toAccount}")
    public String transfere(@PathVariable("accountNo") String aNo,
                            @PathVariable("toAccount") String toNo,
                            @RequestBody BigDecimal amount) {
        return accountService.transfere(aNo,toNo,amount);
    }

    @GetMapping("balance/{accountNo}")
    public BigDecimal getBalance(@PathVariable("accountNo") String accountNo) {
        return accountService.getBalance(accountNo);
    }

    @GetMapping("myAccounts/{idCode}")
    public List<Account> getMyAccounts(@PathVariable("idCode") String idCode) {
        return accountService.getMyAccounts(idCode);

    }

    @GetMapping("history/{accountNo}")
    public List<History> getHistory(@PathVariable("accountNo") String accountNo) {

        return accountService.getHistory(accountNo);
    }

}

