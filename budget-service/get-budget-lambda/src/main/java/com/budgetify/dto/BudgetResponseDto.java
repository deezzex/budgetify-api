package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BudgetResponseDto {
    private Integer budgetId;
    private Integer accountId;
    private String budgetName;
    private String budgetDescription;
    private Double budgetLimit;
    private Double spent;
    private String startedAt;
    private String closedAt;
    private List<String> budgetCategories;
}
