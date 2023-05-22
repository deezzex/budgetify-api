package com.budgetify.dao;

import com.budgetify.constant.SQLQuery;
import com.budgetify.entity.Category;
import com.budgetify.entity.mapper.CategoryMapper;
import com.budgetify.exception.ApiException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;


public class BaseCategoryDao {
    protected final JdbcTemplate jdbcTemplate;

    public BaseCategoryDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Category findById(Integer id) {
        List<Category> countries = jdbcTemplate.query(SQLQuery.SELECT_CATEGORY_BY_ID, new CategoryMapper(), id);

        if (countries.isEmpty()) {
            throw new ApiException(String.format("Category with id: %d not found.", id));
        }

        return countries.get(0);
    }

    public List<Category> findAll() {
        return jdbcTemplate.query(SQLQuery.SELECT_CATEGORIES, new CategoryMapper());
    }
}
