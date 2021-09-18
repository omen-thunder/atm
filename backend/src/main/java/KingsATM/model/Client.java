package KingsATM.model;

import KingsATM.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "client")
@SequenceGenerator(name = "client_seq", initialValue = 1, allocationSize = 1)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @OneToMany(mappedBy = "client")
    private Set<Account> accounts = new HashSet<>();

    protected Client() {}

    public Client(Role role, String firstName, String lastName) {
        this.role = role;


    }
    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public Integer getId() { return id; }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public Role getRole() {
        return role;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        if (accounts.contains(account)) {
            throw new IllegalArgumentException("Client already has this account number");
        }
        this.accounts.add(account);
    }

    public Account removeAccount(Account account) {
        var optionalAccount = accounts
                .stream()
                .filter(c -> c.getId().equals(account.getId()))
                .findFirst();

        // Check if cards contains cardNum
        if (optionalAccount == null) {
            throw new IllegalArgumentException("Client does not have this account number");
        }

        Account removedAccount = optionalAccount.get();

        accounts.remove(account);

        return removedAccount;
    }
}