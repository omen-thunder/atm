//package KingsATM;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import java.lang.Exception;
//// import java.util.NavigableMap;
//import java.util.TreeMap;
//
//
//public class CashStoreTest {
//	private CashStore cash1;
//
//	@BeforeEach
//	public void setUp() {
//		cash1 = new CashStore();
//	}
//
//	@AfterEach
//	public void tearDown() {
//		cash1 = null;
//	}
//
//	@Test
//	public void testAddNotes() {
//		assertEquals(cash1.addNotes((short) 5, 100), 100);
//		assertEquals(cash1.getNoteQuantity((short) 5), 100);
//		assertThrows(IllegalArgumentException.class, () -> cash1.addNotes((short) 20, -1));
//		assertThrows(IllegalArgumentException.class, () -> cash1.addNotes((short) 99, 100));
//		assertThrows(IllegalArgumentException.class, () -> cash1.addNotes((short) 20, Integer.MAX_VALUE + 1));
//
//		cash1.addNotes((short) 20, 1);
//		assertThrows(IllegalStateException.class, () -> cash1.addNotes((short) 20, Integer.MAX_VALUE));
//	}
//
//	@Test
//	public void testWithdrawNotes() {
//		cash1.addNotes((short) 50, 1000);
//		assertEquals(cash1.withdrawNotes((short) 50, 100), 900);
//		assertEquals(cash1.getNoteQuantity((short) 50), 900);
//		assertThrows(IllegalArgumentException.class, () -> cash1.withdrawNotes((short) 50, -1));
//		assertThrows(IllegalArgumentException.class, () -> cash1.withdrawNotes((short) 99, 1));
//		assertThrows(IllegalStateException.class, () -> cash1.withdrawNotes((short) 10, 1));
//	}
//
//	@Test
//	public void testGetNoteQuantity() {
//		cash1.addNotes((short) 20, 600);
//		assertEquals(cash1.getNoteQuantity((short) 5), 0);
//		assertEquals(cash1.getNoteQuantity((short) 20), 600);
//		assertThrows(IllegalArgumentException.class, () -> cash1.getNoteQuantity((short) 99));
//	}
//
//	@Test
//	public void testGetTotalValue() {
//		cash1.addNotes((short) 5, 5);
//		cash1.addNotes((short) 10, 3);
//		cash1.addNotes((short) 20, 15);
//		cash1.addNotes((short) 50, 100);
//		cash1.addNotes((short) 100, 35);
//		assertEquals(cash1.getTotalValue(), 5*5+10*3+20*15+50*100+100*35);
//	}
//
//	@Test
//	public void testWithdraw1() {
//		TreeMap<Short, Integer> correct = new TreeMap<Short, Integer>();
//		correct.put((short) 5, 1);
//		correct.put((short) 10, 1);
//		correct.put((short) 20, 1);
//		correct.put((short) 50, 1);
//		correct.put((short) 100, 5);
//
//		cash1.addNotes((short) 5, 1000);
//		cash1.addNotes((short) 10, 1000);
//		cash1.addNotes((short) 20, 1000);
//		cash1.addNotes((short) 50, 1000);
//		cash1.addNotes((short) 100, 1000);
//
//		assertEquals(cash1.withdraw(585), correct);
//	}
//
//	@Test
//	public void testWithdraw2() {
//		TreeMap<Short, Integer> correct = new TreeMap<Short, Integer>();
//		correct.put((short) 5, 3);
//		correct.put((short) 10, 0);
//		correct.put((short) 20, 9);
//		correct.put((short) 50, 0);
//		correct.put((short) 100, 0);
//
//		cash1.addNotes((short) 5, 1000);
//		cash1.addNotes((short) 20, 1000);
//		assertEquals(cash1.withdraw(195), correct);
//	}
//}
//
//
