package KingsATM.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    private static Transaction transaction1;
    private static Date now;

    @BeforeAll
    public static void setUp() {
        now = Calendar.getInstance().getTime();
        transaction1 = new Transaction(
            111,
            TransactionType.WITHDRAW,
            now,
            150L,
            1500L,
            1000,
            20000);
    }

    @AfterAll
    public static void tearDown() {
        now = null;
        transaction1 = null;
    }

    @Test void testGetId() {
        assertEquals(transaction1.getId(), 111);
    }

    @Test
    public void testGetCardNumber() {
        assertEquals(transaction1.getCardNumber(), 20000);
    }

    @Test
    public void testGetAccountNumber() {
        assertEquals(transaction1.getAccountNumber(), 1000);
    }

    @Test
    public void testGetDateTimeNumber() {
        assertEquals(transaction1.getDateTime(), now);
    }

    @Test
    public void testGetAmount() {
        assertEquals(transaction1.getAmount(), 150L);
    }

    @Test
    public void testTransactionType() {
        assertEquals(transaction1.getType(), TransactionType.WITHDRAW);
    }

    @Test
    public void testTransactionBalance() {
        assertEquals(transaction1.getBalance(), 1500L);
    }

    @Test
    public void testTransactionConstructor2() {
        Transaction tx2 = new Transaction(TransactionType.WITHDRAW, 200L, 1500L, 1000, 20000);

        assertEquals(tx2.getAmount(), 200L);
    }

}
