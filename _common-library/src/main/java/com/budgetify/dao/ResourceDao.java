package com.budgetify.dao;

import com.budgetify.conts.SQLQuery;
import com.budgetify.exception.ApiException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class ResourceDao {

    private static final RowMapper<Integer> MAPPER = (rs, rowNum) -> rs.getInt("user_id");
    private final JdbcTemplate jdbcTemplate;

    public ResourceDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int findAccountUserId(Integer accountId) {
        List<Integer> integers = jdbcTemplate.query(SQLQuery.SELECT_ACCOUNT_USER_ID_BY_ID, MAPPER, accountId);

        if (integers.isEmpty()) {
            throw new ApiException("Resource not found.");
        }

        return integers.get(0);
    }

    public int findTransactionUserId(Integer transactionId) {
        List<Integer> integers = jdbcTemplate.query(SQLQuery.SELECT_TRANSACTION_USER_ID_BY_ID, MAPPER, transactionId);

        if (integers.isEmpty()) {
            throw new ApiException("Resource not found.");
        }

        return integers.get(0);
    }

    public int findBudgetUserId(Integer budgetId) {
        List<Integer> integers = jdbcTemplate.query(SQLQuery.SELECT_BUDGET_USER_ID_BY_ID, MAPPER, budgetId);

        if (integers.isEmpty()) {
            throw new ApiException("Resource not found.");
        }

        return integers.get(0);
    }

    public int findReportUserId(Integer reportId) {
        List<Integer> integers = jdbcTemplate.query(SQLQuery.SELECT_REPORT_USER_ID_BY_ID, MAPPER, reportId);

        if (integers.isEmpty()) {
            throw new ApiException("Resource not found.");
        }

        return integers.get(0);
    }

    public int findDepositUserId(Integer depositId) {
        List<Integer> integers = jdbcTemplate.query(SQLQuery.SELECT_DEPOSIT_USER_ID_BY_ID, MAPPER, depositId);

        if (integers.isEmpty()) {
            throw new ApiException("Resource not found.");
        }

        return integers.get(0);
    }
}
