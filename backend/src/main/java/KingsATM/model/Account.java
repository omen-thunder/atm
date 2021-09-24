package KingsATM.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Entity
@Table(name = "account")
@SequenceGenerator(name="account_seq", initialValue = 1000)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    private Integer id;

    private Long balance = 0L;

    @OneToMany(mappedBy = "account")
    private Set<Card> cards = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    protected Account() {}

    protected Account(Integer id, Long balance) {
        this.id = id;
        this.balance = balance;
    }

    public Account(Role role) {
        this.role = role;
    }

    public Account(Long balance, Set<Card> cards, Role role) {
        this.balance = balance;
        this.cards = cards;
        this.role = role;
    }

    public Account(Long balance, Set<Card> cards) {
        this.balance = balance;
        this.cards = cards;
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

    // This might be used by JPA to add entries into the database.
    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public Role getRole() {
        return role;
    }

    public void addCard(Card card) {
//        // Check if cards already contains card
//        if (!cards.isEmpty()) {
//            throw new IllegalArgumentException("Account already has card");
//        }

        // Check if cards already contains card
        if (cards.contains(card)) {
            throw new IllegalArgumentException("Account already has this card attached");
        }

        cards.add(card);
    }

    public Card removeCard(Card card) {

        var optionalCard = cards
                .stream()
                .filter(c -> c.getId().equals(card.getId()))
                .findFirst();

        // Check if cards contains cardNum
        if (optionalCard.isEmpty()) {
            throw new NoSuchElementException("Account does not have card number");
        }

        Card removedCard = optionalCard.get();

        cards.remove(card);

        return removedCard;
    }

    public Long incrBalance(Long amount) {
        // Negative amount check
        if (amount < 0) {
            throw new IllegalArgumentException("Amount negative");
        }

        // Coins deposited check
        if (amount % 500 != 0) {
            throw new IllegalArgumentException("Can not deposit coins");
        }

        // Overflow check
        if (Long.MAX_VALUE - balance < amount) {
            throw new IllegalStateException("Balance overflow");
        }

        balance += amount;
        return balance;
    }

    public Long decrBalance(Long amount) {
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
}
