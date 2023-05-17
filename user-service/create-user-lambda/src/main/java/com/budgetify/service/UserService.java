package com.budgetify.service;

import com.budgetify.dao.BaseCountryDao;
import com.budgetify.dao.UserDao;
import com.budgetify.dto.UserResponseDto;
import com.budgetify.dto.UserSaveDto;
import com.budgetify.entity.Country;
import com.budgetify.entity.User;
import com.budgetify.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final BaseCountryDao countryDao;

    public UserResponseDto createUser(UserSaveDto userSaveDto) {
        RequestValidator.validate(userSaveDto);

        checkUniqueness(userSaveDto);

        String encryptedPassword = PasswordEncryption.encryptPassword(userSaveDto.getPassword());

        userSaveDto.setPassword(encryptedPassword);

        Country country = countryDao.findById(userSaveDto.getCountryId());
        int userId = userDao.save(userSaveDto);

        return UserResponseDto.builder()
                .id(userId)
                .firstName(userSaveDto.getFirstName())
                .lastName(userSaveDto.getLastName())
                .email(userSaveDto.getEmail())
                .phoneNumber(userSaveDto.getPhoneNumber())
                .passportNumber(userSaveDto.getPassportNumber())
                .role(userSaveDto.getRole())
                .country(country.getName())
                .build();
    }

    private void checkUniqueness(UserSaveDto userSaveDto) {
        Optional<User> userFromDB = userDao.findByEmail(userSaveDto.getEmail());

        if (userFromDB.isPresent()) {
            throw new ApiException(String.format("User with [%s] email already exists.", userSaveDto.getEmail()));
        }

        userFromDB = userDao.findByPassport(userSaveDto.getPassportNumber());

        if (userFromDB.isPresent()) {
            throw new ApiException(String.format("User with [%s] passportNumber already exists.", userSaveDto.getPassportNumber()));
        }
    }
}
