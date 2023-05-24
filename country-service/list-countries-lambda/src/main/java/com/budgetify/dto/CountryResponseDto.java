package com.budgetify.dto;

import com.budgetify.entity.Country;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryResponseDto {
    private Integer id;
    private String name;

    public static CountryResponseDto mapFrom(Country country) {
        return CountryResponseDto.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }
}