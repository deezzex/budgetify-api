package com.budgetify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportCreateDto {
    private Integer budgetId;
    private String createdAt;
}