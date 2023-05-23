package com.budgetify.entity.mapper;

import com.budgetify.entity.Deposit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DepositMapper implements RowMapper<Deposit> {
    @Override
    public Deposit mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Deposit.builder()
                .id(rs.getInt("id"))
                .accountId(rs.getInt("account_id"))
                .startedAt(convertFrom(rs.getDate("started_at")))
                .closedAt(convertFrom(rs.getDate("closed_at")))
                .amount(rs.getBigDecimal("amount"))
                .status(rs.getString("status"))
                .currencyId(rs.getInt("currency_id"))
                .build();
    }

    private LocalDate convertFrom(Date date) {
        return date != null ? date.toLocalDate() : null;
    }
}