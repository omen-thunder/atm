package KingsATM;

import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Iterator;

public class CashStore {
	private NavigableMap<Short, Integer> cash;

	public CashStore() {
		cash = newStore();
	}

	protected NavigableMap<Short, Integer> newStore() {
		NavigableMap<Short, Integer> store = new TreeMap<Short, Integer>();
		store.put((short) 5, 0);
		store.put((short) 10, 0);
		store.put((short) 20, 0);
		store.put((short) 50, 0);
		store.put((short) 100, 0);
		return store;
	}

	public int addNotes(short value, int quantity) {
		// Negative amount check
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity negative");
		}

		// Note value check
		if (!cash.containsKey(value)) {
			throw new IllegalArgumentException("Note value illegal");
		}

		// Overflow check
		long sum = cash.get(value) + quantity;
		if (sum > Integer.MAX_VALUE) {
			throw new IllegalStateException("Account balance overflow");
		}

		cash.put(value, cash.get(value) + quantity);
		return cash.get(value);
	}

	public int withdrawNotes(short value, int quantity) {
		// Negative amount check
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity negative");
		}

		// Note value check
		if (!cash.containsKey(value)) {
			throw new IllegalArgumentException("Note value illegal");
		}

		// Negative store check
		if (cash.get(value) - quantity < 0) {
			throw new IllegalStateException("Insufficient notes in store");
		}

		cash.put(value, cash.get(value) - quantity);
		return cash.get(value);
	}

	public int getNoteQuantity(short value) {
		// Note value check
		if (!cash.containsKey(value)) {
			throw new IllegalArgumentException("Note value illegal");
		}

		return cash.get(value);
	}

	public long getTotalValue() {
		long total = 0;
		Iterator keys = cash.keySet().iterator();
		while (keys.hasNext()) {
			Short key = (Short) keys.next();
			total += key * cash.get(key);
		}
		return total;
	}

	public NavigableMap<Short, Integer> withdraw(long amount) {
		NavigableMap<Short, Integer> withdrawl = newStore();
		Iterator<Short> keys = cash.descendingKeySet().descendingIterator();
		while (keys.hasNext()) {
			Short key = (Short) keys.next();
			while (key < amount && cash.get(key) > 0) {
				withdrawl.put(key, withdrawl.get(key) + 1);
				cash.put(key, cash.get(key) - 1);
				amount -= key;
			}
			
		}

		return withdrawl;
	}
}