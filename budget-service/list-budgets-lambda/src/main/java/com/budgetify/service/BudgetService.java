package com.budgetify.service;

import com.budgetify.dao.BaseBudgetDao;
import com.budgetify.dto.BudgetResponseDto;
import com.budgetify.entity.Budget;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BudgetService {
    private final BaseBudgetDao budgetDao;

    public List<BudgetResponseDto> getAllBudgets() {
        List<Budget> budgets = budgetDao.findAll();

        return budgets.stream()
                .map(BudgetResponseDto::mapFrom)
                .collect(Collectors.toList());
    }

    public List<BudgetResponseDto> getAllBudgetsByAccountId(Integer accountId) {
        List<Budget> budgets = budgetDao.findAllByAccountId(accountId);

        return budgets.stream()
                .map(BudgetResponseDto::mapFrom)
                .collect(Collectors.toList());
    }
}