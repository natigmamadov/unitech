package com.example.unitech.service.concrete;

import com.example.unitech.dao.entity.AccountEntity;
import com.example.unitech.dao.repository.AccountRepository;
import com.example.unitech.exception.IllegalArgumentException;
import com.example.unitech.exception.NotFoundException;
import com.example.unitech.model.request.TransferRequest;
import com.example.unitech.service.abstraction.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.unitech.exception.ExceptionConstants.*;
import static com.example.unitech.model.enums.AccountStatus.ACTIVE;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@RequiredArgsConstructor
@Service
@Slf4j
public class TransactionServiceHandler implements TransactionService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional(isolation = REPEATABLE_READ)
    public void moneyTransfer(TransferRequest transferRequest) {
        log.info("ActionLog.moneyTransfer.start request : {}", transferRequest);

        var fromAccount = getAccountOrThrow(transferRequest.getFromAccount());
        var toAccount = getAccountOrThrow(transferRequest.getToAccount());
        if (fromAccount.equals(toAccount)) {
            throw new IllegalArgumentException(SAME_ACCOUNT_CODE, SAME_ACCOUNT_MESSAGE);
        }
        if (fromAccount.getBalance().compareTo(toAccount.getBalance()) < 0) {
            throw new IllegalArgumentException(NO_ENOUGH_MONEY_CODE, NO_ENOUGH_MONEY_MESSAGE);
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(transferRequest.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transferRequest.getAmount()));
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        log.info("ActionLog.moneyTransfer.success request : {}", transferRequest);
    }


    private AccountEntity getAccountOrThrow(String accountNumber) {
        return accountRepository.findByAccountNumberAndStatus(accountNumber, ACTIVE)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND_CODE,
                        String.format(ACCOUNT_NOT_FOUND_MESSAGE, accountNumber)));
    }
}
