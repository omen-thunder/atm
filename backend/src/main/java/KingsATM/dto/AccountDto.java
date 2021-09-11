package KingsATM.dto;

import KingsATM.model.Account;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDto {

    private Integer id;

    private Long balance;

    private Set<CardDto> cards;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.balance = account.getBalance();
        this.cards = account.getCards().stream().map(CardDto::new).collect(Collectors.toSet());
    }
}
