package com.example.transaction.repository;

import com.example.transaction.model.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.ZonedDateTime;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDatetimeBetween(ZonedDateTime start, ZonedDateTime end);

    Transaction findByAccountFromAndLimitExceeded(Long accountId, Boolean limitExceeded);
}
