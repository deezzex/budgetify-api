package com.budgetify.util;

import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.*;
import com.budgetify.security.AuthorityService;
import com.budgetify.security.SecurityService;
import com.budgetify.service.TransactionService;
import lombok.Getter;

import javax.sql.DataSource;

@Getter
public class Initializer {
    private static Initializer instance;
    private final DataSource dataSource;
    private final BaseSessionDao sessionDao;
    private final SecurityService securityService;
    private final BaseAccountDao accountDao;
    private final BaseUserDao userDao;
    private final BaseCategoryDao categoryDao;
    private final BaseTransactionDao transactionDao;
    private final TransactionService transactionService;
    private final ResourceDao resourceDao;
    private final AuthorityService authorityService;

    private Initializer() {
        dataSource = DataSourceConfig.getDataSource();
        sessionDao = new BaseSessionDao(dataSource);
        securityService = new SecurityService(sessionDao);
        accountDao = new BaseAccountDao(dataSource);
        userDao = new BaseUserDao(dataSource);
        transactionDao = new BaseTransactionDao(dataSource);
        categoryDao = new BaseCategoryDao(dataSource);
        transactionService = new TransactionService(transactionDao, categoryDao, accountDao);
        resourceDao = new ResourceDao(dataSource);
        authorityService = new AuthorityService(userDao, resourceDao);
    }

    public static Initializer getInstance() {
        if (instance == null) {
            return new Initializer();
        }

        return instance;
    }
}
