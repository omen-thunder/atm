package KingsATM.controller;

import KingsATM.dto.CashStoreDto;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

@RestController
@RequestMapping("api/atm")
public class AtmController {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deposit")
    public JsonResponse<String> deposit(@RequestBody CashStoreDto notes) {

        // TODO: Get notes from request body and make deposit

        // TODO: Get updated cash store and return stringified JSON
        JSONObject cash = new JSONObject();
        cash.put("n5", 100);
        cash.put("n10", 100);
        cash.put("n20", 100);
        cash.put("n50", 100);
        cash.put("n100", 100);

        return new JsonResponse<String>(cash.toString());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/balance")
    public JsonResponse<CashStoreDto> details() {
        return new JsonResponse<>(new CashStoreDto());
    }

}
