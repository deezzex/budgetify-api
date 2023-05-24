package com.budgetify.dao;

import com.budgetify.entity.Category;
import com.budgetify.entity.mapper.CategoryMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CategoryDao extends BaseCategoryDao {
    public CategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    public int save(Category category) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("categories")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("name", category.getName());
        parameters.put("description", category.getDescription());
        parameters.put("type", category.getType());

        Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);

        return generatedId.intValue();
    }

    public Optional<Category> findByName(String name) {
        List<Category> categories = jdbcTemplate.query("SELECT * FROM categories WHERE name = ?;", new CategoryMapper(), name);

        if (categories.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(categories.get(0));
    }
}