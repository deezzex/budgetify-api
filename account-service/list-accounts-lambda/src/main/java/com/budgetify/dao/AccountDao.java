package com.budgetify.dao;

import com.budgetify.constant.SQLQuery;
import com.budgetify.entity.Account;
import com.budgetify.entity.mapper.AccountMapper;

import javax.sql.DataSource;
import java.util.List;

public class AccountDao extends BaseAccountDao{
    public AccountDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<Account> findAllAccountByUserId(Integer userId){
        return jdbcTemplate.query(SQLQuery.SELECT_ACCOUNTS_BY_USER_ID, new AccountMapper(), userId);
    }
}
