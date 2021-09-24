package KingsATM.dto;

import KingsATM.model.Account;
import KingsATM.model.CashAmount;
import KingsATM.model.Transaction;
import KingsATM.model.TransactionType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CashStoreDtoTest {
    private static CashStoreDto cashStoreDto;

    @BeforeEach
    public void setUp() {
        cashStoreDto = new CashStoreDto(1,2,3,4,5,6,7,8,9,10,11);
    }

    @Test
    public void testConstruction() {
        assertEquals(cashStoreDto.getNum5c(), 1);
        assertEquals(cashStoreDto.getNum10c(), 2);
        assertEquals(cashStoreDto.getNum20c(), 3);
        assertEquals(cashStoreDto.getNum50c(), 4);
        assertEquals(cashStoreDto.getNum1(), 5);
        assertEquals(cashStoreDto.getNum2(), 6);
        assertEquals(cashStoreDto.getNum5(), 7);
        assertEquals(cashStoreDto.getNum10(), 8);
        assertEquals(cashStoreDto.getNum20(), 9);
        assertEquals(cashStoreDto.getNum50(), 10);
        assertEquals(cashStoreDto.getNum100(), 11);
    }

    @Test
    public void testTotalIsCorrect() {
        long expectedTotal =  (
            (1 * 5) +  // five c
            (2 * 10) + // ten c
            (3 * 20) +  // twenty c
            (4 * 50) +  // etc
            (5 * 100) +
            (6 * 200) +
            (7 * 500) +
            (8 * 1000) +
            (9 * 2000) +
            (10 * 5000) +
            (11 * 10000)
        );

        assertEquals(cashStoreDto.getTotalAmount(), expectedTotal);
    }

    @Test
    public void testSettingValue() {
        cashStoreDto.setNum(CashAmount.FIVECENT, 2);
        cashStoreDto.setNum(CashAmount.TENCENT, 3);
        cashStoreDto.setNum(CashAmount.TWENTYCENT, 4);
        cashStoreDto.setNum(CashAmount.FIFTYCENT, 5);
        cashStoreDto.setNum(CashAmount.ONEDOLLAR, 6);
        cashStoreDto.setNum(CashAmount.TWODOLLAR, 7);
        cashStoreDto.setNum(CashAmount.FIVEDOLLAR, 8);
        cashStoreDto.setNum(CashAmount.TENDOLLAR, 9);
        cashStoreDto.setNum(CashAmount.TWENTYDOLLAR, 10);
        cashStoreDto.setNum(CashAmount.FIFTYDOLLAR, 11);
        cashStoreDto.setNum(CashAmount.HUNDREDDOLLAR, 12);

        assertEquals(cashStoreDto.getNum5c(), 2);
        assertEquals(cashStoreDto.getNum10c(), 3);
        assertEquals(cashStoreDto.getNum20c(), 4);
        assertEquals(cashStoreDto.getNum50c(), 5);
        assertEquals(cashStoreDto.getNum1(), 6);
        assertEquals(cashStoreDto.getNum2(), 7);
        assertEquals(cashStoreDto.getNum5(), 8);
        assertEquals(cashStoreDto.getNum10(), 9);
        assertEquals(cashStoreDto.getNum20(), 10);
        assertEquals(cashStoreDto.getNum50(), 11);
        assertEquals(cashStoreDto.getNum100(), 12);
    }


}
