package com.budgetify.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Country {
    private Integer id;
    private String name;
}
