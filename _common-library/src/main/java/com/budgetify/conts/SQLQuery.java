package com.budgetify.conts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SQLQuery {

    //SESSION
    public static final String SELECT_SESSION_BY_ID = "SELECT * FROM sessions WHERE id = ?;";
    public static final String UPDATE_SESSION_UPDATED_AT_AND_CLOSED_AT_AND_STATUS = "UPDATE sessions SET updated_at = ?, closed_at = ?, status = ? WHERE id = ?;";
    //CURRENCY
    public static final String SELECT_CURRENCY_BY_ID = "SELECT * FROM currencies WHERE id = ?;";
    //USERS
    public static final String SELECT_USERS = "SELECT * FROM users;";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?;";
    public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?;";
    public static final String SELECT_USER_BY_PASSPORT = "SELECT * FROM users WHERE passport_number = ?;";
    public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?;";
    //COUNTRY
    public static final String SELECT_COUNTRY_BY_ID = "SELECT * FROM countries WHERE id = ?;";
    //ACCOUNT
    public static final String SELECT_ACCOUNT_BY_ID = "SELECT * FROM accounts WHERE id = ?;";
    public static final String SELECT_ACCOUNTS= "SELECT * FROM accounts;";
}
