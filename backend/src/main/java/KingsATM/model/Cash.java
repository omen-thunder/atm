package KingsATM.model;

import javax.persistence.*;

@Entity
@Table(name = "cash")
public class Cash {
	@Id
	private float value;
	private int amount; // number of notes, not the total value of the notes

	public Cash(float value) {
		this.value = value;
		amount = 0;
	}

	public Cash(float value, int amount) {
		this.value = value;
		this.amount = amount;
	}

	public float getValue() {
		return value;
	}
	
	public int getAmount() {
		return amount;
	}

	public float getTotal() {
		return value * amount;
	}

	public int incrAmount(int increase) {
		// Negative amount check
		if (increase < 0) {
			throw new IllegalArgumentException("Quantity negative");
		}

		// Overflow check
		if (Integer.MAX_VALUE - amount < increase) {
			throw new IllegalStateException("Account balance overflow");
		}

		amount += increase;
		return amount;
	}

	public int decrAmount(int decrease) {
		// Negative amount check
		if (decrease < 0) {
			throw new IllegalArgumentException("Quantity negative");
		}

		// Negative store check
		if (amount - decrease < 0) {
			throw new IllegalStateException("Insufficient notes in store");
		}

		amount -= decrease;
		return amount;
	}
}
