package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DepositCreateDto {
    private Integer accountId;
    private Double amount;
    private Integer numberOfDays;
}