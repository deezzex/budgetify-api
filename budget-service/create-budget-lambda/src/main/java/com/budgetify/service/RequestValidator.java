package com.budgetify.service;

import com.budgetify.dto.BudgetCreateDto;
import com.budgetify.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    public static void validate(BudgetCreateDto budgetCreateDto) {
        List<String> unsetFields = new ArrayList<>();

        if (budgetCreateDto.getAccountId() == null) {
            unsetFields.add("accountId");
        }
        if (budgetCreateDto.getName() == null) {
            unsetFields.add("name");
        }
        if (budgetCreateDto.getDescription() == null) {
            unsetFields.add("description");
        }
        if (budgetCreateDto.getLimit() == null) {
            unsetFields.add("limit");
        }
        if (budgetCreateDto.getStartedAt() == null) {
            unsetFields.add("startedAt");
        }
        if (budgetCreateDto.getClosedAt() == null) {
            unsetFields.add("closedAt");
        }

        if (!unsetFields.isEmpty()) {
            throw new ApiException("Some required fields are not set in the request: " + unsetFields);
        }
    }
}