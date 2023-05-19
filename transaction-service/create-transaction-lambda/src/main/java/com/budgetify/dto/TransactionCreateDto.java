package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionCreateDto {
    private Integer accountId;
    private BigDecimal amount;
    private String description;
    private Integer categoryId;
}
