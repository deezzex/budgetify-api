package com.budgetify.util;

import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.*;
import com.budgetify.security.AuthorityService;
import com.budgetify.security.SecurityService;
import com.budgetify.service.BudgetService;
import com.budgetify.service.TransactionService;
import lombok.Getter;

import javax.sql.DataSource;

@Getter
public class Initializer {
    private static Initializer instance;
    private final DataSource dataSource;
    private final BaseSessionDao sessionDao;
    private final SecurityService securityService;
    private final AccountDao accountDao;
    private final TransactionDao transactionDao;
    private final BaseCategoryDao categoryDao;
    private final BudgetDao budgetDao;
    private final UserDao userDao;
    private final BaseBudgetCategoryDao budgetCategoryDao;
    private final TransactionService transactionService;
    private final BudgetService budgetService;
    private final ResourceDao resourceDao;
    private final AuthorityService authorityService;

    private Initializer() {
        dataSource = DataSourceConfig.getDataSource();
        sessionDao = new BaseSessionDao(dataSource);
        securityService = new SecurityService(sessionDao);
        accountDao = new AccountDao(dataSource);
        transactionDao = new TransactionDao(dataSource);
        categoryDao = new BaseCategoryDao(dataSource);
        budgetDao = new BudgetDao(dataSource);
        budgetCategoryDao = new BaseBudgetCategoryDao(dataSource);
        userDao = new UserDao(dataSource);
        budgetService = new BudgetService(budgetDao, budgetCategoryDao);
        transactionService = new TransactionService(transactionDao, categoryDao, accountDao, budgetService);
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
