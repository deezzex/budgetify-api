package com.budgetify.util;

import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.SessionDao;
import com.budgetify.service.SessionService;
import lombok.Getter;

import javax.sql.DataSource;

@Getter
public class Initializer {
    private static Initializer instance;
    private final DataSource dataSource;
    private final SessionDao sessionDao;
    private final SessionService sessionService;

    private Initializer() {
        dataSource = DataSourceConfig.getDataSource();
        sessionDao = new SessionDao(dataSource);
        sessionService = new SessionService(sessionDao);
    }

    public static Initializer getInstance() {
        if (instance == null) {
            return new Initializer();
        }

        return instance;
    }
}
