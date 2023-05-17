package com.budgetify.dao;

public class SQLQuery {
    public static final String SELECT_SESSION_BY_ID = "SELECT * FROM sessions WHERE id = ?;";
    public static final String UPDATE_SESSION_CLOSED_AT_AND_STATUS = "UPDATE sessions SET closed_at = ?, status = ? WHERE id = ?;";
}
