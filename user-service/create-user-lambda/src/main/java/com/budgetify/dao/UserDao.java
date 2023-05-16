package com.budgetify.dao;

import com.budgetify.dto.UserSaveDto;
import com.budgetify.entity.User;
import com.budgetify.entity.mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int save(UserSaveDto userSaveDto) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("first_name", userSaveDto.getFirstName());
        parameters.put("last_name", userSaveDto.getLastName());
        parameters.put("email", userSaveDto.getEmail());
        parameters.put("phone_number", userSaveDto.getPhoneNumber());
        parameters.put("passport_number", userSaveDto.getPassportNumber());
        parameters.put("password", userSaveDto.getPassword());
        parameters.put("role", userSaveDto.getRole());
        parameters.put("country_id", userSaveDto.getCountryId());

        Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);

        return generatedId.intValue();
    }

    public Optional<User> findByEmail(String email) {
        List<User> users = jdbcTemplate.query(SQLQuery.SELECT_USER_BY_EMAIL, new UserMapper(), email);

        if (users.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(users.get(0));
    }

    public Optional<User> findByPassport(String passportNumber) {
        List<User> users = jdbcTemplate.query(SQLQuery.SELECT_USER_BY_PASSPORT, new UserMapper(), passportNumber);

        if (users.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(users.get(0));
    }
}
