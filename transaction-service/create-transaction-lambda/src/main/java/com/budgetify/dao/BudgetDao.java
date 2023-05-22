package com.budgetify.dao;

import com.budgetify.constant.SQLQuery;
import com.budgetify.entity.Budget;
import com.budgetify.exception.ApiException;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.math.BigDecimal;

@Slf4j
public class BudgetDao extends BaseBudgetDao {

    public BudgetDao(DataSource dataSource) {
        super(dataSource);
    }

    public void updateSpent(Budget budget, BigDecimal newSpent) {
        int updatedRows = jdbcTemplate.update(SQLQuery.UPDATE_BUDGET_SPENT,
                newSpent,
                budget.getId());

        log.info("Updated rows: {}", updatedRows);
        if (updatedRows != 1) {
            throw new ApiException("Budget spent wasn't updated");
        }
    }
}