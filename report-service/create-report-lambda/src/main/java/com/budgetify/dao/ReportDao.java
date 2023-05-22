package com.budgetify.dao;

import com.budgetify.entity.Report;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class ReportDao extends BaseReportDao {

    public ReportDao(DataSource dataSource) {
        super(dataSource);
    }

    public int save(Report report) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("reports")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("budget_id", report.getBudgetId());
        parameters.put("created_at", report.getCreatedAt());
        parameters.put("avg_spent", report.getAvgSpent());
        parameters.put("medium_spent", report.getMediumSpent());
        parameters.put("max_spent", report.getMaxSpent());
        parameters.put("min_spent", report.getMinSpent());
        parameters.put("number_of_transactions", report.getNumberOfTransactions());

        Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);

        return generatedId.intValue();
    }
}