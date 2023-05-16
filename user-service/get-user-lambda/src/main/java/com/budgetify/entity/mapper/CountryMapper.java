package com.budgetify.entity.mapper;

import com.budgetify.entity.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Country.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
    }
}
