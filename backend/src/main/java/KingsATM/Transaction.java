package KingsATM;

import java.time.LocalDateTime;

public interface Transaction {

    /**
     * Get client number.
     * @return Integer
     */
    public int getClientNumber();

    /**
     * Get account number.
     * @return Integer
     */
    public int getAccountNumber();

    /**
     * Get card number.
     * @return Integer
     */
    public int getCardNumber();

    /**
     * Get date and time.
     * @return LocalDateTime
     */
    public LocalDateTime getDateTime();

    /**
     * Get transaction ammount.
     * @return Long
     */
    public long getAmount();
    
}
