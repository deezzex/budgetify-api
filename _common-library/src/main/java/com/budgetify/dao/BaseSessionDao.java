package com.budgetify.dao;

import com.budgetify.entity.Session;
import com.budgetify.entity.mapper.SessionMapper;
import com.budgetify.exception.ApiException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static com.budgetify.conts.SQLQuery.SELECT_SESSION_BY_ID;
import static com.budgetify.conts.SQLQuery.UPDATE_SESSION_UPDATED_AT_AND_CLOSED_AT_AND_STATUS;

public class BaseSessionDao {
    protected final JdbcTemplate jdbcTemplate;

    public BaseSessionDao(DataSource dataSource) {
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
        int updatedRows = jdbcTemplate.update(UPDATE_SESSION_UPDATED_AT_AND_CLOSED_AT_AND_STATUS,
                session.getUpdatedAt(),
                session.getClosedAt(),
                session.getStatus(),
                session.getId());

        if (updatedRows != 1) {
            throw new ApiException("Session wasn't updated.");
        }
    }
}
