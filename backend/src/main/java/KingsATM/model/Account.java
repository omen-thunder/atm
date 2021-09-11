package KingsATM.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "account")
@SequenceGenerator(name="account_seq", initialValue = 1000)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    private Integer id;

    private Long balance;

    @OneToMany(mappedBy = "account")
    private Set<Card> cards;

    protected Account() {}

    public Account(Long balance, Set<Card> cards) {
        this.balance = balance;
        this.cards = cards;
    }

    public Account(Integer id, Long balance, Set<Card> cards) {
        this.id = id;
        this.balance = balance;
        this.cards = cards;
    }

    public Account(Integer id, Long balance) {
        this.id = id;
        this.balance = balance;
    }

    public Account(Long balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }


}
