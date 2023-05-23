package com.budgetify.dao;

import com.budgetify.constant.SQLQuery;
import com.budgetify.entity.Deposit;
import com.budgetify.entity.mapper.DepositMapper;
import com.budgetify.exception.ApiException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class BaseDepositDao {
    protected final JdbcTemplate jdbcTemplate;

    public BaseDepositDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Deposit findById(Integer id) {
        List<Deposit> deposits = jdbcTemplate.query(SQLQuery.SELECT_DEPOSIT_BY_ID, new DepositMapper(), id);

        if (deposits.isEmpty()) {
            throw new ApiException(String.format("Deposit with id: %d not found.", id));
        }

        return deposits.get(0);
    }

    public List<Deposit> findAll() {
        return jdbcTemplate.query(SQLQuery.SELECT_DEPOSITS, new DepositMapper());
    }

    public List<Deposit> findAllByAccountId(Integer accountId) {
        return jdbcTemplate.query(SQLQuery.SELECT_DEPOSITS_BY_ACCOUNT_ID, new DepositMapper(), accountId);
    }
}