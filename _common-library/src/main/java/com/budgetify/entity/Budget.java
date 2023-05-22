package com.budgetify.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class Budget {
    private Integer id;
    private Integer accountId;
    private String name;
    private String description;
    private BigDecimal limit;
    private LocalDate startedAt;
    private LocalDate closedAt;
    private BigDecimal spent;
}
