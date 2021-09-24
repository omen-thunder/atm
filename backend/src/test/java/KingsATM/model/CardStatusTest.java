package KingsATM.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardStatusTest {

	@Test
	public void testGetStatusString() {
		assertEquals(CardStatus.INACTIVE.getStatusString(), "set to inactive);
		assertEquals(CardStatus.ACTIVE.getStatusString(), "active");
		assertEquals(CardStatus.BANNED.getStatusString(), "exceeded login attempts");
		assertEquals(CardStatus.LOST.getStatusString(), "lost");
		assertEquals(CardStatus.CONFISCATED.getStatusString(), "confiscated");
		assertEquals(CardStatus.EXPIRED.getStatusString(), "past expiry date");
	}
}
