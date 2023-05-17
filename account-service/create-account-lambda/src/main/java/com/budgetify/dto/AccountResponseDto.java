package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class AccountResponseDto {
    private Integer id;
    private String user;
    private String name;
    private Double balance;
    private String createdAt;
    private String currency;
}
