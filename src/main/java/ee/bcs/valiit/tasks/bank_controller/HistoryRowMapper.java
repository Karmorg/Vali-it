package ee.bcs.valiit.tasks.bank_controller;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        History history = new History(resultSet.getString("account_no"),
                resultSet.getBigDecimal("amount"));
        //account.setBalance(resultSet.getBigDecimal("balance"));
        return  history;
    }
}
