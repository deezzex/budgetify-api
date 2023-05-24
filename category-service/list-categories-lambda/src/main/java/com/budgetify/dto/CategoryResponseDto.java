package com.budgetify.dto;

import com.budgetify.entity.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponseDto {
    private Integer id;
    private String name;
    private String description;
    private String type;

    public static CategoryResponseDto mapFrom(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .type(category.getType())
                .build();
    }
}