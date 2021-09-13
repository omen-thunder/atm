package KingsATM.dto;

import KingsATM.Role;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
This class is used by the frontend to request a new account
This contains fields that are required by the user.
 */
public class AccountDtoReq {

    private Long balance;

    private ArrayList<CardDtoReq> cards;

    protected AccountDtoReq() {}

    public AccountDtoReq(Long balance, ArrayList<CardDtoReq> cards) {
        this.balance = balance;
        this.cards = cards;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public ArrayList<CardDtoReq> getCards() {
        return cards;
    }

    public void setCards(ArrayList<CardDtoReq> cards) {
        this.cards = cards;
    }
}
