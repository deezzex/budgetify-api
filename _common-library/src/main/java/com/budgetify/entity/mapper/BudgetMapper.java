package com.budgetify.entity.mapper;

import com.budgetify.entity.Budget;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BudgetMapper implements RowMapper<Budget> {
    @Override
    public Budget mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Budget.builder()
                .id(rs.getInt("id"))
                .accountId(rs.getInt("account_id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .limit(rs.getBigDecimal("budget_limit"))
                .startedAt(convertFrom(rs.getDate("started_at")))
                .closedAt(convertFrom(rs.getDate("closed_at")))
                .spent(rs.getBigDecimal("spent"))
                .build();
    }

    private LocalDate convertFrom(Date date) {
        return date != null ? date.toLocalDate() : null;
    }
}
