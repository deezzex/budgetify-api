package com.budgetify.dao;

import com.budgetify.entity.Budget;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class BudgetDao extends BaseBudgetDao {

    public BudgetDao(DataSource dataSource) {
        super(dataSource);
    }

    public int save(Budget budget) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("budgets")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("account_id", budget.getAccountId());
        parameters.put("name", budget.getName());
        parameters.put("description", budget.getDescription());
        parameters.put("budget_limit", budget.getLimit());
        parameters.put("started_at", budget.getStartedAt());
        parameters.put("closed_at", budget.getClosedAt());
        parameters.put("spent", budget.getSpent());

        Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);

        return generatedId.intValue();
    }
}