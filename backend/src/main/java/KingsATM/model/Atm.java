package KingsATM.model;


import javax.persistence.*;

@Entity
@Table(name = "atm_machine")
public class Atm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;


    @OneToOne
    @JoinColumn(name = "cashstore_id", referencedColumnName = "id")
    private CashStore cashStore;

    //TODO - Remove temporay balance and use the cashstore
    private Long balance;

    public Atm(long id, CashStore cashStore, Long balance) {
        this.id = id;
        this.cashStore = cashStore;
        this.balance = balance;
    }


    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Atm() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CashStore getCashStore() {
        return cashStore;
    }

    public void setCashStore(CashStore cashStore) {
        this.cashStore = cashStore;
    }
}
