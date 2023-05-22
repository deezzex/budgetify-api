package com.budgetify.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Report {
    private Integer id;
    private Integer budgetId;
    private LocalDateTime createdAt;
    private BigDecimal avgSpent;
    private BigDecimal mediumSpent;
    private BigDecimal maxSpent;
    private BigDecimal minSpent;
    private Integer numberOfTransactions;
}
