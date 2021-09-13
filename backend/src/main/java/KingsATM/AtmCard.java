package KingsATM;

import KingsATM.CardStatus;

import java.util.Calendar;
import java.lang.Integer;

public class AtmCard {
    private int cardNumber;
    private Account linkedAccount;
    private Client cardOwner;
    private byte loginAttempt;
    private String cardPin;
    private CardStatus status;
    private byte issueMonth;
    private short issueYear;
    private byte expireMonth;
    private short expireYear;

    public AtmCard() {

    }
    /**
     * Get the 5-digit card number.
     * @return
     */
    public int getCardNumber() {
        return this.cardNumber;
    }

    /**
     * Check the card expiry against system time.
     * @return True, if the card is expired.
     */
    public boolean isExpired() {
        Integer currentYear = new Integer(Calendar.getInstance().get(Calendar.YEAR)); //IntelliJ says that the Integer constructor is deprecated, but this was the only way I could find to be able to use shortValue() and byteValue(), which are methods of the Integer class
        Integer currentMonth = new Integer(Calendar.getInstance().get(Calendar.MONTH));
        if ((currentYear.shortValue() > this.expireYear) & (currentMonth.byteValue() > this.expireMonth)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Get the status of the card. Must be a constant defined in CardStatus.
     * @return CardStatus
     */
    public CardStatus getStatus() {
        return this.status;
    }

    /**
     * Check a given string against cardPin.
     * @param pin
     * @return True, if the pin is correct, and reset loginAttempt.
     * Otherwise, update loginAttempt and ban the card if too many attempts.
     */
    public boolean checkCardPin(String pin) {
        if (pin.equals(this.cardPin)) {
            this.loginAttempt = 0;
            return true;
        } else {
            this.loginAttempt += 1;
            if (this.loginAttempt > 5) {
                this.setBanned();
            }
            return false;
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

    /**
     * Get the associated account of the card.
     * @return Account
     */
    public Account getLinkedAccount() {
        return this.linkedAccount;
    }

    /**
     * Get the card owner.
     * @return Client
     */
    public Client getCardOwner() {
        return this.cardOwner;
    }

    
}