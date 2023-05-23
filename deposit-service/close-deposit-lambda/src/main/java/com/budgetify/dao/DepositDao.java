package com.budgetify.dao;

import com.budgetify.entity.Deposit;
import com.budgetify.exception.ApiException;

import javax.sql.DataSource;

public class DepositDao extends BaseDepositDao {
    public DepositDao(DataSource dataSource) {
        super(dataSource);
    }

    public void update(Deposit deposit) {
        int updatedRows = jdbcTemplate.update("UPDATE deposits SET status = ? WHERE id = ?;", deposit.getStatus(), deposit.getId());

        if (updatedRows != 1) {
            throw new ApiException("Account was not updated.");
        }
    }
}