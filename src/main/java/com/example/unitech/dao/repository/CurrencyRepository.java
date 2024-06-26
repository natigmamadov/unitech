package com.example.unitech.dao.repository;

import com.example.unitech.dao.entity.CurrencyEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CurrencyRepository extends CrudRepository<CurrencyEntity,Long> {

    List<CurrencyEntity> findByUpdatedOnBefore(LocalDateTime localDateTime);
}
