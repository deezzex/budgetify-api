package com.budgetify.dao;

import com.budgetify.entity.Session;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


public class SessionDao extends BaseSessionDao {
    public SessionDao(DataSource dataSource) {
        super(dataSource);
    }

    public void save(Session session) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("sessions");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("id", session.getId());
        parameters.put("user_id", session.getUserId());
        parameters.put("created_at", session.getCreatedAt());
        parameters.put("updated_at", session.getUpdatedAt());
        parameters.put("closed_at", session.getClosedAt());
        parameters.put("status", session.getStatus());

        simpleJdbcInsert.execute(parameters);
    }
}
