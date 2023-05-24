package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class CategoryCreateDto {
    private String name;
    private String description;
    private String type;
}