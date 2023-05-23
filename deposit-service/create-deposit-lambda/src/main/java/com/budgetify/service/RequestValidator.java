package com.budgetify.service;

import com.budgetify.dto.DepositCreateDto;
import com.budgetify.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    public static void validate(DepositCreateDto depositCreateDto) {
        List<String> unsetFields = new ArrayList<>();

        if (depositCreateDto.getAccountId() == null) {
            unsetFields.add("accountId");
        }
        if (depositCreateDto.getAmount() == null) {
            unsetFields.add("amount");
        }
        if (depositCreateDto.getNumberOfDays() == null) {
            unsetFields.add("numberOfDays");
        }

        if (!unsetFields.isEmpty()) {
            throw new ApiException("Some required fields are not set in the request: " + unsetFields);
        }
    }
}