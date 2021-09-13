package KingsATM.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deposit/{amount}")
    public JsonResponse<String> deposit(@PathVariable("amount") Long amount) {
        return new JsonResponse<>("Depositing -- $" + amount.toString());
    }

}
