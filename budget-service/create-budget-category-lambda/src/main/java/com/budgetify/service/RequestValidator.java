package com.budgetify.service;

import com.budgetify.dto.BudgetCategoryCreateDto;
import com.budgetify.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    public static void validate(BudgetCategoryCreateDto budgetCategoryCreateDto) {
        List<String> unsetFields = new ArrayList<>();

        if (budgetCategoryCreateDto.getBudgetId() == null) {
            unsetFields.add("budgetId");
        }
        if (budgetCategoryCreateDto.getCategoryId() == null) {
            unsetFields.add("categoryId");
        }

        if (!unsetFields.isEmpty()) {
            throw new ApiException("Some required fields are not set in the request: " + unsetFields);
        }
    }
}