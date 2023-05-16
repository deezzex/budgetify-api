package com.budgetify.dao;

public class SQLQuery {
    public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?;";
    public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?;";

}
