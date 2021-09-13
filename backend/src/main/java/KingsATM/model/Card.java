package KingsATM.model;


import KingsATM.CardStatus;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "card")
@SequenceGenerator(name="card_seq", initialValue = 2000)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "card_seq")
    private Integer id;

    @NotNull
    private Date expiryDate;

    @NotNull
    private CardStatus cardStatus;

    @NotNull
    @NotBlank
    private String pin;

    @ManyToOne(optional = false)
    @NotNull
    private Account account;

    public Card(String pin, Account account, CardStatus cardStatus) {
        this.cardStatus = cardStatus;
        this.pin = pin;
        this.account = account;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 5);
        this.expiryDate = cal.getTime();
    }

    public Card(String pin, Account account) {
        this(pin, account, CardStatus.ACTIVE);
    }

    protected Card() {}

    public Integer getId() {
        return id;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
