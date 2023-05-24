package com.budgetify.service;

import com.budgetify.dto.CountryCreateDto;
import com.budgetify.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    public static void validate(CountryCreateDto countryCreateDto) {
        List<String> unsetFields = new ArrayList<>();

        if (countryCreateDto.getName() == null) {
            unsetFields.add("name");
        }

        if (!unsetFields.isEmpty()) {
            throw new ApiException("Some required fields are not set in the request: " + unsetFields);
        }
    }
}