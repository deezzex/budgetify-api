package com.budgetify.util;

import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.BaseCountryDao;
import com.budgetify.dao.UserDao;
import com.budgetify.service.UserService;
import lombok.Getter;

import javax.sql.DataSource;

@Getter
public class Initializer {

    private static Initializer instance;
    private final DataSource dataSource;
    private final UserDao userDao;
    private final BaseCountryDao countryDao;
    private final UserService userService;

    private Initializer() {
        dataSource = DataSourceConfig.getDataSource();
        userDao = new UserDao(dataSource);
        countryDao = new BaseCountryDao(dataSource);
        userService = new UserService(userDao, countryDao);
    }

    public static Initializer getInstance() {
        if (instance == null) {
            return new Initializer();
        }

        return instance;
    }
}
