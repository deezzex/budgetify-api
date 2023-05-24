package com.budgetify.service;

import com.budgetify.dao.CurrencyDao;
import com.budgetify.dto.CurrencyCreateDto;
import com.budgetify.dto.CurrencyResponseDto;
import com.budgetify.entity.Currency;
import com.budgetify.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyDao currencyDao;

    public CurrencyResponseDto createCurrency(CurrencyCreateDto currencyCreateDto) {
        Optional<Currency> currencyOptional = currencyDao.findByName(currencyCreateDto.getName());

        if (currencyOptional.isPresent()) {
            throw new ApiException(String.format("Currency: %s already exists.", currencyCreateDto.getName()));
        }

        Currency currency = buildCurrency(currencyCreateDto);

        int currencyId = currencyDao.save(currency);

        return CurrencyResponseDto.builder()
                .id(currencyId)
                .name(currency.getName())
                .build();
    }

    private Currency buildCurrency(CurrencyCreateDto currencyCreateDto) {
        return Currency.builder()
                .name(currencyCreateDto.getName())
                .build();
    }
}