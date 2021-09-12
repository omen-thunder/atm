package KingsATM.service;

import KingsATM.model.Account;
import KingsATM.model.Card;
import KingsATM.respository.AccountRepository;
import KingsATM.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static java.lang.Integer.parseInt;


@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public UserDetails loadUserByUsername(String cardNumber) throws UsernameNotFoundException {


        Optional<Card> optionalCard = cardRepository.findById(parseInt(cardNumber));

        if (optionalCard.isEmpty()) {
            throw new UsernameNotFoundException(cardNumber);
        }
        var card = optionalCard.get();

        var account = card.getAccount();

        if (account == null) {
            throw new UsernameNotFoundException(cardNumber);
        }

        return User.withUsername(String.valueOf(account.getId())).password(card.getPin()).authorities("USER").build();
    }

    public Account getAccountByCardId(Integer id) throws EntityNotFoundException {
        var optionalCard = cardRepository.findById(id);

        if (optionalCard.isEmpty())
            throw new EntityNotFoundException();

        var card = optionalCard.get();

        var account = card.getAccount();

        if (account == null) {
            throw new EntityNotFoundException();
        }

        return account;
    }

    public Optional<Account> getAccountById(Integer id) {
        return accountRepository.findById(id);
    }

    public Account saveNewUser(Account account) {
        return accountRepository.save(account);
    }
}
