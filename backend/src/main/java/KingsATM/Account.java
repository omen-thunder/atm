package KingsATM;

import java.util.HashMap;

public interface Account {

    /**
     * Get account number.
     * @return Integer
     */
    public int getAccountNumber();

    /**
     * Get account balance.
     * @return Long
     */
    public long getBalance();

    /**
     * Increase balanace by a given amount.
     * @param amount
     * @return Updated account balance.
     */
    public long incrBalance(long amount);

    /**
     * Decrease balance by a given amount. Throws IllegalStateException if there is insufficient fund.
     * @param amount
     * @retur Updated account balance.
     * @throws IllegalStateException
     */
    public long decrBalance(long amount) throws IllegalStateException;

    /**
     * Get all the associated cards.
     * @return HashMap of cards
     */
    public HashMap<Integer, AtmCard> getCards();

    /**
     * Add an AtmCard to the account.
     * @param card
     * @return The added AtmCard.
     */
    public AtmCard addCard(AtmCard card);

    /**
     * Remove an AtmCard from the account.
     * @param card
     * @return The removed AtmCard.
     */
    public AtmCard removeCard(AtmCard card);

}
