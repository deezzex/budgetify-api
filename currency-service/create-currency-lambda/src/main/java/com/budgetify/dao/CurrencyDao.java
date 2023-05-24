package com.budgetify.dao;

import com.budgetify.entity.Currency;
import com.budgetify.entity.mapper.CurrencyMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CurrencyDao extends BaseCurrencyDao {
    public CurrencyDao(DataSource dataSource) {
        super(dataSource);
    }

    public int save(Currency currency) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("currencies")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("name", currency.getName());

        Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);

        return generatedId.intValue();
    }

    public Optional<Currency> findByName(String name) {
        List<Currency> currencies = jdbcTemplate.query("SELECT * FROM currencies WHERE name = ?;", new CurrencyMapper(), name);

        if (currencies.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(currencies.get(0));
    }
}