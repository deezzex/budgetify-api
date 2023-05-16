package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSaveDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String passportNumber;
    private String password;
    private String role;
    private Integer countryId;
}
