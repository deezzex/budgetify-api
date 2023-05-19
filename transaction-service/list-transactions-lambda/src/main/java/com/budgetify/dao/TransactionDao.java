package com.budgetify.dao;

import com.budgetify.conts.SQLQuery;
import com.budgetify.entity.Transaction;
import com.budgetify.entity.mapper.TransactionMapper;

import javax.sql.DataSource;
import java.util.List;

public class TransactionDao extends BaseTransactionDao {
    public TransactionDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<Transaction> findAllByAccountId(Integer accountId) {
        return jdbcTemplate.query(SQLQuery.SELECT_TRANSACTIONS_BY_ACCOUNT_ID, new TransactionMapper(), accountId);
    }
}
