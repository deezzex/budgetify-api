package com.budgetify.service;

import com.budgetify.dao.AccountDao;
import com.budgetify.dao.DepositDao;
import com.budgetify.entity.Account;
import com.budgetify.entity.Deposit;
import com.budgetify.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class DepositService {
    private final DepositDao depositDao;
    private final AccountDao accountDao;

    public void closeDeposit(Integer id) {
        Deposit deposit = depositDao.findById(id);
        Account account = accountDao.findById(deposit.getAccountId());

        if (LocalDate.now().isBefore(deposit.getClosedAt())) {
            throw new ApiException("Deposit cannot be closed before specified date.");
        }
        if (!deposit.getStatus().equals("A")) {
            throw new ApiException("Deposit has already closed.");
        }

        account.setBalance(account.getBalance().add(deposit.getAmount()));
        deposit.setStatus("C");

        accountDao.update(account);
        depositDao.update(deposit);
    }
}