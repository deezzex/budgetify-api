package com.budgetify.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class Deposit {
    private Integer id;
    private Integer accountId;
    private LocalDate startedAt;
    private LocalDate closedAt;
    private BigDecimal amount;
    private String status;
    private Integer currencyId;
}
