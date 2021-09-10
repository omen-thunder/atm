package KingsATM;

import java.util.HashMap;

public interface AtmAccount {

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
     * Increase balanace by a given amount. Throws IllegalStateException if overflow will occur (lucky you).
     * @param amount
     * @return Updated account balance.
     * @throws IllegalStateException
     */
    public long incrBalance(long amount);

    /**
     * Decrease balance by a given amount. Throws IllegalStateException if there is insufficient fund.
     * @param amount
     * @return Updated account balance.
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
     * @param cardNum
     * @param card
     * @return true if successful, false if unsuccessful
     */
    public boolean addCard(short cardNum, AtmCard card);

    /**
     * Remove an AtmCard from the account.
     * @param card
     * @return The removed AtmCard if successful, NULL if unsuccessful.
     */
    public AtmCard removeCard(short cardNum);
}

public class Account implements AtmAccount {
	private int accountNumber;
	private Client accountHolder;
	private long balance;
	private Map<short, AtmCard> cards;

	public Account (int accountNumber, Client accountHolder) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		balance = 0;
		cards = new HashMap<shot, AtmCard>;
	}

	public Account (int accountNumber, Client accountHolder, long balance) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.balance = balance;
		cards = new HashMap<shot, AtmCard>;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public long getBalance() {
		return balance;
	}

	public long incrBalance(long amount) {
		// Overflow check
		if (balance > (Long.MAX_VALUE / 2) && amount > (Long.MAX_VALUE / 2)) {
			throw new IllegalStateException("Account balance overflow");
		}

		balance += amount;
		return balance;
	}

	public long decrBalance(long amount) {
		// Negative balance check
		if (balance - amount < 0) {
			throw new IllegalStateException("Insufficient funds");
		}

		balance -= amount;
		return balance;
	}

	public HashMap<Integer, AtmCard> getCards() {
		return cards;
	}

	public boolean addCard(short cardNum, AtmCard card) {
		// Check if cards already contains card
		if (cards.containsValue(card)) {
			throw new IllegalArgumentException("Account already has card");
			return false;
		}

		// Check if cards already contains card
		if (cards.containsKey(cardNum)) {
			throw new IllegalArgumentException("Account already has card number");
			return false;
		}

		cards.put(cardNum, card);
		return true;
	}

	public AtmCard removeCard(short cardNum) {
		// Check if cards contains cardNum
		if (!cards.containsKey(cardNum)) {
			throw new IllegalArgumentException("Account does not have card number");
			return NULL;
		}

		AtmCard removed = cards.get(cardNum);
		cards.remove(cardNum);
		return removed;
	}
}
