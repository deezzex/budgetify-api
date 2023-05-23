package com.budgetify.dto;

import com.budgetify.constant.Formatter;
import com.budgetify.entity.Deposit;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepositResponseDto {
    private Integer depositId;
    private Integer accountId;
    private String startedAt;
    private String closedAt;
    private Double amount;
    private String status;
    private Integer currencyId;

    public static DepositResponseDto mapFrom(Deposit deposit) {
        return DepositResponseDto.builder()
                .depositId(deposit.getId())
                .accountId(deposit.getAccountId())
                .startedAt(deposit.getStartedAt().format(Formatter.DATE_FORMATTER))
                .closedAt(deposit.getClosedAt().format(Formatter.DATE_FORMATTER))
                .amount(deposit.getAmount().doubleValue())
                .status(deposit.getStatus())
                .currencyId(deposit.getCurrencyId())
                .build();
    }
}