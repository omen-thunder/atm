//package KingsATM;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import java.util.Calendar;
//
//public class ATMCardTest {
//    private Client client1;
//    private Account account1;
//    private Calendar issueMonthAndYear1;
//    private Calendar expireMonthAndYear1;
//    private AtmCard card1;
//    private Calendar issueMonthAndYear2;
//    private Calendar expireMonthAndYear2;
//    private AtmCard card2;
//
//    @BeforeEach
//    public void setUp() {
//        Client client1 = new Client(123, "John", "Smith");
//        Account account1 = new Account(123, client1);
//        Calendar issueMonthAndYear1 =  Calendar.getInstance();
//        issueMonthAndYear1.set(2021, 9, 16);
//        Calendar expireMonthAndYear1 = Calendar.getInstance();
//        expireMonthAndYear1.set(2022, 9, 31);
//        AtmCard card1 = new AtmCard(550, account1, client1, "9999", CardStatus.ACTIVE, issueMonthAndYear1, expireMonthAndYear1);
//        Calendar issueMonthAndYear2 = Calendar.getInstance();
//        issueMonthAndYear2.set(2020, 8, 16);
//        Calendar expireMonthAndYear2 = Calendar.getInstance();
//        expireMonthAndYear1.set(2021, 8, 31);
//        AtmCard card2 = new AtmCard(549, account1, client1, "9876", CardStatus.ACTIVE, issueMonthAndYear2, expireMonthAndYear2);
//    }
//
//    @AfterEach
//    public void tearDown() {
//        client1 = null;
//        account1 = null;
//        issueMonthAndYear1 = null;
//        expireMonthAndYear1 = null;
//        card1 = null;
//        issueMonthAndYear2 = null;
//        expireMonthAndYear2 = null;
//        card2 = null;
//    }
//
//
//}
