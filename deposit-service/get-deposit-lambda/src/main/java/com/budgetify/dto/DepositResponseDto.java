package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepositResponseDto {
    private Integer depositId;
    private Integer accountId;
    private String accountName;
    private String startedAt;
    private String closedAt;
    private Double amount;
    private String status;
    private String currencyName;
}
