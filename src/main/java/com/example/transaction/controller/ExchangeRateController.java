package com.example.transaction.controller;

import com.example.transaction.dto.ExchangeRateDTO;
import com.example.transaction.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exchange-rates")
public class ExchangeRateController {

//    @Autowired
//    private ExchangeRateService exchangeRateService;

//    @PostMapping
//    public ResponseEntity<ExchangeRateDTO> createExchangeRate(@RequestBody ExchangeRateDTO exchangeRateDTO) {
//        ExchangeRateDTO savedExchangeRate = exchangeRateService.saveExchangeRate(exchangeRateDTO);
//        return ResponseEntity.ok(savedExchangeRate);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ExchangeRateDTO>> getAllExchangeRates() {
//        List<ExchangeRateDTO> exchangeRates = exchangeRateService.getAllExchangeRates();
//        return ResponseEntity.ok(exchangeRates);
//    }
//
//    @GetMapping("/{currencyPair}")
//    public ResponseEntity<ExchangeRateDTO> getLatestExchangeRate(@PathVariable String currencyPair) {
//        ExchangeRateDTO exchangeRate = exchangeRateService.getLatestExchangeRate(currencyPair);
//        return ResponseEntity.ok(exchangeRate);
//    }
}
