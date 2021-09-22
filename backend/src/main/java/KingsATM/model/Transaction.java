package KingsATM.model;

import KingsATM.TransactionType;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "transaction")
@SequenceGenerator(name="transaction_seq", initialValue = 1000)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private Date date;
    private Long amount;
    private Long balance;
    private Integer accountNumber;
    private Integer cardNumber;

    public Transaction(TransactionType type, Long amount, Long balance,
                       Integer accountNumber, Integer cardNumber) {
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.date = Calendar.getInstance().getTime();
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
    }

    protected Transaction() {}

    public Integer getId() { return this.id; }

    public Date getDateTime() {
        return date;
    }

    public TransactionType getType() { return type; }

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
