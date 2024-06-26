package com.example.unitech.service.abstraction;

import com.example.unitech.model.enums.AccountStatus;
import com.example.unitech.model.request.AccountRequest;
import com.example.unitech.model.response.AccountResponse;

import java.util.List;

public interface AccountService {
    void createAccount(AccountRequest accountRequest);

    List<AccountResponse> allActiveAccounts(Long userId);

    void changeAccountStatus(Long id, AccountStatus accountStatus);
}
