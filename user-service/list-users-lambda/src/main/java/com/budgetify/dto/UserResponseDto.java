package com.budgetify.dto;

import com.budgetify.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String passportNumber;
    private String role;
    private Integer countryId;

    public static UserResponseDto mapFrom(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .passportNumber(user.getPassportNumber())
                .role(user.getRole())
                .countryId(user.getCountryId())
                .build();
    }
}
