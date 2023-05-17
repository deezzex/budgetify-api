package com.budgetify.service;

import com.budgetify.dto.LogoutDto;
import com.budgetify.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    public static void validate(LogoutDto logoutDto) {
        List<String> unsetFields = new ArrayList<>();

        if (logoutDto.getSession() == null) {
            unsetFields.add("session");
        }

        if (!unsetFields.isEmpty()) {
            throw new ApiException("Some required fields are not set in the request: " + unsetFields);
        }
    }
}
