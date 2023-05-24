package com.budgetify.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SQLQuery {

    //SESSION
    public static final String SELECT_SESSION_BY_ID = "SELECT * FROM sessions WHERE id = ?;";
    public static final String UPDATE_SESSION_UPDATED_AT_AND_CLOSED_AT_AND_STATUS = "UPDATE sessions SET updated_at = ?, closed_at = ?, status = ? WHERE id = ?;";
    //CURRENCY
    public static final String SELECT_CURRENCY_BY_ID = "SELECT * FROM currencies WHERE id = ?;";
    public static final String SELECT_CURRENCIES = "SELECT * FROM currencies;";
    //USERS
    public static final String SELECT_USERS = "SELECT * FROM users;";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?;";
    public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?;";
    public static final String SELECT_USER_BY_PASSPORT = "SELECT * FROM users WHERE passport_number = ?;";
    public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?;";
    //COUNTRY
    public static final String SELECT_COUNTRY_BY_ID = "SELECT * FROM countries WHERE id = ?;";
    public static final String SELECT_COUNTRIES = "SELECT * FROM countries;";
    //ACCOUNT
    public static final String SELECT_ACCOUNT_BY_ID = "SELECT * FROM accounts WHERE id = ?;";
    public static final String SELECT_ACCOUNTS= "SELECT * FROM accounts;";
    public static final String SELECT_ACCOUNTS_BY_USER_ID = "SELECT * FROM accounts WHERE user_id = ?;";
    public static final String UPDATE_ACCOUNT_BALANCE= "UPDATE accounts SET balance = ? WHERE id = ?;";
    //TRANSACTION
    public static final String SELECT_TRANSACTION_BY_ID = "SELECT * FROM transactions WHERE id = ?;";
    public static final String SELECT_TRANSACTIONS_BY_ACCOUNT_ID = "SELECT * FROM transactions WHERE account_id = ?;";
    public static final String SELECT_TRANSACTIONS = "SELECT * FROM transactions;";
    //CATEGORY
    public static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM categories WHERE id = ?;";
    public static final String SELECT_CATEGORIES = "SELECT * FROM categories;";
    //BUDGET
    public static final String SELECT_BUDGET_BY_ID = "SELECT * FROM budgets WHERE id = ?;";
    public static final String SELECT_BUDGETS = "SELECT * FROM budgets;";
    public static final String SELECT_BUDGETS_BY_ACCOUNT_ID = "SELECT * FROM budgets WHERE account_id = ?;";
    public static final String UPDATE_BUDGET_SPENT = "UPDATE budgets SET spent = ? WHERE id = ?;";
    //BUDGET CATEGORY
    public static final String SELECT_BUDGET_CATEGORY_BY_ID = "SELECT * FROM budget_categories WHERE id = ?;";
    public static final String SELECT_BUDGET_CATEGORIES = "SELECT * FROM budget_categories;";
    public static final String SELECT_BUDGET_CATEGORIES_BY_BUDGET_ID = "SELECT * FROM budget_categories WHERE budget_id = ?;";
    public static final String SELECT_BUDGET_CATEGORIES_BY_CATEGORY_ID = "SELECT * FROM budget_categories WHERE category_id = ?;";
    //REPORT
    public static final String SELECT_REPORT_BY_ID = "SELECT * FROM reports WHERE id = ?;";
    public static final String SELECT_REPORTS = "SELECT * FROM reports;";
    public static final String SELECT_REPORTS_BY_BUDGET_ID = "SELECT * FROM reports WHERE budget_id = ?;";
    //DEPOSIT
    public static final String SELECT_DEPOSIT_BY_ID = "SELECT * FROM deposits WHERE id = ?;";
    public static final String SELECT_DEPOSITS = "SELECT * FROM deposits;";
    public static final String SELECT_DEPOSITS_BY_ACCOUNT_ID = "SELECT * FROM deposits WHERE account_id = ?;";

    //RESOURCE
    public static final String SELECT_ACCOUNT_USER_ID_BY_ID = "SELECT user_id FROM accounts WHERE id = ?;";
    public static final String SELECT_TRANSACTION_USER_ID_BY_ID = "SELECT user_id FROM accounts WHERE id = (SELECT account_id FROM transactions WHERE id = ?);";
    public static final String SELECT_BUDGET_USER_ID_BY_ID = "SELECT user_id FROM accounts WHERE id = (SELECT account_id FROM budgets WHERE id = ?);";
    public static final String SELECT_REPORT_USER_ID_BY_ID = "SELECT user_id FROM accounts WHERE id = (SELECT account_id FROM budgets WHERE id = (SELECT budget_id FROM reports WHERE id = ?));";
    public static final String SELECT_DEPOSIT_USER_ID_BY_ID = "SELECT user_id FROM accounts WHERE id = (SELECT account_id FROM deposits WHERE id = ?);";
}