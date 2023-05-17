package com.budgetify.security;

import com.budgetify.conts.Role;
import com.budgetify.dao.BaseUserDao;
import com.budgetify.entity.User;
import com.budgetify.exception.ApiException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorityService {
    private final BaseUserDao userDao;

    public void validateAdminAccess(Integer userId){
        User user = userDao.findById(userId);

        if (!user.getRole().equals(Role.ADMIN.name())){
            throw new ApiException("The resource requires administrator access.");
        }
    }
}
