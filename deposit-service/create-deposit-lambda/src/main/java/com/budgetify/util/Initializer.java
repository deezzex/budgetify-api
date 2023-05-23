package com.budgetify.util;

import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.*;
import com.budgetify.security.AuthorityService;
import com.budgetify.security.SecurityService;
import com.budgetify.service.DepositService;
import lombok.Getter;

import javax.sql.DataSource;

@Getter
public class Initializer {
    private static Initializer instance;
    private final DataSource dataSource;
    private final BaseSessionDao sessionDao;
    private final SecurityService securityService;
    private final AccountDao accountDao;
    private final BaseCurrencyDao currencyDao;
    private final DepositDao depositDao;
    private final BaseUserDao userDao;
    private final DepositService depositService;
    private final ResourceDao resourceDao;
    private final AuthorityService authorityService;

    private Initializer() {
        dataSource = DataSourceConfig.getDataSource();
        sessionDao = new BaseSessionDao(dataSource);
        securityService = new SecurityService(sessionDao);
        accountDao = new AccountDao(dataSource);
        currencyDao = new BaseCurrencyDao(dataSource);
        depositDao = new DepositDao(dataSource);
        userDao = new BaseUserDao(dataSource);
        depositService = new DepositService(depositDao, accountDao, currencyDao);
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
