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
public class ClientRepository {

    //See on teine soovitatud viis @Autowired asemel - kasutada konstruktorit
    private NamedParameterJdbcTemplate jdbcTemplate;

    public ClientRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createClient (String fName, String lName, String idCode){
        String sql = "INSERT INTO client (client_id, f_name, l_name) " +
                "VALUES (:clientid, :fname, :lname)";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("personal_id", idCode);
        paramMap.put("fname", fName);
        paramMap.put("lname", lName);
        jdbcTemplate.update(sql, paramMap);
    }
    public Integer addClient (String fName,String lName){
        String sql = "INSERT INTO client ( f_name, l_name) " +
                "VALUES ( :fname, :lname)";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("fname", fName);
        paramMap.put("lname", lName);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        jdbcTemplate.update(sql, paramSource, keyHolder);
        Long id = (Long) keyHolder.getKeys().get("id");
        return Math.toIntExact(id);
    }
}
