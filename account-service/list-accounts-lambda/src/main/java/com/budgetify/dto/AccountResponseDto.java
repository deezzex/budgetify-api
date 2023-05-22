package com.budgetify.dto;

import com.budgetify.constant.Formatter;
import com.budgetify.entity.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponseDto {
    private Integer id;
    private Integer userId;
    private String name;
    private Double balance;
    private String createdAt;
    private Integer currencyId;

    public static AccountResponseDto mapFrom(Account account) {
        return AccountResponseDto.builder()
                .id(account.getId())
                .userId(account.getUserId())
                .name(account.getName())
                .balance(account.getBalance().doubleValue())
                .createdAt(account.getCreatedAt().format(Formatter.DATE_FORMATTER))
                .currencyId(account.getCurrencyId())
                .build();
    }
}
