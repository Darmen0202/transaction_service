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
        limit.setLimitDatetime(ZonedDateTime.now());
        limit.setLimitCurrencyShortname("USD");
        Limit savedLimit = limitRepository.save(limit);
        return limitMapper.toDto(savedLimit);
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void resetMonthlyLimit(){
        List<Limit> limits = limitRepository.findAll();
        for(Limit limit: limits) {
            limit.setLimitSum(new BigDecimal("1000.00"));
            limit.setLimitCurrencyShortname("USD");
            limit.setLimitDatetime(ZonedDateTime.now());
            limitRepository.save(limit);
        }
    }
}
