package com.budgetify.service;

import com.budgetify.dao.UserDao;
import com.budgetify.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public void deleteUser(Integer id) {
        User user = userDao.findById(id);
        userDao.delete(user.getId());
    }
}
