package ee.bcs.valiit.tasks.repository;

import ee.bcs.valiit.tasks.bank_controller.History;
import ee.bcs.valiit.tasks.bank_controller.HistoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HistoryRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<History> getHistory( String accountNo) {

        String sql = "SELECT * FROM account_manager_schema.account_history WHERE account_no= :account_no";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_no", accountNo);
        List<History> historyList = jdbcTemplate.query(sql, paramMap, new HistoryRowMapper());

        return historyList;
    }
}
