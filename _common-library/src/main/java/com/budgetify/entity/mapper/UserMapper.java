package com.budgetify.entity.mapper;

import com.budgetify.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getInt("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .phoneNumber(rs.getString("phone_number"))
                .passportNumber(rs.getString("passport_number"))
                .role(rs.getString("role"))
                .countryId(rs.getInt("country_id"))
                .password(rs.getString("password"))
                .build();
    }
}
