package com.budgetify.dao;

import com.budgetify.constant.SQLQuery;
import com.budgetify.entity.BudgetCategory;
import com.budgetify.entity.mapper.BudgetCategoryMapper;
import com.budgetify.exception.ApiException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class BaseBudgetCategoryDao {
    protected final JdbcTemplate jdbcTemplate;

    public BaseBudgetCategoryDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public BudgetCategory findById(Integer id) {
        List<BudgetCategory> budgetCategories = jdbcTemplate.query(SQLQuery.SELECT_BUDGET_CATEGORY_BY_ID, new BudgetCategoryMapper(), id);

        if (budgetCategories.isEmpty()) {
            throw new ApiException(String.format("Budget category with id: %d not found.", id));
        }

        return budgetCategories.get(0);
    }

    public List<BudgetCategory> findAll() {
        return jdbcTemplate.query(SQLQuery.SELECT_BUDGET_CATEGORIES, new BudgetCategoryMapper());
    }

    public List<BudgetCategory> findAllByBudgetId(Integer budgetId) {
        return jdbcTemplate.query(SQLQuery.SELECT_BUDGET_CATEGORIES_BY_BUDGET_ID, new BudgetCategoryMapper(), budgetId);
    }

    public List<BudgetCategory> findAllByCategoryId(Integer categoryId) {
        return jdbcTemplate.query(SQLQuery.SELECT_BUDGET_CATEGORIES_BY_CATEGORY_ID, new BudgetCategoryMapper(), categoryId);
    }
}