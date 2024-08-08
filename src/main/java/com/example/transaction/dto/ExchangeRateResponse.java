package com.example.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ExchangeRateResponse {

    @JsonProperty("USD/RUB")
    private ExchangeRateDetail usdToRub;

    @JsonProperty("USD/KZT")
    private ExchangeRateDetail usdToKzt;

    public ExchangeRateDetail getUsdToRub() {
        return usdToRub;
    }

    public void setUsdToRub(ExchangeRateDetail usdToRub) {
        this.usdToRub = usdToRub;
    }

    public ExchangeRateDetail getUsdToKzt() {
        return usdToKzt;
    }

    public void setUsdToKzt(ExchangeRateDetail usdToKzt) {
        this.usdToKzt = usdToKzt;
    }

    public static class ExchangeRateDetail {
        private String symbol;
        private BigDecimal rate;
        private Long timestamp;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public BigDecimal getRate() {
            return rate;
        }

        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }
    }
}

