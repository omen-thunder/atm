package KingsATM;

import java.util.Map;
import java.util.HashMap;
import java.lang.Exception;
import java.math.BigInteger;

public class Account {
	private int accountNumber;
	private Client accountHolder;
	private long balance;
	private Map<Integer, AtmCard> cards;

	public Account (int accountNumber, Client accountHolder) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.balance = 0;
		cards = new HashMap<Integer, AtmCard>();
	}

	public Account (int accountNumber, Client accountHolder, long balance) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.balance = balance;
		cards = new HashMap<Integer, AtmCard>();
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
		// BigInteger sum = BigInteger.valueOf(this.balance).add(BigInteger.valueOf(amount));
		// if (sum.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) == -1) {
		// 	throw new IllegalStateException("Account balance overflow");
		// }

		if (Long.MAX_VALUE - this.balance < amount) {
			throw new IllegalStateException("Account balance overflow");
		}

		this.balance += amount;
		return this.balance;
	}

	public long decrBalance(long amount) {
		// Negative amount check
		if (amount < 0) {
			throw new IllegalArgumentException("Amount negative");
		}

		// Negative balance check
		if (this.balance - amount < 0) {
			throw new IllegalStateException("Insufficient funds");
		}

		this.balance -= amount;
		return this.balance;
	}

	public Map<Integer, AtmCard> getCards() {
		return cards;
	}

	public void addCard(int cardNum, AtmCard card) {
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

	public AtmCard removeCard(int cardNum) {
		// Check if cards contains cardNum
		if (!cards.containsKey(cardNum)) {
			throw new IllegalArgumentException("Account does not have card number");
		}

		AtmCard removed = cards.get(cardNum);
		cards.remove(cardNum);
		return removed;
	}
}
