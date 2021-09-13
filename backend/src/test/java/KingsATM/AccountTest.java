package KingsATM;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.lang.Exception;

public class AccountTest {
	private Client client1;
	private Account account1;
	private Account account2;

	@BeforeEach
	public void setUp() {
		client1 = new Client();
		account1 = new Account(1337, client1);
		account1 = new Account(420, client1, 1738);
	}

	@AfterEach
	public void tearDown() {
		client1 = null;
		account1 = null;
		account2 = null;
	}

	@Test
	public void testGetAccountNumber() {
		assertEquals(account1.getAccountNumber(), 1337);
		assertEquals(account2.getAccountNumber(), 420);
	}

	@Test
	public void testGetBalance() {
		assertEquals(account1.getBalance(), 0);
		assertEquals(account2.getBalance(), 1738);
	}

	@Test
	public void testIncrBalance() {
		assertEquals(account1.incrBalance(200), 200);
		assertEquals(account2.incrBalance(50), 470);
		assertThrows(IllegalArgumentException.class, () -> account1.incrBalance(-1));
		assertThrows(IllegalStateException.class, () -> account1.incrBalance(Long.MAX_VALUE));
	}

	@Test
	public void testDecrBalance() {
		assertEquals(account2.decrBalance(20), 400);
		assertThrows(IllegalArgumentException.class, () -> account1.decrBalance(-1));
		assertThrows(IllegalStateException.class, () -> account2.decrBalance(500));
	}

	@Test
	public void testGetCards() {
		AtmCard card1 = new AtmCard();
		assertTrue(account1.getCards().isEmpty());
		account1.addCard((short) 12345, card1);
		assertEquals(account1.getCards().get(123), card1);
		account1.addCard((short) 23456, new AtmCard());
		assertEquals(account1.getCards().size(), 2);
	}

	@Test
	public void testAddCard() {
		AtmCard card1 = new AtmCard();
		account1.addCard((short) 12345, card1);
		assertEquals(account1.getCards().get(123), card1);
		assertThrows(IllegalArgumentException.class, () -> account1.addCard((short) 12345, new AtmCard()));
		assertThrows(IllegalArgumentException.class, () -> account1.addCard((short) 99999, card1));
	}

	@Test
	public void testRemoveCard() {
		AtmCard card1 = new AtmCard();
		account1.addCard((short) 12345, card1);
		assertEquals(account1.removeCard((short) 12345), card1);
		assertThrows(IllegalArgumentException.class, () -> account1.removeCard((short) 12345));
	}
}
