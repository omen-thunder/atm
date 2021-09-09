package KingsATM;

import java.util.TreeMap;

public interface CashStore {

    /**
     * Get the total value of all CashStack.
     * @return long
     */
    public long getTotalStoreValue();

    /**
     * Return an array of CashStack that equals to the value requested or the cloest possible value.
     * The total value returned must not be greater than the requested value.
     * @return An array of CashStack
     */
    public TreeMap<Short,CashStack> withdraw(long amount);
    
}
