package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryCreateDto {
    private String name;
}