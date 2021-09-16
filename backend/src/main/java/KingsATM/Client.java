package KingsATM;

import java.util.ArrayList;

public class Client {
    private int clientNumber;
    private String givenName;
    private String surname;
    private ArrayList<Account> accounts;

    public Client(int clientNumber, String givenName, String surname) {
        this.clientNumber = clientNumber;
        this.givenName = givenName;
        this.surname = surname;
        this.accounts = new ArrayList<Account>();
    }
    /**
     * Get all the accounts owned by the client.
     * @return ArrayList of accounts.
     */

    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }

    /**
     * Get the client's given name.
     * @return String
     */
    public String getGivenName() {
        return this.givenName;
    }

    /**
     * Get the client's surname.
     * @return String
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Get the client number.
     * @return Integer
     */
    public int getClientNumber() {
        return this.clientNumber;
    }

}