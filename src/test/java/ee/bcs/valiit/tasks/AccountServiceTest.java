package ee.bcs.valiit.tasks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountServiceTest {

    //Need päringud lähevad kontrollerisse
    @Autowired
    private MockMvc mockMvc;

    @Test
    void balance () throws Exception{

        mockMvc.perform(get("/balance/3")
                .contentType("Application/json"))
                //.param("amount","300"))
                .andExpect(status().isOk())
                .andExpect(content().string("158.07"));
                //.andExpect(content().equals(new  BigDecimal("157")));
                //.andExpect(ResultMatcher.matchAll(content(),new BigDecimal("157")));
    }
    @Test
    void balance_2 () throws Exception{

        mockMvc.perform(get("/myAccounts/3")
                .contentType("Application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].balance").value("158.07"));

    }
}
