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

    @PostMapping("/deposit")
    public JsonResponse<Transaction> deposit(Authentication auth, @RequestBody CashStoreDto cashStoreDto) {

        var account = accountService.getAccountByCardId(Integer.parseInt(auth.getName()));
        var card = cardService.getCardById(Integer.parseInt(auth.getName()));


        try {
            List cashList = new ArrayList<Cash>();
            Cash num5c = new Cash(5, cashStoreDto.getNum5c());
            Cash num10c = new Cash(10, cashStoreDto.getNum10c());
            Cash num20c = new Cash(20, cashStoreDto.getNum20c());
            Cash num50c = new Cash(50, cashStoreDto.getNum50c());
            Cash num1 = new Cash(100, cashStoreDto.getNum1());
            Cash num2 = new Cash(200, cashStoreDto.getNum2());
            Cash num5 = new Cash(500, cashStoreDto.getNum5());
            Cash num10 = new Cash(1000, cashStoreDto.getNum10());
            Cash num20 = new Cash(2000, cashStoreDto.getNum20());
            Cash num50 = new Cash(5000, cashStoreDto.getNum50());
            Cash num100 = new Cash(10000, cashStoreDto.getNum100());

            cashList.add(num5c);
            cashList.add(num10c);
            cashList.add(num20c);
            cashList.add(num50c);
            cashList.add(num1);
            cashList.add(num2);
            cashList.add(num5);
            cashList.add(num10);
            cashList.add(num20);
            cashList.add(num50);
            cashList.add(num100);

            var addedTotal = cashService.getTotal(cashList);
            account.incrBalance(addedTotal);

            cashService.deposit(cashList);
            accountService.saveAccount(account);

            
            Transaction transaction = transactionService.createTransaction (
                    TransactionType.DEPOSIT, addedTotal, account, card);
            if (transaction == null) {
                return new JsonResponse<>(false, "There was an error creating the new transaction");
            }

            return new JsonResponse<Transaction>(transaction);

        } catch (IllegalArgumentException | IllegalStateException e) {
            return new JsonResponse<Transaction>(null);
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