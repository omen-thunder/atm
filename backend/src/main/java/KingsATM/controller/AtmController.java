package KingsATM.controller;

import KingsATM.dto.CashStoreDto;

import KingsATM.model.Cash;
import KingsATM.model.Transaction;
import KingsATM.model.TransactionType;
import KingsATM.service.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/atm")
public class AtmController {

    @Autowired
    CashService cashService;


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/deposit")
    public JsonResponse<CashStoreDto> deposit(@RequestBody CashStoreDto cashStoreDto) {

        // TODO: Get notes from request body and make deposit

        // TODO: Get updated cash store and return stringified JSON

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

            cashService.deposit(cashList);

            return new JsonResponse<>(cashService.getAmountNotes());

        } catch (IllegalArgumentException | IllegalStateException e) {
            return new JsonResponse<>(false, e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/balance")
    public JsonResponse<CashStoreDto> details() {

        return new JsonResponse<>(cashService.getAmountNotes());
    }

}
