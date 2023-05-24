package com.budgetify.dao;

import com.budgetify.entity.Country;
import com.budgetify.entity.mapper.CountryMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CountryDao extends BaseCountryDao {
    public CountryDao(DataSource dataSource) {
        super(dataSource);
    }

    public int save(Country country) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("countries")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("name", country.getName());

        Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);

        return generatedId.intValue();
    }

    public Optional<Country> findByName(String name) {
        List<Country> countries = jdbcTemplate.query("SELECT * FROM countries WHERE name = ?;", new CountryMapper(), name);

        if (countries.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(countries.get(0));
    }
}