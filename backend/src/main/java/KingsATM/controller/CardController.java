package KingsATM.controller;


import KingsATM.dto.CardDtoRes;
import KingsATM.model.Card;
import KingsATM.service.AccountService;
import KingsATM.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping("/create")
    public JsonResponse<CardDtoRes> createCard(@Valid @RequestBody Card newCard, Authentication auth) {
        try {
            // Must be logged in or create card with account via /account/create
            var account = accountService.getAccountById(Integer.parseInt(auth.getName()));

            if (account == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are currently not logged in");
            }

            var cardToSave = new Card (
                    newCard.getPin(),
                    account,
                    newCard.getStatus()
            );

            Card card = cardService.saveNewCard(cardToSave);

            if (card == null) {
                return new JsonResponse<>(false, "There was an error creating the new card");
            }
            else {
                var cardDto = new CardDtoRes(card);

                return new JsonResponse<>(cardDto);
            }
        }
        catch (RuntimeException e) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an error creating the new card", e);
        }
    }

}
