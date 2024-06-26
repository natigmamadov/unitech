package com.example.unitech.service.concrete;

import com.example.unitech.dao.entity.AccountEntity;
import com.example.unitech.dao.entity.UserEntity;
import com.example.unitech.dao.repository.AccountRepository;
import com.example.unitech.dao.repository.UserRepository;
import com.example.unitech.exception.AlreadyExistsException;
import com.example.unitech.exception.NotFoundException;
import com.example.unitech.exception.SameStatusException;
import com.example.unitech.model.enums.AccountStatus;
import com.example.unitech.model.request.AccountRequest;
import com.example.unitech.model.response.AccountResponse;
import com.example.unitech.service.abstraction.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.unitech.exception.ExceptionConstants.*;
import static com.example.unitech.mapper.AccountMapper.ACCOUNT_MAPPER;
import static com.example.unitech.model.enums.AccountStatus.ACTIVE;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceHandler implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public void createAccount(AccountRequest accountRequest) {
        log.info("ActionLog.createAccount.start request : {}", accountRequest);
        var userId = accountRequest.getUserId();
        var userEntity = fetchAccountByUserIdIfExists(userId);

        var accountNumber = accountRequest.getAccountNumber();
        Optional<AccountEntity> optionalAccount = checkByAccountNumberIfExists(accountNumber);

        if (optionalAccount.isPresent()) {
            throw new AlreadyExistsException(ACCOUNT_ALREADY_EXISTS_CODE,
                    String.format(ACCOUNT_ALREADY_EXISTS_MESSAGE, accountNumber));
        }

        var accountEntity = ACCOUNT_MAPPER.buildAccountEntity(accountRequest, userEntity);
        log.info("ActionLog.createAccount.success request : {}", accountRequest);
        accountRepository.save(accountEntity);

    }

    @Override
    public List<AccountResponse> allActiveAccounts(Long userId) {
        log.info("ActionLog.allActiveAccounts.start userId : {}", userId);
        fetchAccountByUserIdIfExists(userId);
        List<AccountEntity> accountEntities = accountRepository.allActiveAccountByUserId(
                userId,
                ACTIVE);
        if (accountEntities.isEmpty()) {
            throw new NotFoundException(NO_ACTIVE_ACCOUNT_FOUND_CODE,
                    String.format(NO_ACTIVE_ACCOUNT_FOUND_MESSAGE, userId));
        }
        List<AccountResponse> accountResponses = ACCOUNT_MAPPER.buildAccountResponses(accountEntities);
        return accountResponses;
    }

    @Override
    public void changeAccountStatus(Long id, AccountStatus accountStatus) {
        log.info("ActionLog.changeAccountStatus.start id: {}, accountStatus: {}", id, accountStatus);
        var accountEntity = fetchAccountByIdIfExists(id);

        if (accountEntity.getStatus().equals(accountStatus)) {
            throw new SameStatusException(SAME_STATUS_CODE,
                    String.format(SAME_STATUS_MESSAGE, accountStatus));
        }

        accountEntity.setStatus(accountStatus);
        accountRepository.save(accountEntity);
        log.info("ActionLog.changeAccountStatus.success id: {}, accountStatus: {}", id, accountStatus);

    }


    private UserEntity fetchAccountByUserIdIfExists(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () ->
                        new NotFoundException(USER_NOT_FOUND_CODE,
                                String.format(USER_NOT_FOUND_MESSAGE, userId)));
    }

    private AccountEntity fetchAccountByIdIfExists(Long id) {
        return accountRepository.findById(id).orElseThrow(
                () ->
                        new NotFoundException(ACCOUNT_NOT_FOUND_CODE,
                                String.format(ACCOUNT_NOT_FOUND_MESSAGE, id)));
    }


    private Optional<AccountEntity> checkByAccountNumberIfExists(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
