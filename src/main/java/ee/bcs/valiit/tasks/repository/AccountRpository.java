package ee.bcs.valiit.tasks.repository;

import ee.bcs.valiit.tasks.bank_controller.Account;
import ee.bcs.valiit.tasks.bank_controller.History;
import ee.bcs.valiit.tasks.bank_controller.HistoryRowMapper;
import ee.bcs.valiit.tasks.bank_controller.ObjectRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

    public Integer createAccount( Integer client_id){
        String sql = "INSERT INTO account (client_id, balance) " +
                "VALUES (:client_id, :balance)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("client_id", client_id);
        paramMap.put("balance", BigDecimal.ZERO);
        //jdbcTemplate.update(sql, paramMap);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        jdbcTemplate.update(sql, paramSource, keyHolder);
        Long id = (Long) keyHolder.getKeys().get("account_no");
        return Math.toIntExact(id);
    }

    public List<Account> getAccountsList (){
        String sql = "SELECT * FROM account ";
        List<Account> accountsList = jdbcTemplate.query(sql, new ObjectRowMapper());
        return  accountsList;
    }

    public List<Account> getMyAccounts( Integer client_id) {

        String sql = "SELECT * FROM account WHERE client_id= :client_id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("client_id", client_id);
        List<Account> accountsList = jdbcTemplate.query(sql, paramMap, new ObjectRowMapper());
        return accountsList;
    }

    public BigDecimal getAccountBalance(Integer aNo) {

        String sql = "SELECT balance FROM account where account_no = :account_no";
        Map<String, Integer> paramMap = new HashMap<>();
        paramMap.put("account_no", aNo);
        BigDecimal currentBalance = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        return currentBalance;
    }

    public void updateBalance(Integer aNo, BigDecimal amount) {

        String sql = "UPDATE account SET balance = :balance WHERE account_no = :account_no";
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("balance", amount);
        paramMap2.put("account_no", aNo);
        jdbcTemplate.update(sql, paramMap2);
    }
    public void addToHistory(Integer aNo, BigDecimal amount){

        String sql = "INSERT INTO account_history (account_no, amount) " +
                "VALUES (:account_no, :amount)";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("account_no", aNo);
        paramMap3.put("amount", amount);
        jdbcTemplate.update(sql, paramMap3);
    }

}