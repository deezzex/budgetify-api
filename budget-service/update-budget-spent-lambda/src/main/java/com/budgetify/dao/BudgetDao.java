package com.budgetify.dao;

import com.budgetify.constant.SQLQuery;
import com.budgetify.entity.Budget;
import com.budgetify.exception.ApiException;

import javax.sql.DataSource;
import java.math.BigDecimal;

public class BudgetDao extends BaseBudgetDao {

    public BudgetDao(DataSource dataSource) {
        super(dataSource);
    }

    public void updateSpent(Budget budget, BigDecimal newSpent) {
        int updatedRows = jdbcTemplate.update(SQLQuery.UPDATE_BUDGET_SPENT,
                newSpent,
                budget.getId());

        if (updatedRows != 1) {
            throw new ApiException("Budget spent wasn't updated");
        }
    }
}