package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BudgetCreateDto {
    private Integer accountId;
    private String name;
    private String description;
    private BigDecimal limit;
    private String startedAt;
    private String closedAt;
}
