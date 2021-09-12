package KingsATM.controller;


import KingsATM.dto.AccountDto;
import KingsATM.model.Account;
import KingsATM.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/details")
    public JsonResponse<AccountDto> account(Authentication authentication) {
        var optionalAccount = accountService.getAccountById(Integer.parseInt(authentication.getName()));

        if (optionalAccount.isEmpty()) {
            return new JsonResponse<>(false, "No user currently logged in");
        }
        else {
            var accountDto = new AccountDto(optionalAccount.get());

            return new JsonResponse<>(accountDto);
        }
    }

    @PostMapping("/create")
    public JsonResponse<AccountDto> createAccount(@RequestBody Account newAccount) {
        Account account = accountService.saveNewUser(newAccount);

        if (account == null) {
            return new JsonResponse<>(false, "There was an error creating the new account");
        }
        else {
            var accountDto = new AccountDto(account);

            return new JsonResponse<>(accountDto);
        }

    }

}
