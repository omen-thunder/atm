package KingsATM;

import java.util.Map;
import java.util.HashMap;
import java.lang.Exception;
import java.math.BigInteger;

public class Account {
	private int accountNumber;
	private Client accountHolder;
	private long balance;
	private Map<Short, AtmCard> cards;

	public Account (int accountNumber, Client accountHolder) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		balance = 0;
		cards = new HashMap<Short, AtmCard>();
	}

	public Account (int accountNumber, Client accountHolder, long balance) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.balance = balance;
		cards = new HashMap<Short, AtmCard>();
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public long getBalance() {
		return balance;
	}

	public long incrBalance(long amount) {
		// Negative amount check
		if (amount < 0) {
			throw new IllegalArgumentException("Amount negative");
		}

		// Overflow check
		BigInteger sum = BigInteger.valueOf(balance).add(BigInteger.valueOf(amount));
		if (sum.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) == -1) {
			throw new IllegalStateException("Account balance overflow");
		}

		balance += amount;
		return balance;
	}

	public long decrBalance(long amount) {
		// Negative amount check
		if (amount < 0) {
			throw new IllegalArgumentException("Amount negative");
		}

		// Negative balance check
		if (balance - amount < 0) {
			throw new IllegalStateException("Insufficient funds");
		}

		balance -= amount;
		return balance;
	}

	public Map<Short, AtmCard> getCards() {
		return cards;
	}

	public void addCard(short cardNum, AtmCard card) {
		// Check if cards already contains card
		if (cards.containsValue(card)) {
			throw new IllegalArgumentException("Account already has card");
		}

		// Check if cards already contains card
		if (cards.containsKey(cardNum)) {
			throw new IllegalArgumentException("Account already has card number");
		}

		cards.put(cardNum, card);
	}

	public AtmCard removeCard(short cardNum) {
		// Check if cards contains cardNum
		if (!cards.containsKey(cardNum)) {
			throw new IllegalArgumentException("Account does not have card number");
		}

		AtmCard removed = cards.get(cardNum);
		cards.remove(cardNum);
		return removed;
	}
}
