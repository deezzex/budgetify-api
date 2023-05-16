package com.budgetify.dao;

import com.budgetify.entity.User;
import com.budgetify.entity.mapper.UserMapper;
import com.budgetify.exception.ApiException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;


public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User findById(Integer id) {
        List<User> users = jdbcTemplate.query(SQLQuery.SELECT_USER_BY_ID, new UserMapper(), id);

        if (users.isEmpty()) {
            throw new ApiException(String.format("User with id: %d not found.", id));
        }

        return users.get(0);
    }
}
