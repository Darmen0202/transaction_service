package com.example.transaction.repository;

import com.example.transaction.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findTopByCurrencyPairOrderByDateDesc(String currencyPair);

    @Query("SELECT e FROM ExchangeRate e WHERE e.currencyPair IN :pairs ORDER BY e.date DESC")
    List<ExchangeRate> findLatestRates(@Param("pairs") List<String> pairs);
}

