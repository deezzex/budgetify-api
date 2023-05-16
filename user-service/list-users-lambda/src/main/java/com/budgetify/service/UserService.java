package com.budgetify.service;

import com.budgetify.dao.UserDao;
import com.budgetify.dto.UserResponseDto;
import com.budgetify.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userDao.findAll();

        return users.stream()
                .map(UserResponseDto::mapFrom)
                .collect(Collectors.toList());
    }
}
