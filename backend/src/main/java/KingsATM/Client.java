package KingsATM;

import java.util.ArrayList;

public interface Client {

    /**
     * Get all the accounts owned by the client.
     * @return ArrayList of arrounts.
     */
    public ArrayList<Account> getAccounts();

    /**
     * Get the client's given name.
     * @return String
     */
    public String getGivenName();

    /**
     * Get the client's surname.
     * @return String
     */
    public String getSurname();

    /**
     * Get the client number.
     * @return Integer
     */
    public int getClientNumber();
    
}
