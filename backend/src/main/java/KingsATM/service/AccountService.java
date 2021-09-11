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

    public AccountService(AccountRepository accountRepository, CardRepository cardRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
    }

    public AccountService() {
    }

    @Override
    public UserDetails loadUserByUsername(String cardNumber) throws UsernameNotFoundException {


        Optional<Card> optionalCard = cardRepository.findById(parseInt(cardNumber));

        if (optionalCard.isEmpty()) {
            throw new UsernameNotFoundException(cardNumber);
        }

        Optional<Account> optionalAccount = optionalCard.map(Card::getAccount);

        if (optionalAccount.isEmpty()) {
            throw new UsernameNotFoundException(cardNumber);
        }

        var card = optionalCard.get();
        var account = optionalAccount.get();

        UserDetails user = User.withUsername(String.valueOf(account.getId())).password(card.getPin()).authorities("USER").build();
        return user;
    }

    public Account getAccountByCardId(Integer id) throws EntityNotFoundException{
        var optionalCard = cardRepository.findById(id);

        if (optionalCard.isEmpty())
            throw new EntityNotFoundException();

        return optionalCard.get().getAccount();
    }

    public Optional<Account> getAccountById(Integer id) {
        return accountRepository.findById(id);
    }
}
