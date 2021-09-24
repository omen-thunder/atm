package KingsATM;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import KingsATM.model.CardStatus;

public class CashStatusTest {

    @Test
    public void testStringValues() {
        assertEquals("lost", CardStatus.LOST.getStatusString());
        assertEquals("active", CardStatus.ACTIVE.getStatusString());
        assertEquals("exceeded login attempts", CardStatus.BANNED.getStatusString());
        assertEquals("past expiry date", CardStatus.EXPIRED.getStatusString());
        assertEquals("set to inactive", CardStatus.INACTIVE.getStatusString());
        assertEquals("confiscated", CardStatus.CONFISCATED.getStatusString());
    }
    
}
