package com.budgetify.service;

import com.budgetify.dao.AccountDao;
import com.budgetify.dto.AccountResponseDto;
import com.budgetify.entity.Account;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AccountService {
    private final AccountDao accountDao;

    public List<AccountResponseDto> getAllAccounts() {
        List<Account> accounts = accountDao.findAll();

        return accounts.stream()
                .map(AccountResponseDto::mapFrom)
                .collect(Collectors.toList());
    }

    public List<AccountResponseDto> getAllAccountsByUserId(int userId) {
        List<Account> accounts = accountDao.findAllAccountByUserId(userId);

        return accounts.stream()
                .map(AccountResponseDto::mapFrom)
                .collect(Collectors.toList());
    }
}
