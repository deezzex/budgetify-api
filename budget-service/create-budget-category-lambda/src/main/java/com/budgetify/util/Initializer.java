package com.budgetify.util;

import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.*;
import com.budgetify.security.AuthorityService;
import com.budgetify.security.SecurityService;
import com.budgetify.service.BudgetCategoryService;
import lombok.Getter;

import javax.sql.DataSource;

@Getter
public class Initializer {
    private static Initializer instance;
    private final DataSource dataSource;
    private final BaseSessionDao sessionDao;
    private final SecurityService securityService;
    private final BaseBudgetDao budgetDao;
    private final BaseCategoryDao categoryDao;
    private final BudgetCategoryDao budgetCategoryDao;
    private final BaseUserDao userDao;
    private final BudgetCategoryService budgetCategoryService;
    private final ResourceDao resourceDao;
    private final AuthorityService authorityService;

    private Initializer() {
        dataSource = DataSourceConfig.getDataSource();
        sessionDao = new BaseSessionDao(dataSource);
        securityService = new SecurityService(sessionDao);
        budgetDao = new BaseBudgetDao(dataSource);
        categoryDao = new BaseCategoryDao(dataSource);
        budgetCategoryDao = new BudgetCategoryDao(dataSource);
        userDao = new BaseUserDao(dataSource);
        budgetCategoryService = new BudgetCategoryService(budgetDao, categoryDao, budgetCategoryDao);
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
