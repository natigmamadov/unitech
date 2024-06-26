package com.example.unitech.dao.repository;

import com.example.unitech.dao.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,Long> {

    Optional<UserEntity> findByPinIgnoreCase(String pin);
}
