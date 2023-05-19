package com.budgetify.entity.mapper;

import com.budgetify.entity.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TransactionMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Transaction.builder()
                .id(rs.getInt("id"))
                .accountId(rs.getInt("account_id"))
                .amount(rs.getBigDecimal("amount"))
                .settledAt(convertFrom(rs.getTimestamp("settled_at")))
                .description(rs.getString("description"))
                .categoryId(rs.getInt("category_id"))
                .build();
    }

    private LocalDateTime convertFrom(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
