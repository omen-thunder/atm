package KingsATM.service;

import KingsATM.dto.AccountDtoReq;
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

        return User
                .withUsername(String.valueOf(account.getId()))
                .password(card.getPin())
                .authorities(account.getRole().name())
                .build();
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

    public Account getAccountById(Integer id) throws EntityNotFoundException {
        var account = accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new EntityNotFoundException();
        } else {
            return account.get();
        }
    }

    public Account saveNewAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account createAccountFromDto(AccountDtoReq accountDtoReq) {
        Account account = new Account (
                accountDtoReq.getBalance()
        );
        return this.saveNewAccount(account);
    }
}
