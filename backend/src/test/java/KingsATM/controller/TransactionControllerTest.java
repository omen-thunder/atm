package KingsATM.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void withdrawReturnsReceipt() {

    }

    @Test
    public void depositReturnsReceipt() {

    }

    @Test
    public void withdrawOfTooMuchNotAccepted() {

    }

    @Test
    public void withdrawOfNegativeNumberNotAccepted() {
        
    }

    @Test
    public void depositOfCoinsNotAccepted() {

    }

}