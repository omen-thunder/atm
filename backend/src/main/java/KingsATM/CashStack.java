package KingsATM;

public interface CashStack {

    /**
     * Get the value of a single note in this cash stack.
     * @return long
     */
    public long getValue();

    /**
     * Get the number of notes in this stack.
     * @return long
     */
    public long getQuantity();

    /**
     * Get the total value of this stack.
     * @return
     */
    public long getTotalValue();

    /**
     * Withdraw notes from the stack.
     * @param count The number of notes.
     * @return True, if there is enough notes in the stack.
     */
    public boolean withdrawNotes(long count);

    /**
     * Deposit notes into the stack.
     * @param count The number of notes.
     * @return Always return True.
     */
    public boolean depositNotes(long count);


    
}
