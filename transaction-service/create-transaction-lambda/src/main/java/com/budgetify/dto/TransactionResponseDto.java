package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponseDto {
    private Integer id;
    private Integer accountId;
    private String accountName;
    private Double accountBalanceBefore;
    private Double accountBalanceAfter;
    private String settledAt;
    private Double amount;
    private String description;
    private String categoryName;
    private String categoryDescription;
    private String type;
}
