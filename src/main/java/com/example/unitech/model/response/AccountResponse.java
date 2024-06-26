package com.example.unitech.model.response;

import com.example.unitech.model.enums.AccountStatus;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponse {

    private String accountNumber;
    private AccountStatus status;
    private BigDecimal balance;

}