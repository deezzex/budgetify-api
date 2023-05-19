package com.budgetify.dao;

import com.budgetify.conts.SQLQuery;
import com.budgetify.entity.Transaction;
import com.budgetify.entity.mapper.TransactionMapper;
import com.budgetify.exception.ApiException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class BaseTransactionDao {
    protected final JdbcTemplate jdbcTemplate;

    public BaseTransactionDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Transaction findById(Integer id) {
        List<Transaction> transactions = jdbcTemplate.query(SQLQuery.SELECT_TRANSACTION_BY_ID, new TransactionMapper(), id);

        if (transactions.isEmpty()) {
            throw new ApiException(String.format("Transaction with id: %d not found.", id));
        }

        return transactions.get(0);
    }

    public List<Transaction> findAll() {
        return jdbcTemplate.query(SQLQuery.SELECT_TRANSACTIONS, new TransactionMapper());
    }
}
