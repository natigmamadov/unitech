package com.example.unitech.service.abstraction;

import com.example.unitech.model.request.TransferRequest;

public interface TransactionService {
    void moneyTransfer(TransferRequest transferRequest);
}
