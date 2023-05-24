package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponseDto {
    private Integer id;
    private String name;
    private String description;
    private String type;
}