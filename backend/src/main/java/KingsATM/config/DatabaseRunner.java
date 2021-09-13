package KingsATM.config;

import KingsATM.CardStatus;
import KingsATM.Role;
import KingsATM.model.Account;
import KingsATM.model.Card;
import KingsATM.respository.AccountRepository;
import KingsATM.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Date;

@Component
public class DatabaseRunner implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        accountRepository.deleteAll();
        cardRepository.deleteAll();

        // Create a regular user
        var account1 = accountRepository.save(new Account(1500L));
        var card1 = cardRepository.save(new Card(
                passwordEncoder().encode("1234"),
                entityManager.getReference(Account.class, account1.getId()),
                CardStatus.ACTIVE));

        // Create an admin user
        var admin1 = accountRepository.save(new Account(Role.ROLE_ADMIN));
        var adminCard = cardRepository.save( new Card (
                passwordEncoder().encode("4321"),
                entityManager.getReference(Account.class, admin1.getId()),
                CardStatus.ACTIVE));
    }

}
