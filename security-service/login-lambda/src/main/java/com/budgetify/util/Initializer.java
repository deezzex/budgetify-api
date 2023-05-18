package com.budgetify.util;

import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.SessionDao;
import com.budgetify.dao.UserDao;
import com.budgetify.service.SessionService;
import lombok.Getter;

import javax.sql.DataSource;

@Getter
public class Initializer {
    private static Initializer instance;
    private final DataSource dataSource;
    private final UserDao userDao;
    private final SessionDao sessionDao;
    private final SessionService sessionService;

    private Initializer() {
        dataSource = DataSourceConfig.getDataSource();
        sessionDao = new SessionDao(dataSource);
        userDao = new UserDao(dataSource);
        sessionService = new SessionService(userDao, sessionDao);
    }

    public static Initializer getInstance() {
        if (instance == null) {
            return new Initializer();
        }

        return instance;
    }
}
