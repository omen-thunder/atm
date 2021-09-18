package KingsATM.controller;

import KingsATM.dto.AccountDtoReq;
import KingsATM.dto.CardDtoReq;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void accountCreationTest() throws Exception {

        AccountDtoReq accountReq = new AccountDtoReq(
                1500L,
                new ArrayList<>(Arrays.asList(new CardDtoReq("$2a$10$8K.aWLbhQ9fOcnmFLqF2UOSFkiUOLii/G0u0ty2uvAVLFyXojOdyi"))) // 1234
        );

        mvc.perform(
                post("/api/account/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(accountReq)))
                .andExpect(status().isOk());
    }

    @Test
    public void outOfDateCardNotAccepted() throws Exception {

    }

    @Test
    public void beforeIssueDateCardNotAccepted() throws Exception {

    }

    @Test
    public void stolenOrLostCardNotAccepted() throws Exception {

    }

    @Test
    public void lockedCardNotAccepted() throws Exception {

    }

    @Test
    public void incorrectPinNotAccepted() throws Exception {

    }

}