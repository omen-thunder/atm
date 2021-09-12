package KingsATM.dto;

import KingsATM.model.Account;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDto {

    private Integer id;

    private Long balance;

    private Set<CardDto> cards = new HashSet<>();

    public AccountDto(Account account) {
        this.id = account.getId();
        this.balance = account.getBalance();
        if (account.getCards() != null) {
            this.cards = account.getCards().stream().map(CardDto::new).collect(Collectors.toSet());
        }
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

    public Set<CardDto> getCards() {
        return cards;
    }

    public void setCards(Set<CardDto> cards) {
        this.cards = cards;
    }
}
