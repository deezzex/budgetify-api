package com.budgetify.entity.mapper;

import com.budgetify.entity.Currency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyMapper implements RowMapper<Currency> {
    @Override
    public Currency mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Currency.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
    }
}
