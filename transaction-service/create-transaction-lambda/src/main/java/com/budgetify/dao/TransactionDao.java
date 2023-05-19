package com.budgetify.dao;

import com.budgetify.entity.Transaction;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class TransactionDao extends BaseTransactionDao{

    public TransactionDao(DataSource dataSource) {
        super(dataSource);
    }

    public int save(Transaction transaction){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("transactions")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("account_id", transaction.getAccountId());
        parameters.put("settled_at", transaction.getSettledAt());
        parameters.put("amount", transaction.getAmount());
        parameters.put("description", transaction.getDescription());
        parameters.put("category_id", transaction.getCategoryId());

        Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);

        return generatedId.intValue();
    }
}
