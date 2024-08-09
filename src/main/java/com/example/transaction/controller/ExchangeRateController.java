package com.example.transaction.controller;

import com.example.transaction.dto.ExchangeRateDTO;
import com.example.transaction.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST-контроллер для управления курсами валют.
 */
@RestController
@RequestMapping("/api/exchange-rates")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    /**
     * Извлекает все курсы обмена валют.
     *
     * @вернуть список всех курсов обмена.
     */
    @GetMapping
    public ResponseEntity<List<ExchangeRateDTO>> getAllExchangeRates() {
        List<ExchangeRateDTO> exchangeRates = exchangeRateService.getAllExchangeRates();
        return ResponseEntity.ok(exchangeRates);
    }

    /**
     * Возвращает последний курс обмена для указанной валютной пары.
     *
     * @param currencyPair — валютная пара.
     * @возвращает последний обменный курс для валютной пары.
     */
    @GetMapping("/{currencyPair}")
    public ResponseEntity<ExchangeRateDTO> getLatestExchangeRate(@PathVariable String currencyPair) {
        ExchangeRateDTO exchangeRate = exchangeRateService.getLatestExchangeRate(currencyPair);
        return ResponseEntity.ok(exchangeRate);
    }
}
