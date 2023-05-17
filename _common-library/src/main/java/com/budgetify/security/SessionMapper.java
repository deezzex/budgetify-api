package com.budgetify.security;

import com.budgetify.entity.Session;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SessionMapper implements RowMapper<Session> {
    @Override
    public Session mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Session.builder()
                .id(rs.getString("id"))
                .userId(rs.getInt("user_id"))
                .createdAt(convertFrom(rs.getTimestamp("created_at")))
                .updatedAt(convertFrom(rs.getTimestamp("updated_at")))
                .closedAt(convertFrom(rs.getTimestamp("closed_at")))
                .status(rs.getString("status"))
                .build();
    }

    private LocalDateTime convertFrom(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
