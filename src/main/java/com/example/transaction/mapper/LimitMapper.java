package com.example.transaction.mapper;

import com.example.transaction.dto.LimitDTO;
import com.example.transaction.model.Limit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LimitMapper {
    LimitDTO toDto(Limit limit);

    Limit toEntity(LimitDTO limitDTO);
}
