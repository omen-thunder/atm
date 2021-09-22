package KingsATM.config;

import KingsATM.model.CardStatus;
import KingsATM.model.Role;
import KingsATM.model.Account;
import KingsATM.model.Card;
import KingsATM.model.Cash;
import KingsATM.respository.AccountRepository;
import KingsATM.respository.CardRepository;
import KingsATM.respository.CashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class DatabaseRunner implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CashRepository cashRepository;

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
	cashRepository.deleteAll();

	// Populate cash database with coin/note values
	cashRepository.save(new Cash(0.05f));
	cashRepository.save(new Cash(0.1f));
	cashRepository.save(new Cash(0.2f));
	cashRepository.save(new Cash(0.5f));
	cashRepository.save(new Cash(1.0f));
	cashRepository.save(new Cash(2.0f));
	cashRepository.save(new Cash(5.0f));
	cashRepository.save(new Cash(10.0f));
	cashRepository.save(new Cash(20.0f));
	cashRepository.save(new Cash(50.0f));
	cashRepository.save(new Cash(100.0f));

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
