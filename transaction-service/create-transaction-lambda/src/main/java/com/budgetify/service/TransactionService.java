package com.budgetify.service;

import com.budgetify.constant.Formatter;
import com.budgetify.dao.AccountDao;
import com.budgetify.dao.BaseCategoryDao;
import com.budgetify.dao.TransactionDao;
import com.budgetify.dto.TransactionCreateDto;
import com.budgetify.dto.TransactionResponseDto;
import com.budgetify.entity.Account;
import com.budgetify.entity.Category;
import com.budgetify.entity.Transaction;
import com.budgetify.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class TransactionService {
    private final TransactionDao transactionDao;
    private final BaseCategoryDao categoryDao;
    private final AccountDao accountDao;
    private final BudgetService budgetService;

    public TransactionResponseDto createTransaction(TransactionCreateDto transactionCreateDto) {
        Account account = accountDao.findById(transactionCreateDto.getAccountId());
        Category category = categoryDao.findById(transactionCreateDto.getCategoryId());

        BigDecimal accountBalanceBefore = account.getBalance();

        updateAccountBalance(account, transactionCreateDto, category);

        Transaction transaction = buildTransaction(transactionCreateDto);

        int transactionId = transactionDao.save(transaction);

        transaction.setId(transactionId);

        boolean limitReached = budgetService.updateBudgetSpent(transaction);

        return TransactionResponseDto.builder()
                .statusMessage(limitReached ? "Budget limit is reached." : "Success.")
                .id(transactionId)
                .accountId(account.getId())
                .accountName(account.getName())
                .accountBalanceBefore(accountBalanceBefore.doubleValue())
                .accountBalanceAfter(account.getBalance().doubleValue())
                .settledAt(transaction.getSettledAt().format(Formatter.DATETIME_FORMATTER))
                .amount(transaction.getAmount().doubleValue())
                .description(transaction.getDescription())
                .categoryName(category.getName())
                .categoryDescription(category.getDescription())
                .type(category.getType())
                .build();
    }

    private void updateAccountBalance(Account account, TransactionCreateDto transactionCreateDto, Category category) {
        if (category.getType().equals("C")) {
            account.setBalance(account.getBalance().add(transactionCreateDto.getAmount()));
        } else if (category.getType().equals("D")) {
            account.setBalance(account.getBalance().subtract(transactionCreateDto.getAmount()));
        } else {
            throw new ApiException("Invalid transaction type.");
        }

        accountDao.update(account);
    }

    private Transaction buildTransaction(TransactionCreateDto transactionCreateDto) {
        return Transaction.builder()
                .accountId(transactionCreateDto.getAccountId())
                .amount(transactionCreateDto.getAmount())
                .description(transactionCreateDto.getDescription())
                .categoryId(transactionCreateDto.getCategoryId())
                .settledAt(LocalDateTime.now())
                .build();
    }
}