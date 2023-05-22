package com.budgetify.dao;

import com.budgetify.constant.SQLQuery;
import com.budgetify.entity.Budget;
import com.budgetify.entity.mapper.BudgetMapper;
import com.budgetify.exception.ApiException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class BaseBudgetDao {
    protected final JdbcTemplate jdbcTemplate;

    public BaseBudgetDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Budget findById(Integer id) {
        List<Budget> budgets = jdbcTemplate.query(SQLQuery.SELECT_BUDGET_BY_ID, new BudgetMapper(), id);

        if (budgets.isEmpty()) {
            throw new ApiException(String.format("Budget with id: %d not found.", id));
        }

        return budgets.get(0);
    }

    public List<Budget> findAll() {
        return jdbcTemplate.query(SQLQuery.SELECT_BUDGETS, new BudgetMapper());
    }

    public List<Budget> findAllByAccountId(Integer accountId) {
        return jdbcTemplate.query(SQLQuery.SELECT_BUDGETS_BY_ACCOUNT_ID, new BudgetMapper(), accountId);
    }
}
