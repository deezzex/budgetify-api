package com.budgetify.service;

import com.budgetify.constant.Formatter;
import com.budgetify.dao.BaseAccountDao;
import com.budgetify.dao.BaseCurrencyDao;
import com.budgetify.dao.BaseDepositDao;
import com.budgetify.dto.DepositResponseDto;
import com.budgetify.entity.Account;
import com.budgetify.entity.Currency;
import com.budgetify.entity.Deposit;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DepositService {
    private final BaseDepositDao depositDao;
    private final BaseAccountDao accountDao;
    private final BaseCurrencyDao currencyDao;

    public DepositResponseDto getDeposit(Integer id) {
        Deposit deposit = depositDao.findById(id);
        Account account = accountDao.findById(deposit.getAccountId());
        Currency currency = currencyDao.findById(account.getCurrencyId());

        return DepositResponseDto.builder()
                .depositId(deposit.getId())
                .accountId(account.getId())
                .accountName(account.getName())
                .startedAt(deposit.getStartedAt().format(Formatter.DATE_FORMATTER))
                .closedAt(deposit.getClosedAt().format(Formatter.DATE_FORMATTER))
                .amount(deposit.getAmount().doubleValue())
                .status(deposit.getStatus())
                .currencyName(currency.getName())
                .build();
    }
}