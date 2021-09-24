package KingsATM.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

	private static Card card1;
	private static Card card2;
	private static Calendar cal;
	private static Date now;
	private static Account acc;

	@BeforeEach
	public void setUp() {
		cal = Calendar.getInstance();
		now = new Date();
		acc = new Account(3000L);
		card1 = new Card(
			"1234",
			acc);
		card2 = new Card(
			"5555",
			new Account(1000L),
			CardStatus.INACTIVE);
	}

	@AfterEach
	public void tearDown() {
		cal = null;
		now = null;
		card1 = null;
		card2 = null;
	}

	@Test
	public void testGetExpiryDate() {
		cal.add(Calendar.YEAR, 5);
		assertEquals(card1.getExpiryDate(), cal.getTime());
	}

	@Test
	public void testSetExpiryDate() {
		card1.setExpiryDate(now);
		assertEquals(card1.getExpiryDate(), now);
	}

	@Test
	public void testIsExpired() {
		cal.add(Calendar.YEAR, -5);
		card1.setExpiryDate(cal.getTime());
		assertTrue(card1.isExpired());
		assertFalse(card2.isExpired());
	}

	@Test
	public void testGetStatus() {
		assertEquals(card1.getStatus(), CardStatus.ACTIVE);
		assertEquals(card2.getStatus(), CardStatus.INACTIVE);
	}

	@Test
	public void testSetStatus() {
		card1.setStatus(CardStatus.LOST);
		assertEquals(card1.getStatus(), CardStatus.LOST);
		card2.setStatus(CardStatus.BANNED);
		assertEquals(card2.getStatus(), CardStatus.BANNED);
	}

	@Test
	public void testGetId() {
		assertEquals(String.valueOf(card1.getId()).length(), 5);
	}

	@Test
	public void testGetIssueDate() {
		assertEquals(card1.getIssueDate(), now);
	}

	@Test
	public void testSetIssueDate() {
		cal.add(Calendar.YEAR, 1);
		card1.setIssueDate(cal.getTime());
		assertEquals(card1.getIssueDate(), cal.getTime());
	}

	@Test
	public void testBeforeIssue() {
		assertFalse(card1.beforeIssue());
		cal.add(Calendar.YEAR, -1);
		card1.setIssueDate(cal.getTime());
		assertTrue(card1.beforeIssue());
	}

	@Test
	public void testGetPin() {
		assertEquals(card1.getPin(), "1234");
	}

	@Test
	public void testSetPin() {
		card1.setPin("2222");
		assertEquals(card1.getPin(), "2222");
	}

	@Test
	public void testGetAccount() {
		assertEquals(card1.getAccount(), acc);
	}

	@Test
	public void testSetAccount() {
		Account acc2 = new Account(50L);
		card2.setAccount(acc2);
		assertEquals(card2.getAccount(), acc2);
	}

	@Test
	public void testGetLoginAttempt() {
		assertEquals(card1.getLoginAttempt(), 0);
	}

	@Test
	public void testSetLoginAttempt() {
		card1.setLoginAttempt(2);
		assertEquals(card1.getLoginAttempt(), 2);
		card1.setLoginAttempt(3);
		assertEquals(card2.getStatus(), CardStatus.BANNED);
	}

	@Test
	public void testGetAttemptsRemaining() {
		assertEquals(card1.getAttemptsRemaining(), 3);
		card1.setLoginAttempt(2);
		assertEquals(card1.getAttemptsRemaining(), 1);
	}

	@Test
	public void testIsLocked() {
		assertFalse(card1.isLocked());
		assertTrue(card2.isLocked());
		card1.setStatus(CardStatus.BANNED);
		assertTrue(card1.isLocked());
		card1.setStatus(CardStatus.CONFISCATED);
		assertTrue(card1.isLocked());
	}

	@Test
	public void testSetLost() {
		assertTrue(card1.setLost());
		assertEquals(card1.getStatus(), CardStatus.LOST);
		card1.setStatus(CardStatus.CONFISCATED);
		assertFalse(card1.setLost());
	}

	@Test
	public void testSetConfiscated() {
		assertFalse(card1.setConfiscated());
		card1.setStatus(CardStatus.LOST);
		assertTrue(card1.setConfiscated());
		assertEquals(card1.getStatus(), CardStatus.CONFISCATED);
	}

	@Test
	public void testSetBanned() {
		assertTrue(card1.setBanned());
		assertEquals(card1.getStatus(), CardStatus.BANNED);
		card1.setStatus(CardStatus.INACTIVE);
		assertFalse(card1.setBanned());
	}
}