package KingsATM.controller;

import KingsATM.dto.*;
import KingsATM.model.*;
import KingsATM.respository.AccountRepository;
import KingsATM.respository.CardRepository;
import KingsATM.respository.TransactionRepository;
import KingsATM.service.TransactionService;
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


import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

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
        testAccount = accountRepository.save(new Account(150000L));
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
        testAccount.setBalance(150000L);

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
    public void jsonResponseSetters() throws Exception {
        JsonResponse testJsonResponse = new JsonResponse<>(false, null, "No value");

        testJsonResponse.setSuccess(true);
        testJsonResponse.setResult("Value exists!");
        testJsonResponse.setError(null);

        assertTrue(testJsonResponse.isSuccess());
        assertEquals("Value exists!", testJsonResponse.getResult());
        assertEquals(null, testJsonResponse.getError());
    }

    @Test
    public void accountCreationSuccess() throws Exception {

        AccountDtoReq accountReq = new AccountDtoReq(
                150000L,
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
    public void accountNoLinkedCard() throws Exception {
        AccountDtoReq accountReq = new AccountDtoReq(
                150000L,
                new ArrayList<CardDtoReq>() // No cards
        );

        mvc.perform(
                        post("/api/account/create")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(accountReq))
                )
                .andExpect(jsonPath("$.success", is(false)));
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
    public void accountNotFoundReturnsMessage() throws Exception {
        LoginDto login = new LoginDto(1234, "4321");

        mvc.perform(post("/api/account/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(jsonPath("$.success", is(false)));
    }

    @Test
    public void outOfDateCardNotAccepted() throws Exception {

        testCard.setExpiryDate(new Date());

        cardRepository.save(testCard);

        mvc.perform(
                        post("/api/account/login")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(testLogin)))
                .andExpect(jsonPath("$.success", is(false)));
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
                .andExpect(jsonPath("$.success", is(false)));
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
                .andExpect(jsonPath("$.success", is(false)));
    }

    @Test
    public void canRetrieveAccountDetails() throws Exception {
        mvc.perform(get("/api/account/details")
                        .contentType("application/json")
                        .header("Authorization", testAuthHeader))
                .andExpect(jsonPath("$.success", is(true)));
    }

    @Test
    public void adminDepositSuccess() throws Exception {
        testAuthHeader = "Basic " + Base64.getEncoder().encodeToString(("20001:4321").getBytes());

        CashStoreDto testCashStoreDTO = new CashStoreDto(2,2,1,1,1,2,0,3,1,0,2);

        mvc.perform(post("/api/atm/deposit")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testCashStoreDTO))
                        .header("Authorization", testAuthHeader))
                .andExpect(jsonPath("$.success", is(true)));
    }

    @Test
    public void adminCheckBalanceSuccess() throws Exception {
        testAuthHeader = "Basic " + Base64.getEncoder().encodeToString(("20001:4321").getBytes());

        mvc.perform(get("/api/atm/balance")
                        .contentType("application/json")
                        .header("Authorization", testAuthHeader))
                .andExpect(jsonPath("$.success", is(true)));
    }

    @Test
    public void withdrawSuccess() throws Exception {
        mvc.perform(post("/api/transaction/withdraw/2000")
                        .contentType("application/json")
                        .header("Authorization", testAuthHeader))
                .andExpect(jsonPath("$.success", is(true)));
    }

    @Test
    public void withdrawOfTooMuchNotAccepted() throws Exception {
        mvc.perform(post("/api/transaction/withdraw/200000")
                        .contentType("application/json")
                        .header("Authorization", testAuthHeader))
                .andExpect(jsonPath("$.success", is(false)));
    }

    @Test
    public void withdrawOfNegativeNumberNotAccepted() throws Exception {
        mvc.perform(post("/api/transaction/withdraw/-1000")
                        .contentType("application/json")
                        .header("Authorization", testAuthHeader))
                .andExpect(jsonPath("$.success", is(false)));
    }

    @Test
    public void depositSuccess() throws Exception {
        CashStoreDto testCashStoreDTO = new CashStoreDto(0,0,0,0,0,0,0,0,0,0,2);

        mvc.perform(post("/api/transaction/deposit")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testCashStoreDTO))
                        .header("Authorization", testAuthHeader))
                .andExpect(jsonPath("$.success", is(true)));
    }

    @Test
    public void depositOfCoinsNotAccepted() throws Exception {
        CashStoreDto testCashStoreDTO = new CashStoreDto(1,1,9,1,0,1,0,0,0,0,2);
        mvc.perform(post("/api/transaction/deposit")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testCashStoreDTO))
                        .header("Authorization", testAuthHeader))
                .andExpect(jsonPath("$.success", is(false)));
    }

    @Test
    public void transactionBelongsToUser() throws Exception {
        Transaction testTransaction = transactionService.createTransaction (
                TransactionType.DEPOSIT, 5000L, testAccount, testCard);

        mvc.perform(get("/api/transaction/" + testTransaction.getId())
                        .contentType("application/json")
                        .header("Authorization", testAuthHeader))
                .andExpect(jsonPath("$.success", is(true)));

        transactionRepository.delete(testTransaction);
    }

    @Test
    public void transactionDoesNotBelongToUser() throws Exception {
        Transaction testTransaction = transactionService.createTransaction (
                TransactionType.DEPOSIT, 5000L, testAccount, testCard);

        mvc.perform(get("/api/transaction/" + (testTransaction.getId() - 50000))
                        .contentType("application/json")
                        .header("Authorization", testAuthHeader))
                .andExpect(jsonPath("$.success", is(false)));

        transactionRepository.delete(testTransaction);
    }
}