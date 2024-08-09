package com.example.transaction.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import java.io.Serializable;

/**
 * DTO for {@link com.example.transaction.model.Limit}
 */

public class LimitDTO {
    String expenseCategory;
    Long accountId;
    BigDecimal limitSum;
    ZonedDateTime limitDatetime;
    String limitCurrencyShortname;

    public LimitDTO() {

    }

    public LimitDTO(String expenseCategory, Long accountId, BigDecimal limitSum,
                    ZonedDateTime limitDatetime, String limitCurrencyShortname) {
        this.expenseCategory = expenseCategory;
        this.accountId = accountId;
        this.limitSum = limitSum;
        this.limitDatetime = limitDatetime;
        this.limitCurrencyShortname = limitCurrencyShortname;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public BigDecimal getLimitSum() {
        return limitSum;
    }

    public void setLimitSum(BigDecimal limitSum) {
        this.limitSum = limitSum;
    }

    public ZonedDateTime getLimitDatetime() {
        return limitDatetime;
    }

    public void setLimitDatetime(ZonedDateTime limitDatetime) {
        this.limitDatetime = limitDatetime;
    }

    public String getLimitCurrencyShortname() {
        return limitCurrencyShortname;
    }

    public void setLimitCurrencyShortname(String limitCurrencyShortname) {
        this.limitCurrencyShortname = limitCurrencyShortname;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}