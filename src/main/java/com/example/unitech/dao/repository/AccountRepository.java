package com.example.unitech.dao.repository;

import com.example.unitech.dao.entity.AccountEntity;
import com.example.unitech.model.enums.AccountStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<AccountEntity,Long> {

    Optional<AccountEntity> findByAccountNumber(String accountNumber);

    Optional<AccountEntity> findByAccountNumberAndStatus(String accountNumber, AccountStatus status);


    @Query("select a from AccountEntity a where a.user.id=:userId and a.status=:accountStatus")
    List<AccountEntity> allActiveAccountByUserId(Long userId,AccountStatus accountStatus);

}
