package KingsATM;

import java.util.TreeMap;

public interface AtmCashStore {

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

public class CashStore implements AtmCashStore{
	private TreeMap<short, CashStack> cash;

	public CashStore() {
		cash = new TreeMap<short, CashStack>;
	}

	public long getTotalStoreValue() {
		long total = 0;
		Iterator keys = cash.keySet().iterator();
		keys.forEachRemaining(key -> total += cash.get(key).getTotalValue());
		return total;
	}
}
