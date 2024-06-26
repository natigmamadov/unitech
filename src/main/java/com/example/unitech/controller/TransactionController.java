package com.example.unitech.controller;


import com.example.unitech.model.request.TransferRequest;
import com.example.unitech.service.abstraction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    @ResponseStatus(NO_CONTENT)
    public void moneyTransfer(@RequestBody TransferRequest transferRequest) {
        transactionService.moneyTransfer(transferRequest);
    }

}