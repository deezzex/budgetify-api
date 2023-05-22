package com.budgetify.dto;

import com.budgetify.constant.Formatter;
import com.budgetify.entity.Transaction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponseDto {
    private Integer id;
    private Integer accountId;
    private String settledAt;
    private Double amount;
    private String description;
    private Integer categoryId;

    public static TransactionResponseDto mapFrom(Transaction transaction) {
        return TransactionResponseDto.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccountId())
                .settledAt(transaction.getSettledAt().format(Formatter.DATETIME_FORMATTER))
                .amount(transaction.getAmount().doubleValue())
                .description(transaction.getDescription())
                .categoryId(transaction.getCategoryId())
                .build();
    }
}
