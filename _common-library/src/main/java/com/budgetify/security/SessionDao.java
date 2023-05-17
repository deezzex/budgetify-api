package com.budgetify.security;

import com.budgetify.entity.Session;
import com.budgetify.exception.ApiException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class SessionDao {
    private static final String SELECT_SESSION_BY_ID = "SELECT * FROM sessions WHERE id = ?;";
    private static final String UPDATE_SESSION_CLOSED_AT_AND_STATUS = "UPDATE sessions SET updated_at = ?, closed_at = ?, status = ? WHERE id = ?;";
    private final JdbcTemplate jdbcTemplate;

    public SessionDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Session findById(String id) {
        List<Session> sessions = jdbcTemplate.query(SELECT_SESSION_BY_ID, new SessionMapper(), id);

        if (sessions.isEmpty()) {
            throw new ApiException(String.format("Session with id: [%s] not found.", id));
        }

        return sessions.get(0);
    }

    public void update(Session session) {
        int updatedRows = jdbcTemplate.update(UPDATE_SESSION_CLOSED_AT_AND_STATUS,
                session.getUpdatedAt(),
                session.getClosedAt(),
                session.getStatus(),
                session.getId());

        if (updatedRows != 1) {
            throw new ApiException("Session wasn't updated.");
        }
    }
}
