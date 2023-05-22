package com.budgetify.dto;

import com.budgetify.constant.Formatter;
import com.budgetify.entity.Report;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportResponseDto {
    private Integer reportId;
    private Integer budgetId;
    private String createdAt;
    private Double avgSpent;
    private Double mediumSpent;
    private Double maxSpent;
    private Double minSpent;
    private Integer numberOfTransactions;

    public static ReportResponseDto mapFrom(Report report) {
        return ReportResponseDto.builder()
                .reportId(report.getId())
                .budgetId(report.getBudgetId())
                .createdAt(report.getCreatedAt().format(Formatter.DATETIME_FORMATTER))
                .avgSpent(report.getAvgSpent().doubleValue())
                .mediumSpent(report.getMediumSpent().doubleValue())
                .maxSpent(report.getMaxSpent().doubleValue())
                .minSpent(report.getMinSpent().doubleValue())
                .numberOfTransactions(report.getNumberOfTransactions())
                .build();
    }
}