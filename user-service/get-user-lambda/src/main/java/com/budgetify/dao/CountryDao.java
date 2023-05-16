package com.budgetify.dao;

import com.budgetify.entity.Country;
import com.budgetify.entity.mapper.CountryMapper;
import com.budgetify.exception.ApiException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;


public class CountryDao {
    private final JdbcTemplate jdbcTemplate;

    public CountryDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Country findById(Integer id) {
        List<Country> countries = jdbcTemplate.query(SQLQuery.SELECT_COUNTRY_BY_ID, new CountryMapper(), id);

        if (countries.isEmpty()) {
            throw new ApiException(String.format("Country with id: %d not found.", id));
        }

        return countries.get(0);
    }
}
