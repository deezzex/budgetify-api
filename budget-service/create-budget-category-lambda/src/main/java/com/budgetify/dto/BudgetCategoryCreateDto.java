package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BudgetCategoryCreateDto {
    private Integer budgetId;
    private Integer categoryId;
}
