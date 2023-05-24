package com.budgetify.service;

import com.budgetify.dto.CurrencyCreateDto;
import com.budgetify.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    public static void validate(CurrencyCreateDto currencyCreateDto) {
        List<String> unsetFields = new ArrayList<>();

        if (currencyCreateDto.getName() == null) {
            unsetFields.add("name");
        }

        if (!unsetFields.isEmpty()) {
            throw new ApiException("Some required fields are not set in the request: " + unsetFields);
        }
        if (currencyCreateDto.getName().length() != 3){
            throw new ApiException("Currency name length mush be 3.");
        }
    }
}