package com.budgetify.service;

import com.budgetify.dao.BaseDepositDao;
import com.budgetify.dto.DepositResponseDto;
import com.budgetify.entity.Deposit;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DepositService {
    private final BaseDepositDao depositDao;

    public List<DepositResponseDto> getAllDeposits() {
        List<Deposit> deposits = depositDao.findAll();

        return deposits.stream()
                .map(DepositResponseDto::mapFrom)
                .collect(Collectors.toList());
    }

    public List<DepositResponseDto> getAllDepositsByAccountId(Integer accountId) {
        List<Deposit> deposits = depositDao.findAllByAccountId(accountId);

        return deposits.stream()
                .map(DepositResponseDto::mapFrom)
                .collect(Collectors.toList());
    }
}