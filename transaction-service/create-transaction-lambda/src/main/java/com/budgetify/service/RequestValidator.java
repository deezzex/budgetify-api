package com.budgetify.service;

import com.budgetify.dto.TransactionCreateDto;
import com.budgetify.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    public static void validate(TransactionCreateDto transactionCreateDto) {
        List<String> unsetFields = new ArrayList<>();

        if (transactionCreateDto.getAccountId() == null) {
            unsetFields.add("accountId");
        }
        if (transactionCreateDto.getAmount() == null) {
            unsetFields.add("amount");
        }
        if (transactionCreateDto.getDescription() == null) {
            unsetFields.add("description");
        }
        if (transactionCreateDto.getCategoryId() == null) {
            unsetFields.add("categoryId");
        }

        if (!unsetFields.isEmpty()) {
            throw new ApiException("Some required fields are not set in the request: " + unsetFields);
        }
    }
}
