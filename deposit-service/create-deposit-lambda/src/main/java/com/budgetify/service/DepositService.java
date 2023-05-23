package com.budgetify.service;

import com.budgetify.constant.Formatter;
import com.budgetify.dao.AccountDao;
import com.budgetify.dao.BaseCurrencyDao;
import com.budgetify.dao.DepositDao;
import com.budgetify.dto.DepositCreateDto;
import com.budgetify.dto.DepositResponseDto;
import com.budgetify.entity.Account;
import com.budgetify.entity.Currency;
import com.budgetify.entity.Deposit;
import com.budgetify.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
public class DepositService {
    private final DepositDao depositDao;
    private final AccountDao accountDao;
    private final BaseCurrencyDao currencyDao;

    public DepositResponseDto createDeposit(DepositCreateDto depositCreateDto) {
        Account account = accountDao.findById(depositCreateDto.getAccountId());
        Currency currency = currencyDao.findById(account.getCurrencyId());

        checkAccountBalance(depositCreateDto, account);

        Deposit deposit = buildDeposit(depositCreateDto, account);

        int depositId = depositDao.save(deposit);

        account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(depositCreateDto.getAmount())));

        accountDao.update(account);

        return DepositResponseDto.builder()
                .depositId(depositId)
                .accountId(account.getId())
                .accountName(account.getName())
                .startedAt(deposit.getStartedAt().format(Formatter.DATE_FORMATTER))
                .closedAt(deposit.getClosedAt().format(Formatter.DATE_FORMATTER))
                .amount(deposit.getAmount().doubleValue())
                .status(deposit.getStatus())
                .currencyName(currency.getName())
                .build();
    }

    private void checkAccountBalance(DepositCreateDto depositCreateDto, Account account) {
        if (depositCreateDto.getAmount() > account.getBalance().doubleValue()) {
            throw new ApiException("Deposit amount greater than account balance.");
        }
    }

    private Deposit buildDeposit(DepositCreateDto depositCreateDto, Account account) {
        return Deposit.builder()
                .accountId(account.getId())
                .startedAt(LocalDate.now())
                .closedAt(LocalDate.now().plusDays(depositCreateDto.getNumberOfDays()))
                .amount(BigDecimal.valueOf(depositCreateDto.getAmount()))
                .status("A")
                .currencyId(account.getCurrencyId())
                .build();
    }
}