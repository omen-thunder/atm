package KingsATM.dto;

import KingsATM.Role;
import KingsATM.model.Account;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDtoRes {

    private Integer id;

    private Long balance;

    private Role role;

    private Set<CardDtoRes> cards = new HashSet<>();

    public AccountDtoRes(Account account) {
        this.id = account.getId();
        this.balance = account.getBalance();
        this.role = account.getRole();
        if (account.getCards() != null) {
            this.cards = account.getCards().stream().map(CardDtoRes::new).collect(Collectors.toSet());
        }
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public java.lang.Integer getId() {
        return id;
    }

    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Set<CardDtoRes> getCards() {
        return cards;
    }

    public void setCards(Set<CardDtoRes> cards) {
        this.cards = cards;
    }
}
