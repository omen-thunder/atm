package KingsATM.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CashTest {
	private Cash cash1;
	private Cash cash2;

	@BeforeEach
	public void setUp() {
		cash1 = new Cash(500L);
		cash2 = new Cash(1000L, 100);
	}

	@AfterEach
	public void tearDown() {
		cash1 = null;
		cash2 = null;
	}

	@Test
	public void testGetValue() {
		assertEquals(cash1.getValue(), 500L);
	}

	@Test
	public void testGetAmount() {
		assertEquals(cash2.getAmount(), 100);
	}

	@Test
	public void testGetTotal() {
		assertEquals(cash2.getTotal(), 1000L * 100L);
	}

	@Test
	public void testIncrAmount() {
		assertEquals(cash1.incrAmount(25), 25);
		assertEquals(cash1.getAmount(), 25);
		assertThrows(IllegalArgumentException.class, () -> cash1.incrAmount(-1));
		assertThrows(IllegalStateException.class, () -> cash1.incrAmount(Integer.MAX_VALUE));
	}

	@Test
	public void testDecrAmount() {
		assertEquals(cash2.decrAmount(25), 75);
		assertEquals(cash2.getAmount(), 75);
		assertThrows(IllegalArgumentException.class, () -> cash1.decrAmount(-1));
		assertThrows(IllegalStateException.class, () -> cash1.decrAmount(Integer.MAX_VALUE));
	}
}
