package com.example.unitech.controller;


import com.example.unitech.model.enums.AccountStatus;
import com.example.unitech.model.request.AccountRequest;
import com.example.unitech.model.response.AccountResponse;
import com.example.unitech.service.abstraction.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public void createAccount(@RequestBody @Valid AccountRequest accountRequest) {
        accountService.createAccount(accountRequest);
    }

    @GetMapping("/{userId}")
    public List<AccountResponse> allActiveAccounts(@PathVariable Long userId) {
        return accountService.allActiveAccounts(userId);
    }


    @PatchMapping("/change-status/{id}")
    @ResponseStatus(NO_CONTENT)
    public void changeAccountStatus(@PathVariable Long id,
                                    @RequestParam AccountStatus accountStatus) {
        accountService.changeAccountStatus(id, accountStatus);
    }

}