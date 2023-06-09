package com.budgetify.util;

import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.*;
import com.budgetify.security.AuthorityService;
import com.budgetify.security.SecurityService;
import com.budgetify.service.ReportService;
import lombok.Getter;

import javax.sql.DataSource;

@Getter
public class Initializer {
    private static Initializer instance;
    private final DataSource dataSource;
    private final BaseSessionDao sessionDao;
    private final SecurityService securityService;
    private final BaseAccountDao accountDao;
    private final ReportDao reportDao;
    private final BaseBudgetDao budgetDao;
    private final BaseCategoryDao categoryDao;
    private final TransactionDao transactionDao;
    private final BaseUserDao userDao;
    private final ReportService reportService;
    private final ResourceDao resourceDao;
    private final AuthorityService authorityService;

    private Initializer() {
        dataSource = DataSourceConfig.getDataSource();
        sessionDao = new BaseSessionDao(dataSource);
        securityService = new SecurityService(sessionDao);
        accountDao = new BaseAccountDao(dataSource);
        reportDao = new ReportDao(dataSource);
        userDao = new BaseUserDao(dataSource);
        transactionDao = new TransactionDao(dataSource);
        budgetDao = new BaseBudgetDao(dataSource);
        categoryDao = new BaseCategoryDao(dataSource);
        reportService = new ReportService(reportDao, budgetDao, accountDao, transactionDao, categoryDao);
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
