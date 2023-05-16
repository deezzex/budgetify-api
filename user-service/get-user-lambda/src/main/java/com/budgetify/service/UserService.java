package com.budgetify.service;

import com.budgetify.dao.CountryDao;
import com.budgetify.dao.UserDao;
import com.budgetify.dto.UserResponseDto;
import com.budgetify.entity.Country;
import com.budgetify.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final CountryDao countryDao;

    public UserResponseDto findUser(Integer id) {
        User user = userDao.findById(id);
        Country country = countryDao.findById(user.getCountryId());

        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .passportNumber(user.getPassportNumber())
                .role(user.getRole())
                .country(country.getName())
                .build();
    }
}
