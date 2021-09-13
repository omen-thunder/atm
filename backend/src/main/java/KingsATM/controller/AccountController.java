package KingsATM.controller;


import KingsATM.CardStatus;
import KingsATM.dto.AccountDtoReq;
import KingsATM.dto.AccountDtoRes;
import KingsATM.dto.CardDtoReq;
import KingsATM.model.Account;
import KingsATM.model.Card;
import KingsATM.service.AccountService;
import KingsATM.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    CardService cardService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @GetMapping("/details")
    public JsonResponse<AccountDtoRes> account(Authentication authentication) {
        try {
            var account = accountService.getAccountById(Integer.parseInt(authentication.getName()));

            var accountDto = new AccountDtoRes(account);

            return new JsonResponse<>(accountDto);
        }
        catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot retrieve details", e);
        }

    }

    @PostMapping("/create")
    public JsonResponse<AccountDtoRes> createAccount(@RequestBody AccountDtoReq accountDtoReq) {
        // Create the account
        var account = accountService.createAccountFromDto(accountDtoReq);

        if (account == null) {
            return new JsonResponse<>(false, "There was an error creating the new account");
        }

        // Save any cards from the dto
        for (CardDtoReq cardDtoReq : accountDtoReq.getCards()) {
            var card = new Card (
                    passwordEncoder().encode("1234"),
                    entityManager.getReference(Account.class, account.getId()),
                    CardStatus.ACTIVE
            );
            var savedCard = cardService.saveNewCard(card);

            if (savedCard == null) {
                return new JsonResponse<>(false, "There was an error attaching the card to the account");
            }

            account.addNewCard(savedCard);
        }

        var accountDto = new AccountDtoRes(account);

        return new JsonResponse<>(accountDto);


    }

}
