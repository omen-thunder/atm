package KingsATM.controller;

import KingsATM.model.CardStatus;
import KingsATM.dto.AccountDtoReq;
import KingsATM.dto.CardDtoReq;
import KingsATM.dto.LoginDto;
import KingsATM.model.Account;
import KingsATM.model.Card;
import KingsATM.respository.AccountRepository;
import KingsATM.respository.CardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;

    private static Account testAccount;
    private static Card testCard;
    private static LoginDto testLogin;
    private static String testAuthHeader;
    private static final String CARD_PIN = "1234";

    @BeforeAll
    public static void init(
            @Autowired AccountRepository accountRepository,
            @Autowired CardRepository cardRepository,
            @Autowired PasswordEncoder passwordEncoder,
            @Autowired EntityManager entityManager)
    {
        testAccount = accountRepository.save(new Account(1500L));
        testCard = cardRepository.save(
                new Card(
                    passwordEncoder.encode(CARD_PIN),
                    entityManager.getReference(Account.class, testAccount.getId()),
                    CardStatus.ACTIVE
                )
        );
        testLogin =  new LoginDto(testCard.getId(), CARD_PIN);
        testAuthHeader = "Basic " + Base64.getEncoder().encodeToString((testCard.getId() + ":" + CARD_PIN).getBytes());

    }

    @AfterEach
    public void resetAccount() {
        testAccount.setBalance(1500L);

        // Reset issue
        testCard.setIssueDate(Date.from(
                LocalDate.now().minusDays(100).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
        );
        // Back to Active
        testCard.setStatus(CardStatus.ACTIVE);

        // Expiry + 5 years
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 5);
        testCard.setExpiryDate(cal.getTime());

        // Reset login attempts
        testCard.setLoginAttempt(0);

        cardRepository.save(testCard);
        accountRepository.save(testAccount);


    }

    @AfterAll
    public static void tearDown (
            @Autowired AccountRepository accountRepository,
            @Autowired CardRepository cardRepository)
    {
        cardRepository.delete(testCard);
        accountRepository.delete(testAccount);
    }

    @Test
    public void accountCreationWorks() throws Exception {

        AccountDtoReq accountReq = new AccountDtoReq(
                1500L,
                new ArrayList<>(Arrays.asList(new CardDtoReq("$2a$10$8K.aWLbhQ9fOcnmFLqF2UOSFkiUOLii/G0u0ty2uvAVLFyXojOdyi"))) // 1234
        );

        mvc.perform(
                post("/api/account/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(accountReq))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)));
    }

    @Test
    public void goodCredentialsResultsInSuccessfulLogin() throws Exception {
        mvc.perform(post("/api/account/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testLogin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)));
    }

    @Test
    public void outOfDateCardNotAccepted() throws Exception {

        testCard.setExpiryDate(new Date());

        cardRepository.save(testCard);

        mvc.perform(
                post("/api/account/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testLogin)))
                .andExpect(jsonPath("$.error", is("Card has expired")));
    }

    @Test
    public void beforeIssueDateCardNotAccepted() throws Exception {

        // Long winded way of setting to a future date
        testCard.setIssueDate(
                Date.from(LocalDate.now().plusDays(100).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()
        ));

        cardRepository.save(testCard);

        mvc.perform(post("/api/account/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testLogin)))
                .andExpect(jsonPath("$.error", is("Card issue date invalid")));
    }

    @Test
    public void stolenOrLostCardNotAccepted() throws Exception {

        testCard.setStatus(CardStatus.CONFISCATED);
        cardRepository.save(testCard);

        LoginDto login = new LoginDto(testCard.getId(), CARD_PIN);

        mvc.perform(post("/api/account/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(jsonPath("$.success", is(false)));

    }

    @Test
    public void lockedCardNotAccepted() throws Exception {
        testCard.setStatus(CardStatus.BANNED);
        cardRepository.save(testCard);

        mvc.perform(post("/api/account/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testLogin)))
                .andExpect(jsonPath("$.success", is(false)));
    }

    @Test
    public void incorrectPinNotAcceptedAndDeductsAttempts() throws Exception {

        // Incorrect pin
        LoginDto login = new LoginDto(testCard.getId(), "4321");

        mvc.perform(post("/api/account/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(jsonPath("$.error", is("Username or password incorrect attempts remaining 2")));
    }

    @Test
    public void accountNotFoundReturnsMessage() throws Exception {
        LoginDto login = new LoginDto(1234, "4321");

        mvc.perform(post("/api/account/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(jsonPath("$.error", is("No account found")));
    }

    @Test
    public void withdrawReturnsReceipt() throws Exception {
        mvc.perform(post("/api/transaction/withdraw/200")
                        .contentType("application/json")
                        .header("Authorization", testAuthHeader))
                .andExpect(status().isOk());
    }

    @Test
    public void withdrawOfTooMuchNotAccepted() throws Exception {
        mvc.perform(post("/api/transaction/withdraw/2000")
                        .contentType("application/json")
                        .header("Authorization", testAuthHeader))
                .andExpect(jsonPath("$.error", is("Insufficient funds")));
    }

    @Test
    public void withdrawOfNegativeNumberNotAccepted() throws Exception {
        mvc.perform(post("/api/transaction/withdraw/-1000")
                        .contentType("application/json")
                        .header("Authorization", testAuthHeader))
                .andExpect(jsonPath("$.error", is("Amount negative")));
    }

    @Test
    public void depositReturnsReceipt() {

    }

    @Test
    public void depositOfCoinsNotAccepted() {

    }




}