package com.budgetify.service;

import com.budgetify.dto.LoginDto;
import com.budgetify.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    public static void validate(LoginDto loginDto) {
        List<String> unsetFields = new ArrayList<>();

        if (loginDto.getEmail() == null) {
            unsetFields.add("email");
        }
        if (loginDto.getPassword() == null) {
            unsetFields.add("password");
        }

        if (!unsetFields.isEmpty()) {
            throw new ApiException("Some required fields are not set in the request: " + unsetFields);
        }
    }
}
