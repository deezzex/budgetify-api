package com.budgetify.entity.mapper;

import com.budgetify.entity.Report;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ReportMapper implements RowMapper<Report> {
    @Override
    public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Report.builder()
                .id(rs.getInt("id"))
                .budgetId(rs.getInt("budget_id"))
                .createdAt(convertFrom(rs.getTimestamp("created_at")))
                .avgSpent(rs.getBigDecimal("avg_spent"))
                .mediumSpent(rs.getBigDecimal("medium_spent"))
                .maxSpent(rs.getBigDecimal("max_spent"))
                .minSpent(rs.getBigDecimal("min_spent"))
                .numberOfTransactions(rs.getInt("number_of_transactions"))
                .build();
    }

    private LocalDateTime convertFrom(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
