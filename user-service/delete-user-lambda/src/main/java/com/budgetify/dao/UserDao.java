package com.budgetify.dao;

import com.budgetify.conts.SQLQuery;
import com.budgetify.entity.User;
import com.budgetify.entity.mapper.UserMapper;
import com.budgetify.exception.ApiException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;


public class UserDao extends BaseUserDao{
    public UserDao(DataSource dataSource) {
        super(dataSource);
    }

    public void delete(Integer id) {
        int updatedRows = jdbcTemplate.update(SQLQuery.DELETE_USER_BY_ID, id);

        if (updatedRows != 1) {
            throw new ApiException(String.format("User with id: %d wasn't deleted.", id));
        }
    }
}
