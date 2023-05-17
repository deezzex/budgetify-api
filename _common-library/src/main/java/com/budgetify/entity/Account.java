package com.budgetify.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class Account {
    private Integer id;
    private Integer userId;
    private String name;
    private BigDecimal balance;
    private LocalDate createdAt;
    private Integer currencyId;
}
