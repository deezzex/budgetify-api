package com.budgetify.service;

import com.budgetify.dao.BaseCategoryDao;
import com.budgetify.dto.CategoryResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CategoryService {
    private final BaseCategoryDao categoryDao;

    public List<CategoryResponseDto> getAllCategories() {
        return categoryDao.findAll()
                .stream()
                .map(CategoryResponseDto::mapFrom)
                .collect(Collectors.toList());
    }
}