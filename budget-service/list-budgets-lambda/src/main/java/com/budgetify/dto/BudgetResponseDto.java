package com.budgetify.dto;

import com.budgetify.constant.Formatter;
import com.budgetify.entity.Budget;
import lombok.Builder;
import lombok.Data;

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

    public static BudgetResponseDto mapFrom(Budget budget) {
        return BudgetResponseDto.builder()
                .budgetId(budget.getId())
                .accountId(budget.getAccountId())
                .budgetName(budget.getName())
                .budgetDescription(budget.getDescription())
                .budgetLimit(budget.getLimit().doubleValue())
                .spent(budget.getSpent().doubleValue())
                .startedAt(budget.getStartedAt().format(Formatter.DATE_FORMATTER))
                .closedAt(budget.getClosedAt().format(Formatter.DATE_FORMATTER))
                .build();
    }
}
