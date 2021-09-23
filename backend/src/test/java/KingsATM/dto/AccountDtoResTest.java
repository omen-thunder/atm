package KingsATM.dto;

import KingsATM.model.Account;
import KingsATM.model.Card;
import KingsATM.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountDtoResTest {

    private static AccountDtoRes accountDtoRes;
    private static Account account;

    @BeforeEach
    void setUp() {
        account = new Account(1337L);
        accountDtoRes = new AccountDtoRes(account);
    }

    @Test
    void getRole() {
        assertEquals(account.getRole(), accountDtoRes.getRole());
    }

    @Test
    void setRole() {
        accountDtoRes.setRole(Role.ROLE_ADMIN);
        assertEquals(accountDtoRes.getRole(), Role.ROLE_ADMIN);
    }

    @Test
    void getId() {
        assertEquals(account.getId(), accountDtoRes.getId());
    }

    @Test
    void setId() {
        accountDtoRes.setId(1234);
        assertEquals(accountDtoRes.getId(), 1234);
    }

    @Test
    void getBalance() {
        assertEquals(account.getBalance(), accountDtoRes.getBalance());
    }

    @Test
    void setBalance() {
        accountDtoRes.setBalance(200L);
        assertEquals(accountDtoRes.getBalance(), 200L);
    }

    @Test
    void getCards() {
        assertEquals(accountDtoRes.getCards().size(), account.getCards().size());
    }

    @Test
    void setCards() {
        accountDtoRes.setCards(Set.of(new CardDtoRes(new Card("1234", account))));
        assertEquals(accountDtoRes.getCards().size(), 1);
    }
}