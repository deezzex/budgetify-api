package com.budgetify.dao;

import com.budgetify.constant.SQLQuery;
import com.budgetify.entity.Budget;
import com.budgetify.exception.ApiException;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Slf4j
public class BudgetDao extends BaseBudgetDao {

    public BudgetDao(DataSource dataSource) {
        super(dataSource);
    }

    public void updateSpent(Budget budget) {
        int updatedRows = jdbcTemplate.update(SQLQuery.UPDATE_BUDGET_SPENT,
                budget.getSpent(),
                budget.getId());

        log.info("Updated rows: {}", updatedRows);
        if (updatedRows != 1) {
            throw new ApiException("Budget spent wasn't updated");
        }
    }
}