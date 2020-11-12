package ee.bcs.valiit.tasks.repository;

import ee.bcs.valiit.tasks.bank_controller.History;
import ee.bcs.valiit.tasks.bank_controller.HistoryRowMapper;
import ee.bcs.valiit.tasks.bank_controller.Transaction;
import ee.bcs.valiit.tasks.bank_controller.TransactionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TransactionHistoryRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void addTransaction(String tType, Integer account_no, BigDecimal amount) {
        String sql = "INSERT INTO transaction_history (transaction_type, account_no, amount) " +
                "VALUES (:transaction_type, :account_no, :amount)";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("transaction_type", tType);
        paramMap3.put("account_no", account_no);
        paramMap3.put("amount", amount);
        jdbcTemplate.update(sql, paramMap3);
    }

    public List<Transaction> getTransactionsHistory(Integer accountNo) {

        String sql = "SELECT * FROM transaction_history WHERE account_no= :account_no";
        Map<String, Integer> paramMap = new HashMap<>();
        paramMap.put("account_no", accountNo);
        List<Transaction> transactionsList = jdbcTemplate.query(sql, paramMap, new TransactionRowMapper());

        return transactionsList;
    }
}
