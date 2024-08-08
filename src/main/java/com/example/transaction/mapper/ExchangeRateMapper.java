package com.example.transaction.mapper;

import com.example.transaction.dto.ExchangeRateDTO;
import com.example.transaction.model.ExchangeRate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {
    ExchangeRateDTO toDto(ExchangeRate exchangeRate);

    ExchangeRate toEntity(ExchangeRateDTO exchangeRateDTO);
}


