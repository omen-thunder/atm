package KingsATM.controller;


import KingsATM.model.CardStatus;
import KingsATM.dto.AccountDtoReq;
import KingsATM.dto.AccountDtoRes;
import KingsATM.dto.CardDtoReq;
import KingsATM.dto.LoginDto;
import KingsATM.model.Account;
import KingsATM.model.Card;
import KingsATM.service.AccountService;
import KingsATM.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.NoSuchElementException;

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
    private AuthenticationManager authenticationManager;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/login")
    public JsonResponse<AccountDtoRes> account(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request) {
        Card card;
        Account account;

        try {
            card = cardService.getCardById(loginDto.getCardNumber());
            account = accountService.getAccountByCardId(loginDto.getCardNumber());
        }
        catch (EntityNotFoundException | NoSuchElementException e) {
            return new JsonResponse<>(false, "No account found");
        }

        try {
            if (card.isLocked()) {
                return new JsonResponse<>(false, "Account is currently locked because the card has been "
                        + card.getStatus().getStatusString());
            }
            if (card.isExpired()) {
                return new JsonResponse<>(false, "Card has expired");
            }
            if (card.beforeIssue()) {
                return new JsonResponse<>(false, "Card issue date invalid");
            }

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginDto.getCardNumber(), loginDto.getCardPin());

            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(authRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            // Create a new session and add the security context.
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

            return new JsonResponse<>(new AccountDtoRes(account));
        }
        catch (BadCredentialsException e) {
            return new JsonResponse<>(false, "Username or password incorrect attempts remaining " + card.getAttemptsRemaining());
        }



    }

    @GetMapping("/details")
    public JsonResponse<AccountDtoRes> account(Authentication authentication) {
        try {
            var account = accountService.getAccountByCardId(Integer.parseInt(authentication.getName()));

            var accountDto = new AccountDtoRes(account);

            return new JsonResponse<>(accountDto);
        }
        catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot retrieve details", e);
        }

    }

    @PostMapping("/create")
    public JsonResponse<AccountDtoRes> createAccount(@Valid @RequestBody AccountDtoReq accountDtoReq) {
        // Ensure the balance is divisible by 5
        if (accountDtoReq.getBalance() % 5 != 0) {
            return new JsonResponse<>(false, "You can only deposit Australian notes");
        }
        // Ensure a card has been attached
        if (accountDtoReq.getCards() == null) {
            return new JsonResponse<>(false, "A card with a pin has not been provided");
        }

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

            account.addCard(savedCard);
        }

        var accountDto = new AccountDtoRes(account);

        return new JsonResponse<>(accountDto);


    }

}
