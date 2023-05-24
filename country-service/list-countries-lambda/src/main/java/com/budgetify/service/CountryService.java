package com.budgetify.service;

import com.budgetify.dao.BaseCountryDao;
import com.budgetify.dto.CountryResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CountryService {
    private final BaseCountryDao countryDao;

    public List<CountryResponseDto> getAllCountries() {
        return countryDao.findAll()
                .stream()
                .map(CountryResponseDto::mapFrom)
                .collect(Collectors.toList());
    }
}