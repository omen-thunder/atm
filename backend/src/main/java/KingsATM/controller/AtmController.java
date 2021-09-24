package KingsATM.controller;

import KingsATM.dto.CashStoreDto;

import KingsATM.model.Cash;
import KingsATM.model.Transaction;
import KingsATM.model.TransactionType;
import KingsATM.service.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/atm")
public class AtmController {

    @Autowired
    CashService cashService;

    @GetMapping("/get-cashstore")
    public JsonResponse<CashStoreDto> getCashStore() {
        return new JsonResponse<>(cashService.getAmountNotes());
    }

    @GetMapping("/get-cashstore-balance")
    public JsonResponse<Long> getCashStoreBalance() {
        return new JsonResponse<>(cashService.getTotal());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deposit")
    public JsonResponse<CashStoreDto> deposit(@RequestBody CashStoreDto cashStoreDto) {

        var cashList = cashStoreDto.getListOfCash();
        cashService.deposit(cashList);

        return new JsonResponse<>(cashService.getAmountNotes());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/balance")
    public JsonResponse<CashStoreDto> details() {

        return new JsonResponse<>(cashService.getAmountNotes());
    }

}
