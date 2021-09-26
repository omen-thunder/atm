package KingsATM.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class AccountTest {
    private Account account1;
    private Account account2;

    @BeforeEach
    public void setUp() {
        account1 = new Account(1337, 0L);
        account2 = new Account(420, 100000L);
    }

    @AfterEach
    public void tearDown() {
        account1 = null;
        account2 = null;
    }

    @Test
    public void testGetAccountNumber() {
        assertEquals(account1.getId(), 1337);
        assertEquals(account2.getId(), 420);
    }

    @Test
    public void testGetBalance() {
        assertEquals(account1.getBalance(), 0);
        assertEquals(account2.getBalance(), 100000);
    }

    @Test
    public void testIncrBalance() {
        assertEquals(account1.getBalance(), 0);
        assertEquals(account1.incrBalance(20000L), 20000L);
        assertEquals(account2.incrBalance(5000L), 105000L);
        assertThrows(IllegalArgumentException.class, () -> account1.incrBalance(-1L));
        assertThrows(IllegalStateException.class, () -> account1.incrBalance(Long.MAX_VALUE));
    }

    @Test
    public void testDecrBalance() {
        assertEquals(account2.decrBalance(2000L), 98000L);
        assertThrows(IllegalArgumentException.class, () -> account1.decrBalance(-100L));
        assertThrows(IllegalStateException.class, () -> account2.decrBalance(200000L));
    }

    @Test
    public void testGetCards() {
        Card card1 = new Card(12345, CardStatus.ACTIVE, 0, "1234", account1);
        assertTrue(account1.getCards().isEmpty());
        account1.addCard(card1);
        assertTrue(account1.getCards().stream().anyMatch((c -> c.getId() == 12345 )));

        Card card2 = new Card(23456, CardStatus.ACTIVE, 0, "1234", account1);
        account1.addCard(card2);
        assertEquals(account1.getCards().size(), 2);
    }

    @Test
    public void testAddCard() {
        Card card1 = new Card(12345, CardStatus.ACTIVE, 0, "1234", account1);

        account1.addCard(card1);
        assertTrue(account1.getCards().stream().anyMatch((c -> c.getId() == 12345 )));
        assertThrows(IllegalArgumentException.class, () -> account1.addCard(card1));
    }

    @Test
    public void testRemoveCard() {
        Card card1 = new Card(12345, CardStatus.ACTIVE, 0, "1234", account1);

        account1.addCard(card1);
        assertTrue(account1.getCards().stream().anyMatch((c -> c.getId() == 12345 )));

        account1.removeCard(card1);
        assertFalse(account1.getCards().stream().anyMatch((c -> c.getId() == 12345 )));

        assertThrows(NoSuchElementException.class, () -> account1.removeCard(card1));
    }
}
