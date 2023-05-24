package com.budgetify.service;

import com.budgetify.dao.CountryDao;
import com.budgetify.dto.CountryCreateDto;
import com.budgetify.dto.CountryResponseDto;
import com.budgetify.entity.Category;
import com.budgetify.entity.Country;
import com.budgetify.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CountryService {
    private final CountryDao countryDao;

    public CountryResponseDto createCountry(CountryCreateDto countryCreateDto) {
        Optional<Country> countryOptional = countryDao.findByName(countryCreateDto.getName());

        if (countryOptional.isPresent()) {
            throw new ApiException(String.format("Country: %s already exists.", countryCreateDto.getName()));
        }

        Country country = buildCountry(countryCreateDto);

        int countryId = countryDao.save(country);

        return CountryResponseDto.builder()
                .id(countryId)
                .name(country.getName())
                .build();
    }

    private Country buildCountry(CountryCreateDto countryCreateDto) {
        return Country.builder()
                .name(countryCreateDto.getName())
                .build();
    }
}