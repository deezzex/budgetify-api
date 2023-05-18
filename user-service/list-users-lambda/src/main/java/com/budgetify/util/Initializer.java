package com.budgetify.util;

import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.BaseSessionDao;
import com.budgetify.dao.BaseUserDao;
import com.budgetify.dao.ResourceDao;
import com.budgetify.security.AuthorityService;
import com.budgetify.security.SecurityService;
import com.budgetify.service.UserService;
import lombok.Getter;

import javax.sql.DataSource;

@Getter
public class Initializer {

    private static Initializer instance;
    private final DataSource dataSource;
    private final BaseUserDao userDao;
    private final UserService userService;
    private final BaseSessionDao sessionDao;
    private final SecurityService securityService;
    private final AuthorityService authorityService;
    private final ResourceDao resourceDao;

    private Initializer() {
        dataSource = DataSourceConfig.getDataSource();
        userDao = new BaseUserDao(dataSource);
        userService = new UserService(userDao);
        sessionDao = new BaseSessionDao(dataSource);
        securityService = new SecurityService(sessionDao);
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
