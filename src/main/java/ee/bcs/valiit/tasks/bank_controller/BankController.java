package ee.bcs.valiit.tasks.bank_controller;

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
    // createAccountNr (accountNr)
    //depositMoney (accountNr, money)
    //withdrawMoney (accountNr, money)
    //trasfereMoney (fromAccount, toAccount, money)
    //getAccountBalance (accountNr)
    //Raskem
    //createClient(firstName LastName, ...)
    //muuta createAccount (clientId, accountNr) - ühel kliendi saab olla mitu kontot
    //getBalanceHistory (accountNr)

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private List<Client> clients = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();

    @PostMapping("clients/{fName}/{lName}/{idCode}")
    public void createClient(@PathVariable("fName") String fName,
                             @PathVariable("lName") String lName,
                             @PathVariable("idCode") String idCode) {

        String sql = "INSERT INTO account_manager_schema.client (client_id, f_name, l_name) " +
                "VALUES (:clientid, :fname, :lname)";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("clientid", idCode);
        paramMap.put("fname", fName);
        paramMap.put("lname", lName);
        namedParameterJdbcTemplate.update(sql, paramMap);

        Client newClient = new Client(fName, lName, idCode);
        clients.add(newClient);
    }

    @PostMapping("accounts/{accountNo}/{idCode}")
    public void createAccount(@PathVariable("accountNo") String accountNo,
                              @PathVariable("idCode") String idCode) {
        String sql = "INSERT INTO account_manager_schema.account (accountno, idcode) VALUES (:accountno, :idcode)";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accountno", accountNo);
        paramMap.put("idcode", idCode);
        namedParameterJdbcTemplate.update(sql, paramMap);

        Account newAccount = new Account(accountNo);
        for (Client c : clients) {
            if (c.getIdCode().equals(idCode)) {
                c.getClientsAccounts().add(accountNo);
            }
        }
        accounts.add(newAccount);
    }

    @GetMapping("clients")
    public List<Client> getClients() {

        return clients;
    }

    @GetMapping("accounts")
    public List<Account> getAccounts() {

        String sql = "SELECT * FROM account_manager_schema.account ";

        List<Account> accountsList = namedParameterJdbcTemplate.query(sql, new ObjectRowMapper());
        return  accountsList;

        //return accounts;
    }

    @PutMapping("deposit/{accountNo}")
    public void deposit(@PathVariable("accountNo") String aNo,
                        @RequestBody BigDecimal amount) {
        amount=amount.abs();

        //toimib ainult siis, kui on lahtris üks väärtus
        //loeb sisse kontoseisu
        String sql = "SELECT balance FROM account_manager_schema.account where accountno = :accountno";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accountno", aNo);
        BigDecimal currentBalance = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        currentBalance = currentBalance.add(amount.abs());

        //uuendab konto seisu
        sql = "UPDATE account_manager_schema.account SET balance = :balance WHERE accountno = :accountno";
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("balance", currentBalance);
        paramMap2.put("accountno", aNo);
        namedParameterJdbcTemplate.update(sql, paramMap2);

        //kirjutab kande ajalukku
        sql = "INSERT INTO account_manager_schema.account_history (account_no, amount) " +
                "VALUES (:accountno, :amount)";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("accountno", aNo);
        paramMap3.put("amount", amount);
        namedParameterJdbcTemplate.update(sql, paramMap3);


        for (Account a : accounts) {
            if (aNo.equals(a.getAccountNo())) {
                a.setBalance(a.getBalance().add(amount.abs()));
                a.getHistory().add(amount.abs().toString());
            }
        }
    }

    @PutMapping("withdraw/{accountNo}")
    public String withdraw(@PathVariable("accountNo") String aNo,
                           @RequestParam BigDecimal amount) {
        amount=amount.abs();

        //võtab konto seisu
        String sql = "SELECT balance FROM account_manager_schema.account where accountno = :accountno";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accountno", aNo);
        BigDecimal currentBalance = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        currentBalance = currentBalance.subtract(amount.abs());

        //kui on piisavalt vahendeid, võtab raha maha
        if (currentBalance.compareTo(BigDecimal.ZERO)>0){

            //võtab raha maha
            sql = "UPDATE account_manager_schema.account SET balance = :balance WHERE accountno = :accountno";
            Map<String, Object> paramMap2 = new HashMap<>();
            paramMap2.put("balance", currentBalance);
            paramMap2.put("accountno", aNo);
            namedParameterJdbcTemplate.update(sql, paramMap2);

            //teeb kande ajalukku
            sql = "INSERT INTO account_manager_schema.account_history (account_no, amount) " +
                    "VALUES (:accountno, :amount)";
            BigDecimal minus = new BigDecimal("-1");
            minus=amount.multiply(minus);
            Map<String, Object> paramMap3 = new HashMap<>();
            paramMap3.put("accountno", aNo);
            paramMap3.put("amount", minus);
            namedParameterJdbcTemplate.update(sql, paramMap3);
        }else {
            return "Pole piisavalt vahendeid";
        }

        for (Account a : accounts) {
            if (aNo.equals(a.getAccountNo())) {
                if (a.getBalance().compareTo(amount) > 0) {
                    a.setBalance(a.getBalance().subtract(amount.abs()));
                    a.getHistory().add("-" + amount.abs().toString());
                } else {
                    return "Pole piisavalt vahendeid";
                }
            }
        }
        return "Raha võetud";
    }

    @PutMapping("transfere/{accountNo}/{toAccount}")
    public String transfere(@PathVariable("accountNo") String aNo,
                            @PathVariable("toAccount") String toNo,
                            @RequestBody BigDecimal amount) {
        amount=amount.abs();

        //kontrollib, kas lähte kontol on piisavalt raha
        String sql = "SELECT balance FROM account_manager_schema.account where accountno = :accountno";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accountno", aNo);
        BigDecimal currentBalance = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        currentBalance = currentBalance.subtract(amount.abs());

        //kui on piisavalt raha
        if (currentBalance.compareTo(BigDecimal.ZERO)>0){

            //Võtab raha ära
            sql = "UPDATE account_manager_schema.account SET balance = :balance WHERE accountno = :accountno";
            Map<String, Object> paramMap2 = new HashMap<>();
            paramMap2.put("balance", currentBalance);
            paramMap2.put("accountno", aNo);
            namedParameterJdbcTemplate.update(sql, paramMap2);

            //teeb kande ajalukku
            sql = "INSERT INTO account_manager_schema.account_history (account_no, amount) " +
                    "VALUES (:accountno, :amount)";
            BigDecimal minus = new BigDecimal("-1");
            minus=amount.multiply(minus);
            Map<String, Object> paramMap5 = new HashMap<>();
            paramMap5.put("accountno", aNo);
            paramMap5.put("amount", minus);
            namedParameterJdbcTemplate.update(sql, paramMap5);

            //paneb raha juurde

            //võtab konto seiseu
            sql = "SELECT balance FROM account_manager_schema.account where accountno = :accountno";
            Map<String, String> paramMap3 = new HashMap<>();
            paramMap3.put("accountno", toNo);
            BigDecimal currentBalance2 = namedParameterJdbcTemplate.queryForObject(sql, paramMap3, BigDecimal.class);
            currentBalance2 = currentBalance2.add(amount.abs());

            //paneb raha juurde
            sql = "UPDATE account_manager_schema.account SET balance = :balance WHERE accountno = :accountno";
            Map<String, Object> paramMap4 = new HashMap<>();
            paramMap4.put("balance", currentBalance2);
            paramMap4.put("accountno", toNo);
            namedParameterJdbcTemplate.update(sql, paramMap4);

            //kirjutab kande ajalukku
            sql = "INSERT INTO account_manager_schema.account_history (account_no, amount) " +
                    "VALUES (:accountno, :amount)";
            Map<String, Object> paramMap6 = new HashMap<>();
            paramMap6.put("accountno", toNo);
            paramMap6.put("amount", amount);
            namedParameterJdbcTemplate.update(sql, paramMap6);

        }else {
            return "Pole piisavalt vahendeid";
        }

        for (Account a : accounts) {
            if (aNo.equals(a.getAccountNo())) {
                if (a.getBalance().compareTo(amount) > 0) {
                    a.setBalance(a.getBalance().subtract(amount.abs()));
                    a.getHistory().add(amount.abs().toString());
                    deposit(toNo, amount.abs());
                } else {
                    return "Pole piisavalt vahendeid";
                }
            }
        }
        return "Raha üle kantud";
    }

    @GetMapping("balance/{accountNo}")
    public BigDecimal getBalance(@PathVariable("accountNo") String accountNo) {

        String sql = "SELECT balance FROM account_manager_schema.account where accountno = :accountno";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accountno", accountNo);
        BigDecimal currentBalance = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);

        return currentBalance;

//        BigDecimal balance = new BigDecimal(0);
//        for (Account a : accounts) {
//            if (accountNo.equals(a.getAccountNo())) {
//                balance = a.getBalance();
//            }
//        }
//        return balance;
    }

    @GetMapping("myAccounts/{idCode}")
    public List<Account> getMyAccounts(@PathVariable("idCode") String idCode) {

        String sql = "SELECT * FROM account_manager_schema.account WHERE idcode= :idcode";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("idcode", idCode);
        List<Account> accountsList = namedParameterJdbcTemplate.query(sql, paramMap, new ObjectRowMapper());

        return accountsList;

//        List<String> myAccounts = new ArrayList<>();
//        for (Client c : clients) {
//            if (idCode.equals(c.getIdCode())) {
//                myAccounts = c.getClientsAccounts();
//            }
//        }
//        return myAccounts;
    }

    @GetMapping("history/{accountNo}")
    public List<String> getHistory(@PathVariable("accountNo") String accountNo) {
        List<String> history = new ArrayList<>();
        for (Account a : accounts) {
            if (accountNo.equals(a.getAccountNo())) {
                history = a.getHistory();
            }
        }
        return history;
    }

}

