package com.example.transaction.service;

import com.example.transaction.dto.LimitDTO;
import com.example.transaction.mapper.LimitMapper;
import com.example.transaction.model.Limit;
import com.example.transaction.repository.LimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class LimitService {
    @Autowired
    private LimitRepository limitRepository;

    @Autowired
    private LimitMapper limitMapper;

    public LimitDTO saveLimit(LimitDTO limitDTO) {

        Limit limit = limitMapper.toEntity(limitDTO);
        System.out.println(limit);
        Limit limitEx = limitRepository.findByAccountIdAndExpenseCategory(limit.getAccountId(), limit.getExpenseCategory());
        BigDecimal exLimitSum = limitEx.getLimitSum();
        BigDecimal limitSum = limit.getLimitSum();
        if (limitSum.compareTo(new BigDecimal(1000.00)) > 0) {
            if (exLimitSum.signum() < 0) {
                limitEx.setLimitSum(limit.getLimitSum().add(exLimitSum));
            } else {
                limitEx.setLimitSum(limit.getLimitSum());
            }
        } else {
            limitEx.setLimitSum(limitSum.add(exLimitSum.subtract(new BigDecimal(1000.00))));
        }
        limitEx.setLimitDatetime(ZonedDateTime.now());
        Limit savedLimit = limitRepository.save(limitEx);
        return limitMapper.toDto(savedLimit);
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void resetMonthlyLimit() {
        List<Limit> limits = limitRepository.findAll();
        for (Limit limit : limits) {
            limit.setLimitSum(new BigDecimal("1000.00"));
            limit.setLimitDatetime(ZonedDateTime.now());
            limitRepository.save(limit);
        }
    }
}
