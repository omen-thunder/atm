package KingsATM.controller;

import KingsATM.CashStore;
import KingsATM.model.Atm;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/atm")
public class AtmController {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deposit/{amount}")
    public JsonResponse<String> depositFund(@PathVariable("amount") Long amount) {

        // TODO: Update ATM cash store DB
        return new JsonResponse<>("Depositing -- $" + amount.toString());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deposit")
    public JsonResponse<String> deposit(@RequestBody CashStore notes) {

        // TODO: Update ATM cash store DB

        return new JsonResponse<>("OK");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/details/{machineId}")
    public JsonResponse<Atm> details(@PathVariable("machineId") Integer machineId) {
        return new JsonResponse<>(new Atm());
    }

}
