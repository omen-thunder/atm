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
	cashRepository.save(new Cash(5));
	cashRepository.save(new Cash(10));
	cashRepository.save(new Cash(20));
	cashRepository.save(new Cash(50));
	cashRepository.save(new Cash(100));
	cashRepository.save(new Cash(200));
	cashRepository.save(new Cash(500));
	cashRepository.save(new Cash(1000));
	cashRepository.save(new Cash(2000));
	cashRepository.save(new Cash(5000));
	cashRepository.save(new Cash(10000));

        // Create a regular user
        var account1 = accountRepository.save(new Account(150000L));
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
