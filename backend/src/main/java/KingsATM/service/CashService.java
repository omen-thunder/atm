package KingsATM.service;

import KingsATM.model.Cash;
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

	private int incrNote(float value, int amount) {
		var stack = cashRepository.findById(value).get();
		
		if (stack == null) {
			throw new EntityNotFoundException("Invalid note value");
		}

		stack.incrAmount(amount);
		cashRepository.save(stack);
		return stack.getAmount();
	}

	private int decrNote(float value, int amount) {
		var stack = cashRepository.findById(value).get();
		
		if (stack == null) {
			throw new EntityNotFoundException("Invalid note value");
		}

		stack.decrAmount(amount);
		cashRepository.save(stack);
		return stack.getAmount();
	}

	public List<Cash> withdraw(float amount) {
		if (amount > this.getTotal()) {
			throw new IllegalArgumentException("Insufficient cash in ATM");
		}
		
		Iterator<Cash> iter = cashRepository.findByOrderByValueDesc().iterator();
		List<Cash> withdrawn = new ArrayList<Cash>();
		while (amount > 0 && iter.hasNext()) {
			Cash stack = iter.next();
			float value = stack.getValue();
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

	public float deposit(List<Cash> deposited) {
		Iterator<Cash> iter = deposited.iterator();
		float total = 0;
		while (iter.hasNext()) {
			Cash stack = iter.next();
			this.incrNote(stack.getValue(), stack.getAmount());
			total += stack.getTotal();
		}

		return total;
	}

	public float getTotal() {
		float total = 0;
		Iterator<Cash> iter = cashRepository.findAll().iterator();
		while (iter.hasNext()) {
			Cash stack = iter.next();
			total += stack.getTotal();
		}

		return total;
	}
}
