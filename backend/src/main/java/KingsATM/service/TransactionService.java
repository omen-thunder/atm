package KingsATM.service;

import KingsATM.model.TransactionType;
import KingsATM.model.Account;
import KingsATM.model.Card;
import KingsATM.model.Transaction;
import KingsATM.respository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction getTransactionById(Integer id) {
        var optionalTransaction = transactionRepository.findById(id);

        if (optionalTransaction == null) {
            throw new EntityNotFoundException("Transaction by user with id "+ id + " not found");
        }

        return optionalTransaction.get();
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction createTransaction(TransactionType type, Long amount, Account account, Card card) {
        Transaction transaction = new Transaction (
                type,
                amount,
                account.getBalance(),
                account.getId(),
                card.getId()
        );
        return this.saveTransaction(transaction);
    }
}
