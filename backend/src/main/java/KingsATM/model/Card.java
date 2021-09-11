package KingsATM.model;


import KingsATM.CardStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date expiryDate;

    private CardStatus cardStatus;

    private String pin;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Card(Date expiryDate, CardStatus cardStatus, String pin, Account account) {
        this.expiryDate = expiryDate;
        this.cardStatus = cardStatus;
        this.pin = pin;
        this.account = account;
    }

    public Card(Integer id, Date expiryDate, CardStatus cardStatus, String pin, Account account) {
        this.id = id;
        this.expiryDate = expiryDate;
        this.cardStatus = cardStatus;
        this.pin = pin;
        this.account = account;
    }

    public Card() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account card) {
        this.account = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
