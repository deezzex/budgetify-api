package com.budgetify.dto;

import com.budgetify.entity.Currency;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyResponseDto {
    private Integer id;
    private String name;

    public static CurrencyResponseDto mapFrom(Currency currency) {
        return CurrencyResponseDto.builder()
                .id(currency.getId())
                .name(currency.getName())
                .build();
    }
}