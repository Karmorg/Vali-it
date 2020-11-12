package ee.bcs.valiit.tasks.bank_controller;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Transaction transaction = new Transaction(
                resultSet.getInt("id"),
                resultSet.getInt("account_no"),
                resultSet.getString("transaction_type"),
                resultSet.getBigDecimal("amount"));
        //account.setBalance(resultSet.getBigDecimal("balance"));
        return  transaction;
    }
}
