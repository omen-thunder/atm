package KingsATM.model;

import KingsATM.Role;

import javax.persistence.*;
import java.util.HashSet;
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

    public void addNewCard(Card card) {
        this.cards.add(card);
    }
}
