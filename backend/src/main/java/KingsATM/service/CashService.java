package KingsATM.service;

import KingsATM.dto.CashStoreDto;
import KingsATM.model.Cash;
import KingsATM.model.CashAmount;
import KingsATM.respository.CashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math;

import javax.persistence.EntityNotFoundException;

@Service
public class CashService {
	@Autowired
	private CashRepository cashRepository;

	private int incrNote(long value, int amount) {
		var stack = cashRepository.findById(value).get();
		
		if (stack == null) {
			throw new EntityNotFoundException("Invalid note value");
		}

		stack.incrAmount(amount);
		cashRepository.save(stack);
		return stack.getAmount();
	}

	private int decrNote(long value, int amount) {
		var stack = cashRepository.findById(value).get();
		
		if (stack == null) {
			throw new EntityNotFoundException("Invalid note value");
		}

		stack.decrAmount(amount);
		cashRepository.save(stack);
		return stack.getAmount();
	}

	public List<Cash> withdraw(long amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount Negative");
		}

		if (amount > this.getTotal()) {
			throw new IllegalArgumentException("Insufficient cash in ATM");
		}

		if (((amount) % 5) != 0) {
			throw new IllegalArgumentException("The amount entered should be divisible by 0.05");
		}
		
		Iterator<Cash> iter = cashRepository.findByOrderByValueDesc().iterator();
		List<Cash> withdrawn = new ArrayList<Cash>();
		while (amount > 0 && iter.hasNext()) {
			Cash stack = iter.next();
			long value = stack.getValue();
			int num = Math.round(amount / value);
			if (num > stack.getAmount()) {
				num = stack.getAmount();
			}

			this.decrNote(value, num);
			withdrawn.add(new Cash(value, num));
			amount -= value * num;
		}

		return withdrawn;
	}

	public long deposit(List<Cash> deposited) {
		Iterator<Cash> iter = deposited.iterator();
		long total = 0;
		while (iter.hasNext()) {
			Cash stack = iter.next();
			this.incrNote(stack.getValue(), stack.getAmount());
			total += stack.getTotal();
		}

		return total;
	}

	public long getTotal() {
		long total = 0;
		Iterator<Cash> iter = cashRepository.findAll().iterator();
		while (iter.hasNext()) {
			Cash stack = iter.next();
			total += stack.getTotal();
		}

		return total;
	}

	public long getTotal(List<Cash> list_cash) {
		long total = 0;
		for (Cash cash : list_cash) {
			total += cash.getAmount() * cash.getValue();
		}

		return total;
	}

	public CashStoreDto getAmountNotes() {
		var cash_list = cashRepository.findAll();
		CashStoreDto cash_store = new CashStoreDto(0,0,0,0,0,0,0,0,0,0,0);

		for (Cash cash : cash_list) {
			cash_store.setNum(CashAmount.getEnum(cash.getValue()), cash.getAmount());
		}
		return cash_store;
	}
}
