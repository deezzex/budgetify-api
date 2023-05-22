package com.budgetify.entity.mapper;

import com.budgetify.entity.BudgetCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BudgetCategoryMapper implements RowMapper<BudgetCategory> {
    @Override
    public BudgetCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BudgetCategory.builder()
                .id(rs.getInt("id"))
                .budgetId(rs.getInt("budget_id"))
                .categoryId(rs.getInt("category_id"))
                .build();
    }
}
