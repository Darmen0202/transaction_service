package com.example.transaction.repository;

import com.example.transaction.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LimitRepository extends JpaRepository<Limit, Long> {
    Limit findByExpenseCategory(String expenseCategory);

    Limit findByAccountId(Long accountId);
}
