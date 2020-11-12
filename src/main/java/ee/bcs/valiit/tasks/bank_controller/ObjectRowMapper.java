package ee.bcs.valiit.tasks.bank_controller;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException{
        Account account = new Account(resultSet.getInt("account_no"),
                resultSet.getInt("client_id"),
                resultSet.getBigDecimal("balance"));
        //account.setBalance(resultSet.getBigDecimal("balance"));
        return  account;
    }
}
