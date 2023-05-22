package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BudgetCategoryResponseDto {
    private Integer budgetCategoryId;
    private String budgetName;
    private String categoryName;
}
