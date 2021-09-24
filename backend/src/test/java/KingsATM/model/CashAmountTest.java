package KingsATM.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CashAmountTest {

	@Test
	public void testGetValue() {
		assertEquals(CashAmount.FIVECENT.getValue(), 5L);
		assertEquals(CashAmount.TENCENT.getValue(), 10L);
		assertEquals(CashAmount.TWENTYCENT.getValue(), 20L);
		assertEquals(CashAmount.FIFTYCENT.getValue(), 50L);
		assertEquals(CashAmount.ONEDOLLAR.getValue(), 100L);
		assertEquals(CashAmount.TWODOLLAR.getValue(), 200L);
		assertEquals(CashAmount.FIVEDOLLAR.getValue(), 500L);
		assertEquals(CashAmount.TENDOLLAR.getValue(), 1000L);
		assertEquals(CashAmount.TWENTYDOLLAR.getValue(), 2000L);
		assertEquals(CashAmount.FIFTYDOLLAR.getValue(), 5000L);
		assertEquals(CashAmount.HUNDREDDOLLAR.getValue(), 10000L);
	}

	@Test
	public void testGetEnum() {
		assertEquals(CashAmount.getEnum(5L), CashAmount.FIVECENT);
		assertEquals(CashAmount.getEnum(10L), CashAmount.TENCENT);
		assertEquals(CashAmount.getEnum(20L), CashAmount.TWENTYCENT);
		assertEquals(CashAmount.getEnum(50L), CashAmount.FIFTYCENT);
		assertEquals(CashAmount.getEnum(100L), CashAmount.ONEDOLLAR);
		assertEquals(CashAmount.getEnum(200L), CashAmount.TWODOLLAR);
		assertEquals(CashAmount.getEnum(500L), CashAmount.FIVEDOLLAR);
		assertEquals(CashAmount.getEnum(1000L), CashAmount.TENDOLLAR);
		assertEquals(CashAmount.getEnum(2000L), CashAmount.TWENTYDOLLAR);
		assertEquals(CashAmount.getEnum(5000L), CashAmount.FIFTYDOLLAR);
		assertEquals(CashAmount.getEnum(10000L), CashAmount.HUNDREDDOLLAR);
		assertThrows(IllegalArgumentException.class, () -> CashAmount.getEnum(-1L));
	}
}
