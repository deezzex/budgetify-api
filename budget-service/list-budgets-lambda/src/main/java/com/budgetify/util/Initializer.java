package com.budgetify.util;

import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.BaseBudgetDao;
import com.budgetify.dao.BaseSessionDao;
import com.budgetify.dao.BaseUserDao;
import com.budgetify.dao.ResourceDao;
import com.budgetify.security.AuthorityService;
import com.budgetify.security.SecurityService;
import com.budgetify.service.BudgetService;
import lombok.Getter;

import javax.sql.DataSource;

@Getter
public class Initializer {
    private static Initializer instance;
    private final DataSource dataSource;
    private final BaseSessionDao sessionDao;
    private final SecurityService securityService;
    private final BaseBudgetDao budgetDao;
    private final BaseUserDao userDao;
    private final BudgetService budgetService;
    private final ResourceDao resourceDao;
    private final AuthorityService authorityService;

    private Initializer() {
        dataSource = DataSourceConfig.getDataSource();
        sessionDao = new BaseSessionDao(dataSource);
        securityService = new SecurityService(sessionDao);
        budgetDao = new BaseBudgetDao(dataSource);
        userDao = new BaseUserDao(dataSource);
        budgetService = new BudgetService(budgetDao);
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
