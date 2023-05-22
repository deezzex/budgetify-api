package com.budgetify.service;

import com.budgetify.constant.Formatter;
import com.budgetify.dao.BaseBudgetCategoryDao;
import com.budgetify.dao.BaseBudgetDao;
import com.budgetify.dao.BaseCategoryDao;
import com.budgetify.dto.BudgetResponseDto;
import com.budgetify.entity.Budget;
import com.budgetify.entity.BudgetCategory;
import com.budgetify.entity.Category;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BudgetService {
    private final BaseBudgetDao budgetDao;
    private final BaseBudgetCategoryDao budgetCategoryDao;
    private final BaseCategoryDao categoryDao;

    public BudgetResponseDto getBudget(Integer id) {
        Budget budget = budgetDao.findById(id);
        List<BudgetCategory> budgetCategories = budgetCategoryDao.findAllByBudgetId(budget.getId());
        List<Category> categories = new ArrayList<>();

        budgetCategories.forEach(budgetCategory -> {
            Category category = categoryDao.findById(budgetCategory.getCategoryId());
            categories.add(category);
        });

        List<String> categoryNames = categories.stream().map(Category::getName).collect(Collectors.toList());

        return BudgetResponseDto.builder()
                .budgetId(budget.getId())
                .accountId(budget.getAccountId())
                .budgetName(budget.getName())
                .budgetDescription(budget.getDescription())
                .budgetLimit(budget.getLimit().doubleValue())
                .spent(budget.getSpent().doubleValue())
                .startedAt(budget.getStartedAt().format(Formatter.DATE_FORMATTER))
                .closedAt(budget.getClosedAt().format(Formatter.DATE_FORMATTER))
                .budgetCategories(categoryNames)
                .build();
    }
}