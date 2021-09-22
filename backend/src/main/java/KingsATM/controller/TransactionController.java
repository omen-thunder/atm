package KingsATM.controller;

import KingsATM.TransactionType;
import KingsATM.model.Transaction;
import KingsATM.dto.AccountDtoRes;
import KingsATM.service.AccountService;
import KingsATM.service.CardService;
import KingsATM.service.TransactionService;
// import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.Calendar;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {
    @Autowired
    CardService cardService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    EntityManager entityManager;

    @PostMapping("/withdraw/{amount}")
    public JsonResponse<Transaction> withdraw(Authentication auth, @PathVariable Long amount) {
        var account = accountService.getAccountByCardId(Integer.parseInt(auth.getName()));
        var card = cardService.getCardById(Integer.parseInt(auth.getName()));

        try {
            Long newBalance = account.decrBalance(amount);
            accountService.saveAccount(account);

            Transaction transaction = transactionService.createTransaction (
                    TransactionType.WITHDRAW, amount, account, card);

            if (transaction == null) {
                return new JsonResponse<>(false, "There was an error creating the new transaction");
            }

            return new JsonResponse<>(transaction);

        } catch (Exception e) {
            return new JsonResponse<>(false, e.getMessage());
        }

    }

    @PostMapping("/deposit/")
    public JsonResponse<Transaction> deposit(Authentication auth, Long amount) {

        var account = accountService.getAccountByCardId(Integer.parseInt(auth.getName()));
        var card = cardService.getCardById(Integer.parseInt(auth.getName()));

        try {
            Long newBalance = account.incrBalance(amount);
            accountService.saveAccount(account);

            Transaction transaction = transactionService.createTransaction (
                    TransactionType.DEPOSIT, amount, account, card);
            if (transaction == null) {
                return new JsonResponse<>(false, "There was an error creating the new transaction");
            }

            return new JsonResponse<Transaction>(transaction);

        } catch (IllegalArgumentException | IllegalStateException e) {
            return new JsonResponse<Transaction>(null);
        }
    }

    @GetMapping("/balance")
    public JsonResponse<Long> checkBalance(Authentication auth) {
        try {
            var account = accountService.getAccountByCardId(Integer.parseInt(auth.getName()));
            return new JsonResponse<Long>(account.getBalance());
        }
        catch (Exception e) {
            return new JsonResponse<>(false, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public JsonResponse<Transaction> getTransactionById(Authentication auth, @PathVariable Integer id) {
        //TODO- Check that the transaction belongs to user
        try {
            var transaction = transactionService.getTransactionById(id);
            return new JsonResponse<>(transaction);
        }
        catch (Exception e) {
            return new JsonResponse<>(false, e.getMessage());
        }
    }
}