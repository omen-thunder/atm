package KingsATM.service;

import KingsATM.model.Card;
import KingsATM.respository.AccountRepository;
import KingsATM.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    public Card getCardById(Integer id) {
        var optionalCard = cardRepository.findById(id);

        if (optionalCard.isEmpty()) {
            throw new EntityNotFoundException("No card by of id: "+ id + ", was found");
        }

        return optionalCard.get();
    }

    public Card saveNewCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }


}
