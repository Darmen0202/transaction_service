package com.example.transaction.service;

import com.example.transaction.dto.TransactionDTO;
import com.example.transaction.mapper.TransactionMapper;
import com.example.transaction.model.Limit;
import com.example.transaction.model.Transaction;
import com.example.transaction.model.ExchangeRate;
import com.example.transaction.repository.LimitRepository;
import com.example.transaction.repository.TransactionRepository;
import com.example.transaction.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private LimitRepository limitRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    public TransactionDTO saveTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        transaction.setLimitExceeded(false);
        BigDecimal inUSD = calculateTransactionInUSD(transaction);
        try {
            Limit limit = limitRepository.findByAccountIdAndExpenseCategory(transaction.getAccountFrom(), transaction.getExpenseCategory());

            if (limit != null && limit.getExpenseCategory().equals(transaction.getExpenseCategory())) {
                limit.setLimitSum(limit.getLimitSum().subtract(inUSD));
                limitRepository.save(limit);
                if (limit.getLimitSum().signum() <= 0) {
                    transaction.setLimitExceeded(true);
                } else {
                    transaction.setLimitExceeded(false);
                }
            } else {
                limit = new Limit();
                limit.setExpenseCategory(transaction.getExpenseCategory());
                limit.setAccountId(transaction.getAccountFrom());
                limit.setLimitDatetime(LocalDate.now().withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()));
                limit.setLimitCurrencyShortname("USD");
                limit.setLimitSum(new BigDecimal("1000.00").subtract(inUSD));
                limitRepository.save(limit);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to update limit" + e);
        }
        transaction.setDatetime(ZonedDateTime.now());
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(savedTransaction);
    }

    public TransactionDTO exceedingLimits(Long accountId) {
        Transaction transaction = transactionRepository.findByAccountFromAndLimitExceeded(accountId, true);
        return transactionMapper.toDto(transaction);
    }

<<<<<<< HEAD
    private BigDecimal calculateTransactionInUSD(Transaction transaction) {
        if ("USD".equals(transaction.getCurrencyShortname())) {
            return transaction.getSum();
        } else {
            String currencyPair = "USD/" + transaction.getCurrencyShortname();
            ExchangeRate exchangeRate = exchangeRateRepository.findTopByCurrencyPairOrderByDateDesc(currencyPair);
            if (exchangeRate != null) {
                BigDecimal amountInUSD = transaction.getSum().divide(exchangeRate.getRate(), RoundingMode.HALF_UP);
                return amountInUSD;
            }
            return BigDecimal.ZERO;
        }
    }
}
=======
}
>>>>>>> 2257a6ef7542784daee48379e1a1893bc936c06b
