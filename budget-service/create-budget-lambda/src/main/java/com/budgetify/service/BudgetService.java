package com.budgetify.service;

import com.budgetify.constant.Formatter;
import com.budgetify.dao.BaseAccountDao;
import com.budgetify.dao.BudgetDao;
import com.budgetify.dto.BudgetCreateDto;
import com.budgetify.dto.BudgetResponseDto;
import com.budgetify.entity.Account;
import com.budgetify.entity.Budget;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
public class BudgetService {
    private final BudgetDao budgetDao;
    private final BaseAccountDao accountDao;

    public BudgetResponseDto createBudget(BudgetCreateDto budgetCreateDto) {
        Account account = accountDao.findById(budgetCreateDto.getAccountId());

        Budget budget = buildBudget(budgetCreateDto);

        int budgetId = budgetDao.save(budget);

        return BudgetResponseDto.builder()
                .budgetId(budgetId)
                .budgetName(budget.getName())
                .budgetDescription(budget.getDescription())
                .budgetLimit(budget.getLimit().doubleValue())
                .accountId(account.getId())
                .accountName(account.getName())
                .startedAt(budgetCreateDto.getStartedAt())
                .closedAt(budgetCreateDto.getClosedAt())
                .build();
    }

    private Budget buildBudget(BudgetCreateDto budgetCreateDto) {
        return Budget.builder()
                .accountId(budgetCreateDto.getAccountId())
                .name(budgetCreateDto.getName())
                .description(budgetCreateDto.getDescription())
                .limit(budgetCreateDto.getLimit())
                .startedAt(LocalDate.parse(budgetCreateDto.getStartedAt(), Formatter.DATE_FORMATTER))
                .closedAt(LocalDate.parse(budgetCreateDto.getClosedAt(), Formatter.DATE_FORMATTER))
                .spent(BigDecimal.ZERO)
                .build();
    }
}