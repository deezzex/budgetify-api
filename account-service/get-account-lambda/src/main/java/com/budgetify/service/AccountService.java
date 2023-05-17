package com.budgetify.service;

import com.budgetify.conts.Formatter;
import com.budgetify.dao.BaseAccountDao;
import com.budgetify.dao.BaseCurrencyDao;
import com.budgetify.dao.BaseUserDao;
import com.budgetify.dto.AccountResponseDto;
import com.budgetify.entity.Account;
import com.budgetify.entity.Currency;
import com.budgetify.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountService {
    private final BaseAccountDao accountDao;
    private final BaseUserDao baseUserDao;
    private final BaseCurrencyDao currencyDao;

    public AccountResponseDto getAccount(Integer id) {
        Account account = accountDao.findById(id);
        User user = baseUserDao.findById(account.getUserId());
        Currency currency = currencyDao.findById(account.getCurrencyId());

        return AccountResponseDto.builder()
                .id(account.getId())
                .name(account.getName())
                .user(user.getEmail())
                .currency(currency.getName())
                .createdAt(account.getCreatedAt().format(Formatter.DATE_FORMATTER))
                .balance(account.getBalance().doubleValue())
                .build();
    }
}
