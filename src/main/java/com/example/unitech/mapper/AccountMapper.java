package com.example.unitech.mapper;

import com.example.unitech.dao.entity.AccountEntity;
import com.example.unitech.dao.entity.UserEntity;
import com.example.unitech.model.request.AccountRequest;
import com.example.unitech.model.response.AccountResponse;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.unitech.model.enums.AccountStatus.ACTIVE;

public enum AccountMapper {
    ACCOUNT_MAPPER;

    public AccountEntity buildAccountEntity(AccountRequest accountRequest, UserEntity userEntity) {
        return AccountEntity.builder()
                .accountNumber(accountRequest.getAccountNumber())
                .balance(accountRequest.getBalance())
                .status(ACTIVE)
                .user(userEntity)
                .build();
    }

    public AccountResponse buildAccountResponse(AccountEntity accountEntity) {
        return AccountResponse.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .balance(accountEntity.getBalance())
                .status(accountEntity.getStatus())
                .build();
    }

    public List<AccountResponse> buildAccountResponses(List<AccountEntity> accountEntities) {
        return accountEntities.stream()
                .map(ACCOUNT_MAPPER::buildAccountResponse)
                .collect(Collectors.toList());

    }
}
