package com.budgetify.util;

import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.BaseSessionDao;
import com.budgetify.dao.BaseUserDao;
import com.budgetify.dao.CategoryDao;
import com.budgetify.dao.ResourceDao;
import com.budgetify.security.AuthorityService;
import com.budgetify.security.SecurityService;
import com.budgetify.service.CategoryService;
import lombok.Getter;

import javax.sql.DataSource;

@Getter
public class Initializer {
    private static Initializer instance;
    private final DataSource dataSource;
    private final BaseSessionDao sessionDao;
    private final SecurityService securityService;
    private final CategoryDao categoryDao;
    private final BaseUserDao userDao;
    private final CategoryService categoryService;
    private final ResourceDao resourceDao;
    private final AuthorityService authorityService;

    private Initializer() {
        dataSource = DataSourceConfig.getDataSource();
        sessionDao = new BaseSessionDao(dataSource);
        securityService = new SecurityService(sessionDao);
        categoryDao = new CategoryDao(dataSource);
        userDao = new BaseUserDao(dataSource);
        categoryService = new CategoryService(categoryDao);
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
