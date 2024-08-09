package com.example.transaction.controller;

import com.example.transaction.dto.TransactionDTO;
import com.example.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST-контроллер для управления транзакциями.
 */
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Создает новую транзакцию.
     *
     * @param transactionDTO объект передачи данных транзакции.
     * @вернуть созданную транзакцию.
     */
    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO savedTransaction = transactionService.saveTransaction(transactionDTO);
        return ResponseEntity.ok(savedTransaction);
    }

    /**
     * Возвращает транзакцию, в которой превышен лимит для указанного счета.
     *
     * @param accountId идентификатор учетной записи.
     * @вернуть транзакцию, в которой был превышен лимит.
     */
    @GetMapping("/limitExceeded/{accountId}")
    public ResponseEntity<TransactionDTO> getTransactionLimitExceeded(@PathVariable Long accountId) {
        TransactionDTO exceedTransaction = transactionService.exceedingLimits(accountId);
        return ResponseEntity.ok(exceedTransaction);
    }
}
