package com.budgetify.util;

import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.BaseAccountDao;
import com.budgetify.dao.BaseBudgetCategoryDao;
import com.budgetify.dao.BaseTransactionDao;
import com.budgetify.dao.BudgetDao;
import com.budgetify.service.BudgetService;
import lombok.Getter;

import javax.sql.DataSource;

@Getter
public class Initializer {
    private static Initializer instance;
    private final DataSource dataSource;
    private final BaseAccountDao accountDao;
    private final BudgetDao budgetDao;
    private final BaseTransactionDao transactionDao;
    private final BaseBudgetCategoryDao budgetCategoryDao;
    private final BudgetService budgetService;

    private Initializer() {
        dataSource = DataSourceConfig.getDataSource();
        accountDao = new BaseAccountDao(dataSource);
        budgetDao = new BudgetDao(dataSource);
        transactionDao = new BaseTransactionDao(dataSource);
        budgetCategoryDao = new BaseBudgetCategoryDao(dataSource);
        budgetService = new BudgetService(budgetDao, transactionDao, budgetCategoryDao);
    }

    public static Initializer getInstance() {
        if (instance == null) {
            return new Initializer();
        }

        return instance;
    }
}
