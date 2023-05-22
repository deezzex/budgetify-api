package com.budgetify.service;

import com.budgetify.constant.Formatter;
import com.budgetify.dao.BaseAccountDao;
import com.budgetify.dao.BaseBudgetDao;
import com.budgetify.dao.ReportDao;
import com.budgetify.dao.TransactionDao;
import com.budgetify.dto.ReportCreateDto;
import com.budgetify.dto.ReportResponseDto;
import com.budgetify.entity.Account;
import com.budgetify.entity.Budget;
import com.budgetify.entity.Report;
import com.budgetify.entity.Transaction;
import com.budgetify.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReportService {
    private final ReportDao reportDao;
    private final BaseBudgetDao budgetDao;
    private final BaseAccountDao accountDao;
    private final TransactionDao transactionDao;

    public ReportResponseDto createReport(ReportCreateDto reportCreateDto) {
        Budget budget = budgetDao.findById(reportCreateDto.getBudgetId());

        Report report = buildReport(reportCreateDto, budget);

        int reportId = reportDao.save(report);

        return ReportResponseDto.builder()
                .reportId(reportId)
                .budgetId(budget.getId())
                .budgetName(budget.getName())
                .avgSpent(report.getAvgSpent().doubleValue())
                .createdAt(reportCreateDto.getCreatedAt())
                .maxSpent(report.getMaxSpent().doubleValue())
                .minSpent(report.getMinSpent().doubleValue())
                .mediumSpent(report.getMediumSpent().doubleValue())
                .numberOfTransactions(report.getNumberOfTransactions())
                .build();
    }

    private Report buildReport(ReportCreateDto reportCreateDto, Budget budget) {
        Account account = accountDao.findById(budget.getAccountId());
        List<Transaction> transactions = transactionDao.findAllByAccountId(account.getId());
        LocalDateTime reportCreatedAt = LocalDateTime.parse(reportCreateDto.getCreatedAt(), Formatter.DATETIME_FORMATTER);

        transactions = transactions.stream()
                .filter(transaction -> transaction.getSettledAt().isBefore(reportCreatedAt))
                .collect(Collectors.toList());

        if (transactions.isEmpty()) {
            throw new ApiException("No transactions to report.");
        }

        Optional<BigDecimal> min = transactions.stream().map(Transaction::getAmount).min(BigDecimal::compareTo);
        Optional<BigDecimal> max = transactions.stream().map(Transaction::getAmount).max(BigDecimal::compareTo);

        OptionalDouble average = transactions.stream()
                .map(Transaction::getAmount)
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(BigDecimal::doubleValue)
                .average();

        return Report.builder()
                .budgetId(reportCreateDto.getBudgetId())
                .createdAt(reportCreatedAt)
                .avgSpent(BigDecimal.valueOf(average.getAsDouble()))
                .mediumSpent(BigDecimal.valueOf(findMiddle(transactions)))
                .maxSpent(max.get())
                .minSpent(min.get())
                .numberOfTransactions(transactions.size())
                .build();
    }

    private double findMiddle(List<Transaction> transactions) {
        List<BigDecimal> amounts = transactions.stream()
                .map(Transaction::getAmount)
                .collect(Collectors.toList())
                .stream()
                .sorted()
                .collect(Collectors.toList());

        int size = amounts.size();
        double median;

        int middleIndex = size / 2;

        if (size % 2 == 0) {
            BigDecimal middleValue1 = amounts.get(middleIndex - 1);
            BigDecimal middleValue2 = amounts.get(middleIndex);
            return middleValue1.add(middleValue2).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP).doubleValue();
        } else {
            return amounts.get(middleIndex).doubleValue();
        }
    }
}