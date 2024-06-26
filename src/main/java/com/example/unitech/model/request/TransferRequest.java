package com.example.unitech.model.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {

    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;

}