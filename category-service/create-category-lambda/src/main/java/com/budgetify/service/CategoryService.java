package com.budgetify.service;

import com.budgetify.dao.CategoryDao;
import com.budgetify.dto.CategoryCreateDto;
import com.budgetify.dto.CategoryResponseDto;
import com.budgetify.entity.Category;
import com.budgetify.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CategoryService {
    private final CategoryDao categoryDao;

    public CategoryResponseDto createCategory(CategoryCreateDto categoryCreateDto) {
        Optional<Category> categoryOptional = categoryDao.findByName(categoryCreateDto.getName());

        if (categoryOptional.isPresent()) {
            throw new ApiException(String.format("Category: %s already exists.", categoryCreateDto.getName()));
        }

        Category category = buildCategory(categoryCreateDto);

        int categoryId = categoryDao.save(category);

        return CategoryResponseDto.builder()
                .id(categoryId)
                .name(category.getName())
                .description(category.getDescription())
                .type(category.getType())
                .build();
    }

    private Category buildCategory(CategoryCreateDto categoryCreateDto) {
        return Category.builder()
                .name(categoryCreateDto.getName())
                .description(categoryCreateDto.getDescription())
                .type(categoryCreateDto.getType())
                .build();
    }
}