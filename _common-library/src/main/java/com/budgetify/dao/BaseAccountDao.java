package com.budgetify.dao;

import com.budgetify.conts.SQLQuery;
import com.budgetify.entity.Account;
import com.budgetify.entity.mapper.AccountMapper;
import com.budgetify.exception.ApiException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class BaseAccountDao {
    protected final JdbcTemplate jdbcTemplate;

    public BaseAccountDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Account findById(Integer id) {
        List<Account> accounts = jdbcTemplate.query(SQLQuery.SELECT_ACCOUNT_BY_ID, new AccountMapper(), id);

        if (accounts.isEmpty()) {
            throw new ApiException(String.format("Account with id: %d not found.", id));
        }

        return accounts.get(0);
    }

    public List<Account> findAll() {
        return jdbcTemplate.query(SQLQuery.SELECT_ACCOUNTS, new AccountMapper());
    }
}
