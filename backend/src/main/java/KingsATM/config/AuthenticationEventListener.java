package KingsATM.config;

import KingsATM.service.AccountService;
import KingsATM.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The main purpose of this class is to watch for notifications
 * from spring security when there has been a login attempt on one of the accounts.
 */

@Component
public class AuthenticationEventListener {

    @Autowired
    AccountService accountService;

    @Autowired
    CardService cardService;

    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {

        event.getAuthentication().getPrincipal();
        // update the failed login count for the account
        var card = cardService.getCardById((Integer) event.getAuthentication().getPrincipal());

        card.setLoginAttempt(card.getLoginAttempt() + 1);

        card = cardService.updateCard(card);
    }

    @EventListener
    public void authenticationFailed(AuthenticationSuccessEvent event) {

        var user = (UserDetails) event.getAuthentication().getPrincipal();

        // reset the failed login count for the user
        var card = cardService.getCardById(Integer.parseInt(user.getUsername()));

        if (card.getLoginAttempt() > 0) {
            card.setLoginAttempt(0);
            cardService.updateCard(card);
        }
    }



}