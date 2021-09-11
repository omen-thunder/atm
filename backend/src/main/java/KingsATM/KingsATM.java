package KingsATM;

import KingsATM.model.Account;
import KingsATM.model.Card;
import KingsATM.respository.AccountRepository;
import KingsATM.respository.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Set;

@SpringBootApplication
public class KingsATM {

    public static void main(String[] args) {

        SpringApplication.run(KingsATM.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(PasswordEncoder encoder, AccountRepository accountRepo, CardRepository cardRepo) {
//        return (args -> {
//
//            Account account1 = new Account(1000,1500L);
//            Card card1 = new Card (
//                    3000,
//                    new Date("01/01/2025"),
//                    CardStatus.ACTIVE,
//                    encoder.encode("1234"),
//                    account1
//            );
//
//            System.out.println(account1.getId());
//            account1.setCards(Set.of(card1));
//
//            accountRepo.save(account1);
//            cardRepo.save(card1);
//        });
//    }

}
