package KingsATM.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class CashTest {
    private Cash c;

    @BeforeEach
    public void setUp() {
        c = new Cash(10, 200);
    }

    @AfterEach
    public void tearDown() {
        c = null;
    }

    @Test
    public void testGetAmount() {
        assertEquals(c.getAmount(), 200);
    }

    @Test
    public void testGetValue() {
        assertEquals(c.getValue(), 10L);
    }

    @Test
    public void testGetTotal() {
        assertEquals(c.getTotal(), 2000L);
    }

    @Test
    public void testIncrAmount() {
        c.incrAmount(100);
        assertEquals(c.getAmount(), 300);
        assertEquals(c.getValue(), 10L);
        assertEquals(c.getTotal(), 3000L);
    }

    @Test
    public void testDecrAmount() {
        c.decrAmount(150);
        assertEquals(c.getAmount(), 50);
        assertEquals(c.getValue(), 10L);
        assertEquals(c.getTotal(), 500L);
    }

    @Test
    public void testExceptions() {
        assertThrows(IllegalArgumentException.class, () -> c.incrAmount(-10));
        assertThrows(IllegalStateException.class, () -> c.incrAmount(Integer.MAX_VALUE));

    }

}
