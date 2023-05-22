package com.budgetify.dao;

import com.budgetify.constant.SQLQuery;
import com.budgetify.entity.User;
import com.budgetify.entity.mapper.UserMapper;
import com.budgetify.exception.ApiException;

import javax.sql.DataSource;
import java.util.List;


public class UserDao extends BaseUserDao {

    public UserDao(DataSource dataSource) {
        super(dataSource);
    }

    public User findByEmail(String email) {
        List<User> users = jdbcTemplate.query(SQLQuery.SELECT_USER_BY_EMAIL, new UserMapper(), email);

        if (users.isEmpty()) {
            throw new ApiException(String.format("User with email: [%s] not found.", email));
        }

        return users.get(0);
    }
}
