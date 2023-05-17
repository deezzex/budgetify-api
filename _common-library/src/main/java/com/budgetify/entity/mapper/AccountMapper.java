package com.budgetify.entity.mapper;

import com.budgetify.entity.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AccountMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Account.builder()
                .id(rs.getInt("id"))
                .userId(rs.getInt("user_id"))
                .currencyId(rs.getInt("currency_id"))
                .name(rs.getString("name"))
                .balance(rs.getBigDecimal("balance"))
                .createdAt(convertFrom(rs.getDate("created_at")))
                .build();
    }

    private LocalDate convertFrom(Date date) {
        return date != null ? date.toLocalDate() : null;
    }
}
