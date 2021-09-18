package KingsATM.model;

import KingsATM.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;
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

    @ManyToOne(optional = false)
    @NotNull
    private Client client;

    protected Account() {}

    public Account(Role role) {
        this.role = role;
    }

    public Account(Long balance, Set<Card> cards, Role role, Client client) {
        this.balance = balance;
        this.cards = cards;
        this.role = role;
        this.client = client;
    }

    public Account(Long balance, Set<Card> cards, Client client) {
        this.balance = balance;
        this.cards = cards;
        this.client = client;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addCard(Card card) {
        // Check if cards already contains card
        if (!cards.isEmpty()) {
            throw new IllegalArgumentException("Account already has card");
        }

        // Check if cards already contains card
        if (cards.contains(card)) {
            throw new IllegalArgumentException("Account already has card number");
        }

        cards.add(card);
    }

    public Card removeCard(Card card) {

        var optionalCard = cards
                .stream()
                .filter(c -> c.getId().equals(card.getId()))
                .findFirst();

        // Check if cards contains cardNum
        if (optionalCard == null) {
            throw new IllegalArgumentException("Account does not have card number");
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

        // Overflow check
        BigInteger sum = BigInteger.valueOf(balance).add(BigInteger.valueOf(amount));
        if (sum.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) == -1) {
            throw new IllegalStateException("Account balance overflow");
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
