package KingsATM.controller;


import KingsATM.dto.AccountDto;
import KingsATM.model.Account;
import KingsATM.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/details")
    public ResponseEntity<AccountDto> account(Authentication authentication) {
        Optional<Account> optionalAccount = accountService.getAccountById(Integer.parseInt(authentication.getName()));

        if (optionalAccount.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            var accountDto = new AccountDto(optionalAccount.get());

            return ResponseEntity.ok(accountDto);
        }
    }

}
