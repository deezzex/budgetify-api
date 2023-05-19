package com.budgetify.util;

import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.BaseSessionDao;
import com.budgetify.dao.BaseUserDao;
import com.budgetify.dao.ResourceDao;
import com.budgetify.dao.TransactionDao;
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
    private final BaseUserDao userDao;
    private final TransactionDao transactionDao;
    private final TransactionService transactionService;
    private final ResourceDao resourceDao;
    private final AuthorityService authorityService;

    private Initializer() {
        dataSource = DataSourceConfig.getDataSource();
        sessionDao = new BaseSessionDao(dataSource);
        securityService = new SecurityService(sessionDao);
        userDao = new BaseUserDao(dataSource);
        transactionDao = new TransactionDao(dataSource);
        transactionService = new TransactionService(transactionDao);
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
