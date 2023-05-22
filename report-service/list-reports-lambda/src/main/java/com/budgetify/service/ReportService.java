package com.budgetify.service;

import com.budgetify.dao.BaseReportDao;
import com.budgetify.dto.ReportResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReportService {
    private final BaseReportDao reportDao;

    public List<ReportResponseDto> getAllReports() {
        return reportDao.findAll().stream().map(ReportResponseDto::mapFrom).collect(Collectors.toList());
    }

    public List<ReportResponseDto> getAllReportsByBudgetId(Integer budgetId) {
        return reportDao.findAllByBudgetId(budgetId).stream().map(ReportResponseDto::mapFrom).collect(Collectors.toList());
    }
}