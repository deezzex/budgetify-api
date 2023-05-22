package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BudgetResponseDto {
    private Integer budgetId;
    private Integer accountId;
    private String accountName;
    private String budgetName;
    private String budgetDescription;
    private Double budgetLimit;
    private String startedAt;
    private String closedAt;
}
