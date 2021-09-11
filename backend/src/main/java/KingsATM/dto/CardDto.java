package KingsATM.dto;

import KingsATM.CardStatus;
import KingsATM.model.Account;
import KingsATM.model.Card;

import java.util.Date;

public class CardDto {
    private Integer id;

    private Date expiryDate;

    private CardStatus cardStatus;

    private Integer accountId;

    public CardDto(Card card) {
        this.id = card.getId();
        this.cardStatus = card.getCardStatus();
        this.expiryDate = card.getExpiryDate();
        this.accountId = card.getAccount().getId();
    }
}
