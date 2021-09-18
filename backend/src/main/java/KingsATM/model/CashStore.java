package KingsATM.model;

import javax.persistence.*;
import java.util.Iterator;
import java.util.TreeMap;


@Entity
@Table(name = "cash_store")
public class CashStore {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    enum AusCashNote {
        FIVE,
        TEN,
        TWENTY,
        FIFTY,
        ONE_HUNDRED
    }

    private Integer fiveDollarAmount = 0;
    private Integer tenDollarAmount = 0;
    private Integer twentyDollarAmount = 0;
    private Integer fiftyDollarAmount = 0;
    private Integer oneHundredDollarAmount = 0;

    public CashStore() {
    }

    public Integer getNoteCount(AusCashNote note) {
        return switch (note) {
            case FIVE -> fiveDollarAmount;
            case TEN -> tenDollarAmount;
            case TWENTY -> twentyDollarAmount;
            case FIFTY -> fiftyDollarAmount;
            case ONE_HUNDRED -> oneHundredDollarAmount;
        };
    }

    public Long addNotes(AusCashNote note, int quantity) {
        // Negative amount check
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity negative");
        }

        // Overflow check
        // long sum = cash.get(value) + quantity;
//        if (Integer.MAX_VALUE - cash.get(value) < quantity) {
//            throw new IllegalStateException("Account balance overflow");
//        }

        switch (note) {
            case FIVE -> fiveDollarAmount += quantity;
            case TEN -> tenDollarAmount += quantity;
            case TWENTY -> twentyDollarAmount += quantity;
            case FIFTY -> fiftyDollarAmount += quantity;
            case ONE_HUNDRED -> oneHundredDollarAmount += quantity;
        }

        return getNoteValue(note);
    }

    public long getNoteValue(AusCashNote note) {
        return switch (note) {
            case FIVE -> fiveDollarAmount * 5;
            case TEN -> tenDollarAmount * 10;
            case TWENTY -> twentyDollarAmount * 20;
            case FIFTY -> fiftyDollarAmount * 50;
            case ONE_HUNDRED -> oneHundredDollarAmount * 100;
        };
    }

    public long withdrawNotes(AusCashNote note, int quantity) throws IllegalStateException {
        // Negative amount check
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity negative");
        }

        // Negative store check
        if (getNoteCount(note) - quantity < 0) {
            throw new IllegalStateException("Insufficient notes in store");
        }

        switch (note) {
            case FIVE -> fiveDollarAmount -= quantity;
            case TEN -> tenDollarAmount -= quantity;
            case TWENTY -> twentyDollarAmount -= quantity;
            case FIFTY -> fiftyDollarAmount -= quantity;
            case ONE_HUNDRED -> oneHundredDollarAmount -= quantity;
        }

        return getNoteValue(note);
    }

    public long getTotalValue() {
        long total = 0;
        for (AusCashNote note: AusCashNote.values()) {
            total += getNoteValue(note);
        }
        return total;
    }

    public boolean deduct(long amount) {

        //TODO - write deduct function.
        return true;
    }
}
