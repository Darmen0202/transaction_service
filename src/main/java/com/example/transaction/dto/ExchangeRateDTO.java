package com.example.transaction.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.transaction.model.ExchangeRate}
 */
public class ExchangeRateDTO implements Serializable {
    String currencyPair;
    BigDecimal rate;
    LocalDate date;

    public ExchangeRateDTO() {
    }

    public ExchangeRateDTO(String currencyPair, BigDecimal rate, LocalDate date) {
        this.currencyPair = currencyPair;
        this.rate = rate;
        this.date = date;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}