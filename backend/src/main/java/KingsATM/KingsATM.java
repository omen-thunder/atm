package KingsATM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.awt.geom.AreaOp;

import java.util.HashMap;
import java.util.SortedMap;

@SpringBootApplication
public class KingsATM {
    private HashMap<Integer, Account> accounts;
    private HashMap<Integer, AtmCard> cards;
    private SortedMap<Long, Transaction> transactions;
    private int machineNumber;
    private int lastCardNumber;
    private int lastAccountNumber;
    private Long lastTransactionNumber;
    private AtmCard currentCard;
    private CashStore cashStore;

    public KingsATM() {
        this.accounts = new HashMap<Integer, Account>();
        this.cards = new HashMap<Integer, AtmCard>();
        this.transactions = new SortedMap<Long, Transaction>();
    }

    /**
     * Get an Account using a given accountNumber
     * @param accountNumber
     * @return Account
     */
    public Account getAccount(int accountNumber) {
        return this.accounts.get(accountNumber);
    }
    /**
     * Add a new Account to the system
     * @param accountToAdd
     * @return True if Account was successfully added
     */
    public boolean addAccount(Account accountToAdd) {
        if (accountToAdd == null)
            return false;
        if (this.accounts.put(++this.lastAccountNumber, accountToAdd) != null)
            return false;

        return true;
    }
    /**
     * Remove an Account from the system
     * @param accountNumber
     * @return The removed Account
     */
    public Account removeAccount(int accountNumber) {
        return this.accounts.remove(accountNumber);
    }

    /**
     * Get an AtmCard using a given cardNumber
     * @param cardNumber
     * @return AtmCard
     */
    public AtmCard getAtmCard(int cardNumber) {
        return this.cards.get(cardNumber);
    }
    /**
     * Add an AtmCard to the system
     * @param cardToAdd
     * @return True if AtmCard was successfully added
     */
    public boolean AtmCard(AtmCard cardToAdd) {
        if (cardToAdd == null)
            return false;
        if (this.cards.put(++this.lastCardNumber, cardToAdd) != null)
            return false;

        return true;
    }
    /**
     * Remove an AtmCard from the system
     * @param cardNumber
     * @return The removed AtmCard
     */
    public AtmCard removeAtmCard(int cardNumber) {
        return this.cards.remove(cardNumber);
    }

    /**
     * Add a Transaction to the system
     * @param transactionToAdd
     * @return True if Transaction was successfully added
     */
    public boolean addTransaction(Transaction transactionToAdd) {
        if (transactionToAdd == null)
            return false;
        if (this.transactions.put(++this.lastTransactionNumber, transactionToAdd) != null)
            return false;

        return true;
    }

    private void loadDB() {
        // Dont know what to do here
    }
    private void saveDB() {
        // Dont know what to do here
    }

    /**
     * Check validity of card
     * @param cardNumber
     * @return AtmCard if card is valid
     */
    public AtmCard verifyCard(int cardNumber) {
        AtmCard cardToVerify = this.cards.get(cardNumber);
        if (cardToVerify == null)
            return null;
        if (cardToVerify.isExpired())
            return null;
        if (cardToVerify.getStatus().equals("banned")
        || cardToVerify.getStatus().equals("lost")
        || cardToVerify.getStatus().equals("confiscated"))
            return null;

        return cardToVerify;
    }

    /**
     * Insert AtmCard into machine (Only call this method on a verified card)
     * @param cardToInsert
     * @return True if AtmCard successfully added
     */
    public boolean insertCard(AtmCard cardToInsert) {
        if (cardToInsert == null)
            return false;

        this.currentCard = cardToInsert;
        return true;
    }

    /**
     * Eject AtmCard from machine
     * @return True if AtmCard successfully ejected
     */
    public boolean ejectCard() {
        if (this.currentCard == null)
            return false;
        this.currentCard = null;
        return true;
    }

    /**
     * Withdraw given amount from Account linked to currently inserted card
     * @param amount
     * @return True if given amount was successfully withdrawn
     */
    public boolean withdraw(long amount) {
        if (currentCard == null)
            return false;
        Account linkedAccount = currentCard.getLinkedAccount();
        if (linkedAccount.getBalance() - amount < 0)
            return false;

        linkedAccount.decrBalance(amount);
        this.accounts.put(linkedAccount.getAccountNumber(), linkedAccount);
        return true;
    }
    /**
     * Deposit given amount from Account linked to currently inserted card
     * @param amount
     * @return True if given amount was successfully deposited
     */
    public boolean deposit(long amount) {
        if (currentCard == null)
            return false;
        if ((amount % 5) != 0)
            return false;

        Account linkedAccount = currentCard.getLinkedAccount();
        linkedAccount.incrBalance(amount);
        this.accounts.put(linkedAccount.getAccountNumber(), linkedAccount);
        return true;
    }
    /**
     * Check balance of currently inserted card
     * @return Account balance
     */
    public long checkBalance() {
        if (currentCard == null)
            return -1;
        return this.currentCard.getLinkedAccount().getBalance();
    }

    public static void main(String[] args) {
        SpringApplication.run(KingsATM.class, args);
    }
}
