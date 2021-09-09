package KingsATM;

public interface ATM {

    /**
     * Add a client to the system.
     * @param client
     * @return True if successful.
     */
    public boolean addClient(Client client);

    /**
     * Create a new client.
     * @param givenName
     * @param surname
     * @return New client
     */
    public Client createClient(String givenName, String surname);

    /**
     * Get a client with a given clientNumber
     * @param clientNumber
     * @return Client
     */
    public Client getClient(int clientNumber);

    /**
     * Remove a client from the system.
     * @param client
     * @return Removed client
     */
    public Client removeClient(Client client);


    /**
     * Add an account to the system.
     * @param account
     * @return True, if successful.
     */
    public boolean addAccount(Account account);

    /**
     * Create a new account for a client.
     * @param client
     * @return New Account
     */
    public Account createAccount(Client client);

    /**
     * Get an account with a given accountNumber.
     * @param accountNumber
     * @return Account
     */
    public Account getAccount(int accountNumber);

    /**
     * Remove an account from the system.
     * @param account
     * @return Removed Account
     */
    public Account removeAccount(Account account);


    /**
     * Add an ATM card to the system.
     * @param card
     * @return True, if successful.
     */
    public boolean addAtmCard(AtmCard card);

    /**
     * Create a new ATM card. It must be associated with a client and linked to an account.
     * @param client
     * @param account
     * @return New AtmCard
     */
    public AtmCard createAtmCard(Client client, Account account);

    /**
     * Get an AtmCard with a given cardNumber.
     * @param cardNumber
     * @return AtmCard
     */
    public AtmCard getAtmCard(int cardNumber);

    /**
     * Remove an AtmCard fomr the system.
     * @param card
     * @return Remvoed AtmCard
     */
    public AtmCard removeAtmCard(AtmCard card);


    /**
     * Add a transaction to the system
     * @param tx
     * @return True, if successful.
     */
    public boolean addTransaction(Transaction tx);

    /**
     * Remove a transaction from the system.
     * @param tx
     * @return Removed Transaction
     */
    public Transaction removeTransaction(Transaction tx);

    /**
     * Get a transaction with a given transaction number.
     * @param txNumber
     * @return Transaction
     */
    public Transaction getTransaction(long txNumber);

    /**
     * Load records from an external DB into the system.
     * @return
     */
    public boolean loadDB();

    /**
     * Save records in the system to an external DB.
     * @return
     */
    public boolean saveDB();


    /**
     * Given a card, verify the card based on card status. The card should be inserted into the machine already.
     * @param card
     * @return
     */
    public boolean verifyCard(AtmCard card);

    /**
     * Insert a card into the machine. Any action with the card should be performed after the card is inserted. 
     * Cannot insert more than one card at a time.
     * @param card
     * @return
     */
    public boolean insertCard(AtmCard card);

    /**
     * Eject card from the ATM. Can only eject a card if there is a card inserted. Do not eject a confiscated card.
     * @return True, if successful.
     */
    public boolean ejectCard() throws IllegalStateException;

    /**
     * Check card balance. Throw IllegalStateException if no card is inserted.
     * @return Associated account balance.
     * @throws IllegalStateException
     */
    public long checkBalance() throws IllegalStateException;

    /**
     * Withdraw money from the account.
     * @param amount
     * @return True, if the action is successful.
     */
    public boolean withdraw(long amount) throws IllegalStateException;

    /**
     * Deposit money into the account.
     * @param amount
     * @return True, if the action is successful.
     */
    public boolean deposit(long amount) throws IllegalStateException;


}
