package com.budgetify.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
    private Integer id;
    private String name;
    private String description;
    private String type;
}