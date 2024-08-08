package com.example.transaction.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "MonthlyLimits")
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", length = 12, nullable = false)
    private Long accountId;

    @Column(name = "category", length = 50, nullable = false)
    private String expenseCategory;

    @Column(name = "amount", nullable = false)
    private BigDecimal limitSum;

    @Column(name = "set_date", nullable = false)
    private ZonedDateTime limitDatetime;

    @Column(name = "currency_shortname", length = 3, nullable = false)
    private String limitCurrencyShortname;

    public Limit() {
    }

    public Limit(String expenseCategory, Long accountId, BigDecimal limitSum,
                 ZonedDateTime limitDatetime, String limitCurrencyShortname) {
        this.accountId = accountId;
        this.expenseCategory = expenseCategory;
        this.limitSum = limitSum;
        this.limitDatetime = limitDatetime;
        this.limitCurrencyShortname = limitCurrencyShortname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

