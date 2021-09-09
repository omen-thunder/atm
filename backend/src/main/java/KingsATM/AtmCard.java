package KingsATM;

public interface AtmCard {

    /**
     * Get the 5-digit card number.
     * @return
     */
    public int getCardNumber();

    /**
     * Check the card expiry against system time.
     * @return True, if the card is expired.
     */
    public boolean isExpired();

    /**
     * Get the status of the card. Must be a constant defined in CardStatus.
     * @return CardStatus
     */
    public CardStatus getStatus();

    /**
     * Check a given string against cardPin.
     * @param pin
     * @return True, if the pin is correct, and reset loginAttempt.
     * Otherwise, update loginAttempt and ban the card if too many attempts.
     */
    public boolean checkCardPin(String pin);

    /**
     * Set the card as "banned". It can only be "banned" if the card is "active".
     * @return True, if successful.
     */
    public boolean setBanned() throws IllegalStateException;

    /**
     * Set the card as "lost". It cannot be marked as "lost" if it is already "confiscated".
     * @return True, if successful.
     */
    public boolean setLost() throws IllegalStateException;

    /**
     * Set the card as "confiscated". It can only be confiscated if it is marked as "lost".
     * @return True, if successful.
     */
    public boolean setConfiscated() throws IllegalStateException;

    /**
     * Get the associated account of the card.
     * @return Account
     */
    public Account getLinkedAccount();

    /**
     * Get the card owner.
     * @return Client
     */
    public Client getCardOwner();

    
}
