package com.budgetify.service;

import com.budgetify.dto.UserSaveDto;
import com.budgetify.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    public static void validate(UserSaveDto userSaveDto) {
        List<String> unsetFields = new ArrayList<>();

        if (userSaveDto.getFirstName() == null) {
            unsetFields.add("firstName");
        }
        if (userSaveDto.getLastName() == null) {
            unsetFields.add("lastName");
        }
        if (userSaveDto.getEmail() == null) {
            unsetFields.add("email");
        }
        if (userSaveDto.getPassportNumber() == null) {
            unsetFields.add("passportNumber");
        }
        if (userSaveDto.getPassword() == null) {
            unsetFields.add("password");
        }
        if (userSaveDto.getRole() == null) {
            unsetFields.add("role");
        }
        if (userSaveDto.getCountryId() == null) {
            unsetFields.add("countryId");
        }

        if (!unsetFields.isEmpty()) {
            throw new ApiException("Some required fields are not set in the request: " + unsetFields);
        }
    }
}
