package com.budgetify.security;

import com.budgetify.constant.Resource;
import com.budgetify.constant.Role;
import com.budgetify.dao.BaseUserDao;
import com.budgetify.dao.ResourceDao;
import com.budgetify.entity.User;
import com.budgetify.exception.ApiException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorityService {
    private final BaseUserDao userDao;
    private final ResourceDao resourceDao;

    public boolean validateAdminAccess(Integer userId) {
        User user = userDao.findById(userId);

        return user.getRole().equals(Role.ADMIN.name());
    }

    public void validateResourceAccess(Integer userId, Integer resourceId, Resource resource) {
        User user = userDao.findById(userId);

        if (user.getRole().equals(Role.ADMIN.name())) {
            return;
        }

        switch (resource) {
            case USER:
                checkAccess(userId, resourceId);
                break;
            case ACCOUNT:
                checkAccess(userId, resourceDao.findAccountUserId(resourceId));
                break;
            case TRANSACTION:
                checkAccess(userId, resourceDao.findTransactionUserId(resourceId));
                break;
            case BUDGET:
                checkAccess(userId, resourceDao.findBudgetUserId(resourceId));
                break;
            case REPORT:
                checkAccess(userId, resourceDao.findReportUserId(resourceId));
                break;
            case DEPOSIT:
                checkAccess(userId, resourceDao.findDepositUserId(resourceId));
                break;
            default:
                throw new ApiException("Unknown resource.");
        }
    }

    private void checkAccess(int userId, int resourceId) {
        if (userId != resourceId) {
            throw new ApiException("User don't have access to requested resource.");
        }
    }
}
