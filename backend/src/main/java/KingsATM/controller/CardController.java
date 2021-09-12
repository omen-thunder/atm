package KingsATM.controller;


import KingsATM.dto.AccountDto;
import KingsATM.dto.CardDto;
import KingsATM.model.Account;
import KingsATM.model.Card;
import KingsATM.service.AccountService;
import KingsATM.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("api/card")
public class CardController {

    @Autowired
    CardService cardService;

    @Autowired
    AccountService accountService;

    @Autowired
    EntityManager entityManager;

//    @GetMapping("/{id}")
//    public JsonResponse<CardDto> details(Authentication authentication, @PathVariable Integer id) {
//        // Check to ensure the current user has one of the cards
//    }

    @PostMapping("/create")
    public JsonResponse<CardDto> createCard(@Valid @RequestBody Card newCard, Authentication auth) {
        try {
            // Must be logged in or create card with account via /account/create
            var optionalAccount = accountService.getAccountById(Integer.parseInt(auth.getName()));

            if (optionalAccount.isEmpty()) {
                return new JsonResponse<>(false, "You are currently not logged in and cannot add a new card");
            }

            var account = optionalAccount.get();

            var cardToSave = new Card (
                    newCard.getPin(),
                    account,
                    newCard.getCardStatus()
            );

            Card card = cardService.saveNewCard(cardToSave);

            if (card == null) {
                return new JsonResponse<>(false, "There was an error creating the new card");
            }
            else {
                var cardDto = new CardDto(card);

                return new JsonResponse<>(cardDto);
            }
        }
        catch (RuntimeException e) {

            return new JsonResponse<>(false, e.getLocalizedMessage());
        }
    }

}
