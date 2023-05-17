package com.budgetify.config;

import lombok.experimental.UtilityClass;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


@UtilityClass
public class DataSourceConfig {
    private static SingleConnectionDataSource dataSource;

    public static DriverManagerDataSource getDataSource() {
        if (dataSource == null) {
            String endpoint = System.getenv("DB_ENDPOINT");
            String dbname = System.getenv("DB_NAME");
            String username = System.getenv("DB_USERNAME");
            String password = System.getenv("DB_PASSWORD");

            String url = String.format("jdbc:mysql://%s:3306/%s?%s", endpoint, dbname, "zeroDateTimeBehavior=convertToNull&serverTimezone=UTC");

            dataSource = DataSourceBuilder
                    .create()
                    .type(SingleConnectionDataSource.class)
                    .url(url)
                    .username(username)
                    .password(password).build();
        }
        return dataSource;
    }
}
