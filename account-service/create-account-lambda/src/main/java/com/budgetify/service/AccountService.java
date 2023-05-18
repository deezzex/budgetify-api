package com.budgetify.service;

import com.budgetify.conts.Formatter;
import com.budgetify.dao.AccountDao;
import com.budgetify.dao.BaseCurrencyDao;
import com.budgetify.dao.BaseUserDao;
import com.budgetify.dto.AccountResponseDto;
import com.budgetify.dto.AccountSaveDto;
import com.budgetify.entity.Currency;
import com.budgetify.entity.User;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
public class AccountService {
    private final AccountDao accountDao;
    private final BaseCurrencyDao currencyDao;
    private final BaseUserDao baseUserDao;

    public AccountResponseDto createAccount(AccountSaveDto accountSaveDto) {
        Currency currency = currencyDao.findById(accountSaveDto.getCurrencyId());
        User user = baseUserDao.findById(accountSaveDto.getUserId());

        accountSaveDto.setCreatedAt(LocalDate.now());
        accountSaveDto.setBalance(BigDecimal.ZERO);

        int accountId = accountDao.save(accountSaveDto);

        return AccountResponseDto.builder()
                .id(accountId)
                .name(accountSaveDto.getName())
                .user(user.getEmail())
                .balance(accountSaveDto.getBalance().doubleValue())
                .createdAt(accountSaveDto.getCreatedAt().format(Formatter.DATE_FORMATTER))
                .currency(currency.getName())
                .build();
    }
}
