package com.example.transaction.repository;

import com.example.transaction.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findTopByCurrencyPairOrderByDateDesc(String currencyPair);

}

