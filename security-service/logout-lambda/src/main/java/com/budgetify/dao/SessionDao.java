package com.budgetify.dao;

import com.budgetify.conts.SQLQuery;
import com.budgetify.entity.Session;
import com.budgetify.exception.ApiException;

import javax.sql.DataSource;


public class SessionDao extends BaseSessionDao {

    public SessionDao(DataSource dataSource) {
        super(dataSource);
    }

    public void update(Session session) {
        int updatedRows = jdbcTemplate.update(SQLQuery.UPDATE_SESSION_UPDATED_AT_AND_CLOSED_AT_AND_STATUS,
                session.getUpdatedAt(),
                session.getClosedAt(),
                session.getStatus(),
                session.getId());

        if (updatedRows != 1) {
            throw new ApiException("Session wasn't updated.");
        }
    }
}
