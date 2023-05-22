package com.budgetify.dao;

import com.budgetify.constant.SQLQuery;
import com.budgetify.entity.Report;
import com.budgetify.entity.mapper.ReportMapper;
import com.budgetify.exception.ApiException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class BaseReportDao {
    protected final JdbcTemplate jdbcTemplate;

    public BaseReportDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Report findById(Integer id) {
        List<Report> reports = jdbcTemplate.query(SQLQuery.SELECT_REPORT_BY_ID, new ReportMapper(), id);

        if (reports.isEmpty()) {
            throw new ApiException(String.format("Transaction with id: %d not found.", id));
        }

        return reports.get(0);
    }

    public List<Report> findAll() {
        return jdbcTemplate.query(SQLQuery.SELECT_REPORTS, new ReportMapper());
    }

    public List<Report> findAllByBudgetId(Integer budgetId) {
        return jdbcTemplate.query(SQLQuery.SELECT_REPORTS_BY_BUDGET_ID, new ReportMapper(), budgetId);
    }
}
