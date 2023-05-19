package com.budgetify.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Transaction {
    private Integer id;
    private Integer accountId;
    private LocalDateTime settledAt;
    private BigDecimal amount;
    private String description;
    private Integer categoryId;
}
