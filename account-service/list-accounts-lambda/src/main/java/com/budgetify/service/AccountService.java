package com.budgetify.service;

import com.budgetify.dao.BaseAccountDao;
import com.budgetify.dto.AccountResponseDto;
import com.budgetify.entity.Account;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AccountService {
    private final BaseAccountDao accountDao;

    public List<AccountResponseDto> getAllAccounts() {
        List<Account> accounts = accountDao.findAll();

        return accounts.stream()
                .map(AccountResponseDto::mapFrom)
                .collect(Collectors.toList());
    }
}
