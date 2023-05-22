package com.budgetify.service;

import com.budgetify.dao.BaseBudgetDao;
import com.budgetify.dao.BaseCategoryDao;
import com.budgetify.dao.BudgetCategoryDao;
import com.budgetify.dto.BudgetCategoryCreateDto;
import com.budgetify.dto.BudgetCategoryResponseDto;
import com.budgetify.entity.Budget;
import com.budgetify.entity.BudgetCategory;
import com.budgetify.entity.Category;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BudgetCategoryService {
    private final BaseBudgetDao budgetDao;
    private final BaseCategoryDao categoryDao;
    private final BudgetCategoryDao budgetCategoryDao;

    public BudgetCategoryResponseDto createBudgetCategory(BudgetCategoryCreateDto budgetCategoryCreateDto) {
        Budget budget = budgetDao.findById(budgetCategoryCreateDto.getBudgetId());
        Category category = categoryDao.findById(budgetCategoryCreateDto.getCategoryId());

        BudgetCategory budgetCategory = buildBudgetCategory(budgetCategoryCreateDto);

        int budgetCategoryId = budgetCategoryDao.save(budgetCategory);

        return BudgetCategoryResponseDto.builder()
                .budgetCategoryId(budgetCategoryId)
                .budgetName(budget.getName())
                .categoryName(category.getName())
                .build();
    }

    private BudgetCategory buildBudgetCategory(BudgetCategoryCreateDto budgetCategoryCreateDto) {
        return BudgetCategory.builder()
                .budgetId(budgetCategoryCreateDto.getBudgetId())
                .categoryId(budgetCategoryCreateDto.getCategoryId())
                .build();
    }
}