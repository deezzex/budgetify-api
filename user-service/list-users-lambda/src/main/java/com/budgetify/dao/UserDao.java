package com.budgetify.dao;

import com.budgetify.entity.User;
import com.budgetify.entity.mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;


public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> findAll() {
        return jdbcTemplate.query(SQLQuery.SELECT_USERS, new UserMapper());
    }
}
