package com.budgetify.service;

import com.budgetify.dto.AccountSaveDto;
import com.budgetify.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    public static void validate(AccountSaveDto accountSaveDto) {
        List<String> unsetFields = new ArrayList<>();

        if (accountSaveDto.getName() == null) {
            unsetFields.add("name");
        }
        if (accountSaveDto.getUserId() == null) {
            unsetFields.add("usedId");
        }
        if (accountSaveDto.getCurrencyId() == null) {
            unsetFields.add("currencyId");
        }

        if (!unsetFields.isEmpty()) {
            throw new ApiException("Some required fields are not set in the request: " + unsetFields);
        }
    }
}
