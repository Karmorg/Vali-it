package ee.bcs.valiit.tasks.repository;

import ee.bcs.valiit.tasks.bank_controller.Account;
import ee.bcs.valiit.tasks.bank_controller.History;
import ee.bcs.valiit.tasks.bank_controller.HistoryRowMapper;
import ee.bcs.valiit.tasks.bank_controller.ObjectRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRpository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void creatAccount( String accountNo, String idCode){
        String sql = "INSERT INTO account (account_no, idcode, balance) " +
                "VALUES (:account_no, :idcode, :balance)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_no", accountNo);
        paramMap.put("client_id", idCode);
        paramMap.put("balance", BigDecimal.ZERO);
        jdbcTemplate.update(sql, paramMap);
    }
    public List<Account> getAccountsList (){
        String sql = "SELECT * FROM account ";
        List<Account> accountsList = jdbcTemplate.query(sql, new ObjectRowMapper());
        return  accountsList;
    }
    public BigDecimal getAccountBalance(String aNo) {

        String sql = "SELECT balance FROM account where account_no = :account_no";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("account_no", aNo);
        BigDecimal currentBalance = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        return currentBalance;
    }
    public void updateBalance(String aNo, BigDecimal amount) {

        String sql = "UPDATE account SET balance = :balance WHERE account_no = :account_no";
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("balance", amount);
        paramMap2.put("account_no", aNo);
        jdbcTemplate.update(sql, paramMap2);
    }
    public void addToHistory(String aNo, BigDecimal amount){

        String sql = "INSERT INTO account_history (account_no, amount) " +
                "VALUES (:account_no, :amount)";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("account_no", aNo);
        paramMap3.put("amount", amount);
        jdbcTemplate.update(sql, paramMap3);
    }

    public List<Account> getMyAccounts( String idCode) {

        String sql = "SELECT * FROM account WHERE idcode= :idcode";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("idcode", idCode);
        List<Account> accountsList = jdbcTemplate.query(sql, paramMap, new ObjectRowMapper());
        return accountsList;
    }
}
