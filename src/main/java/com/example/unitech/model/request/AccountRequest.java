package com.example.unitech.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.example.unitech.model.constants.ApplicationConstants.ACCOUNT_NUMBER_IS_REQUIRED;
import static com.example.unitech.model.constants.ApplicationConstants.USER_ID_IS_REQUIRED;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {
    @NotBlank(message = ACCOUNT_NUMBER_IS_REQUIRED)
    private String accountNumber;

    private BigDecimal balance;

    @NotNull(message = USER_ID_IS_REQUIRED)
    private Long userId;

}