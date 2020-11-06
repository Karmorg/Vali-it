package ee.bcs.valiit.tasks.bank_controller;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BankController {
    // createAccountNr (accountNr)
    //depositMoney (accountNr, money)
    //withdrawMoney (accountNr, money)
    //trasfereMoney (fromAccount, toAccount, money)
    //getAccountBalance (accountNr)
    //Raskem
    //createClient(firstName LastName, ...)
    //muuta createAccount (clientId, accountNr) - ühel kliendi saab olla mitu kontot
    //getBalanceHistory (accountNr)

    private List<Client> clients = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();

    @PostMapping("clients/{fName}/{lName}/{idCode}")
    public void createClient(@PathVariable("fName") String fName,
                             @PathVariable("lName") String lName,
                             @PathVariable("idCode") String idCode) {
        Client newClient = new Client(fName, lName, idCode);
        clients.add(newClient);
    }

    @PostMapping("accounts/{accountNo}/{idCode}")
    public void createAccount(@PathVariable ("accountNo") String accountNo,
                              @PathVariable ("idCode") String idCode) {
        Account newAccount = new Account(accountNo);
        for (Client c : clients) {
            if (c.getIdCode() .equals(idCode)) {
                c.getClientsAccounts().add(accountNo);
            }
        }
        accounts.add(newAccount);
    }

    @GetMapping ("clients")
    public List<Client> getClients(){
        return clients;
    }

    @GetMapping ("accounts")
    public List<Account> getAccounts(){
        return accounts;
    }

    @PutMapping ("deposit/{accountNo}")
    public void deposit (@PathVariable ("accountNo") String aNo,
                         @RequestBody BigDecimal amount){
        for (Account a : accounts){
            if (aNo.equals(a.getAccountNo())){
                a.setBalance(a.getBalance().add(amount.abs()));
                a.getHistory().add(amount.abs().toString());
            }
        }
    }

    @PutMapping ("withdraw/{accountNo}")
    public String withdraw (@PathVariable ("accountNo") String aNo,
                         @RequestParam BigDecimal amount){
        for (Account a : accounts){
            if (aNo.equals(a.getAccountNo())){
                if (a.getBalance().compareTo(amount) > 0){
                    a.setBalance(a.getBalance().subtract(amount.abs()));
                    a.getHistory().add("-" + amount.abs().toString());
                }else {
                    return "Pole piisavalt vahendeid";
                }
            }
        }
        return "Raha võetud";
    }

    @PutMapping ("transfere/{accountNo}/{toAccount}")
    public String transfere (@PathVariable ("accountNo") String aNo,
                             @PathVariable ("toAccount") String toNo,
                            @RequestBody BigDecimal amount){
        for (Account a : accounts){
            if (aNo.equals(a.getAccountNo())){
                if (a.getBalance().compareTo(amount) > 0){
                    a.setBalance(a.getBalance().subtract(amount.abs()));
                    a.getHistory().add(amount.abs().toString());
                    deposit(toNo,amount.abs());
                }else {
                    return "Pole piisavalt vahendeid";
                }
            }
        }
        return "Raha üle kantud";
    }

    @GetMapping ("balance/{accountNo}")
    public BigDecimal getBalance(@PathVariable ("accountNo") String accountNo){
        BigDecimal balance = new BigDecimal(0);
        for (Account a : accounts){
            if (accountNo.equals(a.getAccountNo())){
                balance = a.getBalance();
            }
        }
        return balance;
    }

    @GetMapping ("myAccounts/{idCode}")
    public List<String> getMyAccounts(@PathVariable ("idCode") String idCode){
        List<String> myAccounts= new ArrayList<>();
        for (Client c : clients ){
            if (idCode.equals(c.getIdCode())){
                myAccounts = c.getClientsAccounts();
            }
        }
        return myAccounts;
    }

    @GetMapping ("history/{accountNo}")
    public List<String> getHistory(@PathVariable ("accountNo") String accountNo){
        List<String> history = new ArrayList<>();
        for (Account a : accounts){
            if (accountNo.equals(a.getAccountNo())){
                history = a.getHistory();
            }
        }
        return history;
    }

}

