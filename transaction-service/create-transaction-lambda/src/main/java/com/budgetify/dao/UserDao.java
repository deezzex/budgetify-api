package com.budgetify.dao;

import com.budgetify.entity.User;
import com.budgetify.entity.mapper.UserMapper;
import com.budgetify.exception.ApiException;

import javax.sql.DataSource;
import java.util.List;

public class UserDao extends BaseUserDao {
    public UserDao(DataSource dataSource) {
        super(dataSource);
    }

    public User findByBudgetId(Integer budgetId) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id = (SELECT user_id FROM accounts WHERE id = (SELECT account_id FROM budgets WHERE id = ?));",
                new UserMapper(),
                budgetId);

        if (users.isEmpty()) {
            throw new ApiException("User not found.");
        }

        return users.get(0);
    }
}