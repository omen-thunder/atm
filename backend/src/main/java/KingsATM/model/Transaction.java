package KingsATM.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "transaction")
@SequenceGenerator(name="transaction_seq", initialValue = 1000)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    private long id;

    private Date date;
    private String type;
    private Long amount;
    private Long balance;
    private Integer accountNumber;
    private Integer cardNumber;

    public Transaction( String type, Long amount, Long balance,
                       Integer accountNumber, Integer cardNumber) {
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.date = Calendar.getInstance().getTime();
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
    }

    protected Transaction() {}

    public long getId() { return this.id; }

    public Date getDateTime() {
        return date;
    }

    public String getType() { return type; }

    public long getAmount() {
        return amount;
    }

    public long getBalance() { return balance; }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }
}
