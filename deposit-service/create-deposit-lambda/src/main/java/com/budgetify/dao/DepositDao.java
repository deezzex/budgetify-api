package com.budgetify.dao;

import com.budgetify.entity.Deposit;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DepositDao extends BaseDepositDao {

    public DepositDao(DataSource dataSource) {
        super(dataSource);
    }

    public int save(Deposit deposit) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("deposits")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("account_id", deposit.getAccountId());
        parameters.put("started_at", deposit.getStartedAt());
        parameters.put("closed_at", deposit.getClosedAt());
        parameters.put("amount", deposit.getAmount());
        parameters.put("status", deposit.getStatus());
        parameters.put("currency_id", deposit.getCurrencyId());

        Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);

        return generatedId.intValue();
    }
}