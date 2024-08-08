package com.example.transaction.service;

import com.example.transaction.dto.ExchangeRateResponse;
import com.example.transaction.model.ExchangeRate;
import com.example.transaction.repository.ExchangeRateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeRateService {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateService.class);

    @Autowired
    private WebClient webClient;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Value("${exchange.rate.api.key}")
    private String apiKey;

    @Scheduled(cron = "30 4 2 * * ?")
    public void fetchDailyExchangeRates() {
        fetchExchangeRate();
    }

    private void fetchExchangeRate() {
        String date = LocalDate.now().toString();
        String url = String.format("/currency_conversion?symbol=USD/RUB,USD/KZT&date=%s&apikey=%s", date, apiKey);

        logger.info("Fetching exchange rates from URL: {}", url);

        ExchangeRateResponse response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class)
                .block();

        logger.info("Response received: {}", response);

        if (response != null) {
            List<ExchangeRate> exchangeRates = convertToExchangeRates(response);
            logger.info("Converted exchange rates: {}", exchangeRates);
            exchangeRateRepository.saveAll(exchangeRates);
            logger.info("Exchange rates saved successfully.");
        } else {
            logger.warn("No response received from exchange rate API.");
        }
    }

    private List<ExchangeRate> convertToExchangeRates(ExchangeRateResponse response) {
        List<ExchangeRate> exchangeRates = new ArrayList<>();

        if (response.getUsdToRub() != null) {
            ExchangeRate rubToUsd = new ExchangeRate();
            rubToUsd.setCurrencyPair("USD/RUB");
            rubToUsd.setRate(response.getUsdToRub().getRate());
            rubToUsd.setDate(LocalDate.now());
            exchangeRates.add(rubToUsd);
        }

        if (response.getUsdToKzt() != null) {
            ExchangeRate kztToUsd = new ExchangeRate();
            kztToUsd.setCurrencyPair("USD/KZT");
            kztToUsd.setRate(response.getUsdToKzt().getRate());
            kztToUsd.setDate(LocalDate.now());
            exchangeRates.add(kztToUsd);
        }

        return exchangeRates;
    }
}
