package com.budgetify.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BudgetCategory {
    private Integer id;
    private Integer budgetId;
    private Integer categoryId;
}
