package com.budgetify.dao;

import com.budgetify.constant.SQLQuery;
import com.budgetify.entity.Currency;
import com.budgetify.entity.mapper.CurrencyMapper;
import com.budgetify.exception.ApiException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class BaseCurrencyDao {
    protected final JdbcTemplate jdbcTemplate;

    public BaseCurrencyDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Currency findById(Integer id) {
        List<Currency> currencies = jdbcTemplate.query(SQLQuery.SELECT_CURRENCY_BY_ID, new CurrencyMapper(), id);

        if (currencies.isEmpty()) {
            throw new ApiException(String.format("Currency with id: %d not found.", id));
        }

        return currencies.get(0);
    }

    public List<Currency> findAll() {
        return jdbcTemplate.query(SQLQuery.SELECT_CURRENCIES, new CurrencyMapper());
    }
}
