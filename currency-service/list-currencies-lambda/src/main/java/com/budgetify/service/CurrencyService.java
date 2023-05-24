package com.budgetify.service;

import com.budgetify.dao.BaseCurrencyDao;
import com.budgetify.dto.CurrencyResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CurrencyService {
    private final BaseCurrencyDao currencyDao;

    public List<CurrencyResponseDto> getAllCurrencies() {
        return currencyDao.findAll()
                .stream()
                .map(CurrencyResponseDto::mapFrom)
                .collect(Collectors.toList());
    }
}