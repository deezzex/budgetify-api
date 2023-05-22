package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportResponseDto {
    private Integer reportId;
    private Integer budgetId;
    private String budgetName;
    private String createdAt;
    private Double avgSpent;
    private Double mediumSpent;
    private Double maxSpent;
    private Double minSpent;
    private Integer numberOfTransactions;
}