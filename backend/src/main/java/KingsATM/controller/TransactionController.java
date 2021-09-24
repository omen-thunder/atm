package KingsATM.controller;

import KingsATM.model.TransactionType;
import KingsATM.dto.CashStoreDto;
import KingsATM.model.Transaction;
import KingsATM.dto.AccountDtoRes;
import KingsATM.service.AccountService;
import KingsATM.service.CardService;
import KingsATM.service.CashService;
import KingsATM.service.TransactionService;

import KingsATM.model.Cash;

// import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {
    @Autowired
    CardService cardService;

    @Autowired
    AccountService accountService;

    @Autowired
    CashService cashService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    EntityManager entityManager;

    @PostMapping("/withdraw/{amount}")
    public JsonResponse<Transaction> withdraw(Authentication auth, @PathVariable Long amount) {
        var account = accountService.getAccountByCardId(Integer.parseInt(auth.getName()));
        var card = cardService.getCardById(Integer.parseInt(auth.getName()));

        List<Cash> cashList = cashService.withdraw(amount);
        Long withdrawnAmount = cashService.getTotal(cashList);

        Long newBalance = account.decrBalance(withdrawnAmount);
        accountService.saveAccount(account);

        Transaction transaction = transactionService.createTransaction (
                TransactionType.WITHDRAW, withdrawnAmount, account, card);

        return new JsonResponse<>(transaction);

    }

    @PostMapping("/deposit")
    public JsonResponse<Transaction> deposit(Authentication auth, @RequestBody CashStoreDto cashStoreDto) {

        var account = accountService.getAccountByCardId(Integer.parseInt(auth.getName()));
        var card = cardService.getCardById(Integer.parseInt(auth.getName()));
        var cashList = cashStoreDto.getListOfCash();
        var addedTotal = cashService.getTotal(cashList);

        account.incrBalance(addedTotal);
        cashService.deposit(cashList);
        accountService.saveAccount(account);
        Transaction transaction = transactionService.createTransaction (TransactionType.DEPOSIT, addedTotal, account, card);

        return new JsonResponse<Transaction>(transaction);
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