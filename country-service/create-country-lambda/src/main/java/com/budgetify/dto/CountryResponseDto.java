package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryResponseDto {
    private Integer id;
    private String name;
}