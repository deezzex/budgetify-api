package com.budgetify.service;

import com.budgetify.dto.CategoryCreateDto;
import com.budgetify.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    public static void validate(CategoryCreateDto categoryCreateDto) {
        List<String> unsetFields = new ArrayList<>();

        if (categoryCreateDto.getName() == null) {
            unsetFields.add("name");
        }
        if (categoryCreateDto.getDescription() == null) {
            unsetFields.add("description");
        }
        if (categoryCreateDto.getType() == null) {
            unsetFields.add("type");
        }

        if (!unsetFields.isEmpty()) {
            throw new ApiException("Some required fields are not set in the request: " + unsetFields);
        }
    }
}