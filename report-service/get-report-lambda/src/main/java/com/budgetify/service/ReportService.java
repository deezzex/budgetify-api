package com.budgetify.service;

import com.budgetify.constant.Formatter;
import com.budgetify.dao.BaseBudgetDao;
import com.budgetify.dao.BaseReportDao;
import com.budgetify.dto.ReportResponseDto;
import com.budgetify.entity.Budget;
import com.budgetify.entity.Report;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportService {
    private final BaseReportDao reportDao;
    private final BaseBudgetDao budgetDao;

    public ReportResponseDto getReport(Integer id) {
        Report report = reportDao.findById(id);
        Budget budget = budgetDao.findById(report.getBudgetId());


        return ReportResponseDto.builder()
                .reportId(report.getId())
                .budgetId(budget.getId())
                .budgetName(budget.getName())
                .avgSpent(report.getAvgSpent().doubleValue())
                .createdAt(report.getCreatedAt().format(Formatter.DATETIME_FORMATTER))
                .maxSpent(report.getMaxSpent().doubleValue())
                .minSpent(report.getMinSpent().doubleValue())
                .mediumSpent(report.getMediumSpent().doubleValue())
                .numberOfTransactions(report.getNumberOfTransactions())
                .build();
    }
}