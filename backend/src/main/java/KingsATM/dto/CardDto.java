package KingsATM.dto;

import KingsATM.CardStatus;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
