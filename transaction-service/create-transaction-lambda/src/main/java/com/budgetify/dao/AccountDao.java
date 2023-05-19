package com.budgetify.dao;

import com.budgetify.conts.SQLQuery;
import com.budgetify.entity.Account;
import com.budgetify.exception.ApiException;

import javax.sql.DataSource;

public class AccountDao extends BaseAccountDao {
    public AccountDao(DataSource dataSource) {
        super(dataSource);
    }

    public void update(Account account) {
        int updatedRows = jdbcTemplate.update(SQLQuery.UPDATE_ACCOUNT_BALANCE, account.getBalance(), account.getId());

        if (updatedRows != 1) {
            throw new ApiException("Account was now updated.");
        }
    }
}
