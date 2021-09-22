package KingsATM.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "card")
@SequenceGenerator(name="card_seq", initialValue = 20000)
public class Card {
    public static final int MAX_ATTEMPTS = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "card_seq")
    private Integer id;

    @NotNull
    private Date issueDate;

    @NotNull
    private Date expiryDate;

    @NotNull
    private CardStatus status;

    @NotNull
    private Integer loginAttempt = 0;

    @NotNull
    @NotBlank
    private String pin;

    @ManyToOne(optional = false)
    @NotNull
    private Account account;

    protected Card() {}

    protected Card(Integer id, CardStatus status, Integer loginAttempt, String pin, Account account) {
        this.id = id;
        this.status = status;
        this.loginAttempt = loginAttempt;
        this.pin = pin;
        this.account = account;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 5);
        this.expiryDate = cal.getTime();
        this.issueDate = new Date();
    }

    public Card(String pin, Account account, CardStatus cardStatus) {
        this.status = cardStatus;
        this.pin = pin;
        this.account = account;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 5);
        this.expiryDate = cal.getTime();
        this.issueDate = new Date();
    }

    public Card(String pin, Account account) {
        this(pin, account, CardStatus.ACTIVE);
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


    public void setStatus(CardStatus cardStatus) {
        this.status = cardStatus;
    }

    /**
     * Get the 5-digit card number.
     * @return Integer
     */
    public Integer getId() {
        return id;
    }


    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Check the card expiry against system time.
     * @return True, if the card is expired.
     */
    public boolean isExpired() {
       return this.expiryDate.before(Calendar.getInstance().getTime());
    }

    /**
     * Check the card issue against system time.
     * @return True, if the card is expired.
     */
    public boolean beforeIssue() {
        return Calendar.getInstance().getTime().before(this.issueDate);
    }

    /**
     * Get the status of the card. Must be a constant defined in CardStatus.
     * @return CardStatus
     */
    public CardStatus getStatus() {
        return status;
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

    public Integer getLoginAttempt() {
        return loginAttempt;
    }

    public Integer getAttemptsRemaining() {
        return MAX_ATTEMPTS - loginAttempt;
    }

    public Boolean isLocked() {
        return this.status == CardStatus.BANNED || this.status == CardStatus.CONFISCATED || this.status == CardStatus.INACTIVE;
    }

    public void setLoginAttempt(Integer loginAttempt) {
        this.loginAttempt = loginAttempt;

        if (this.loginAttempt == MAX_ATTEMPTS) {
            setBanned();
        }
    }

    /**
     * Set the card as "banned". It can only be "banned" if the card is "active".
     * @return True, if successful.
     */
    public boolean setBanned() throws IllegalStateException {
        if (this.status.equals(CardStatus.ACTIVE)) {
            this.status = CardStatus.BANNED;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set the card as "lost". It cannot be marked as "lost" if it is already "confiscated".
     * @return True, if successful.
     */
    public boolean setLost() throws IllegalStateException {
        if (!(this.status.equals(CardStatus.CONFISCATED))) {
            this.status = CardStatus.LOST;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set the card as "confiscated". It can only be confiscated if it is marked as "lost".
     * @return True, if successful.
     */
    public boolean setConfiscated() throws IllegalStateException{
        if(this.status.equals(CardStatus.LOST)) {
            this.status = CardStatus.CONFISCATED;
            return true;
        } else {
            return false;
        }
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }


}
