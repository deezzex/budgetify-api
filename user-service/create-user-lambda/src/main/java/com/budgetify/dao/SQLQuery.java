package com.budgetify.dao;

public class SQLQuery {
    public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?;";
    public static final String SELECT_USER_BY_PASSPORT = "SELECT * FROM users WHERE passport_number = ?;";
}
