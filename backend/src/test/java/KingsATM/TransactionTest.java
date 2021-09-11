package KingsATM;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class TransactionTest {
	private Transaction transaction1;
	private Transaction transaction2;
	private LocalDateTime now;

	@BeforeAll
	public void setUp() {
		now = LocalDateTime.now();
		transaction1 = new Transaction(111, 222, 333, 444, now, 555, "Complete", "Purchase");
	}

	@AfterAll
	public void tearDown() {
		now = null;
		transaction1 = null;
	}

	@Test
	public void testGetClientNumber() {
		assertEquals(transaction1.getClientNumber(), 111);
	}

	@Test
	public void testGetAccountNumber() {
		assertEquals(transaction1.getAccountNumber(), 222);
	}

	@Test
	public void testGetCardNumber() {
		assertEquals(transaction1.getCardNumber(), 333);
	}

	@Test
	public void testGetDateTimeNumber() {
		assertEquals(transaction1.getDateTime(), now);
	}

	@Test
	public void testGetAmount() {
		assertEquals(transaction1.getAmount(), 555);
	}

	@Test
	public void testGetStatus() {
		assertEquals(transaction1.getStatus(), "Complete");
	}

	@Test
	public void testGetComment() {
		assertEquals(transaction1.getComment(), "Purchase");
	}
}
