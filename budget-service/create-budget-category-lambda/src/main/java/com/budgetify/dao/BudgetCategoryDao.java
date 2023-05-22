package com.budgetify.dao;

import com.budgetify.entity.Budget;
import com.budgetify.entity.BudgetCategory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class BudgetCategoryDao extends BaseBudgetCategoryDao {

    public BudgetCategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    public int save(BudgetCategory budgetCategory) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("budget_categories")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("budget_id", budgetCategory.getBudgetId());
        parameters.put("category_id", budgetCategory.getCategoryId());

        Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);

        return generatedId.intValue();
    }
}