package com.budgetify.service;

import com.budgetify.constant.Formatter;
import com.budgetify.dao.BaseAccountDao;
import com.budgetify.dao.BaseCategoryDao;
import com.budgetify.dao.BaseTransactionDao;
import com.budgetify.dto.TransactionResponseDto;
import com.budgetify.entity.Account;
import com.budgetify.entity.Category;
import com.budgetify.entity.Transaction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransactionService {
    private final BaseTransactionDao transactionDao;
    private final BaseCategoryDao categoryDao;
    private final BaseAccountDao accountDao;

    public TransactionResponseDto getTransaction(Integer id) {
        Transaction transaction = transactionDao.findById(id);
        Account account = accountDao.findById(transaction.getAccountId());
        Category category = categoryDao.findById(transaction.getCategoryId());

        return TransactionResponseDto.builder()
                .id(transaction.getId())
                .accountId(account.getId())
                .accountName(account.getName())
                .accountBalanceAfter(account.getBalance().doubleValue())
                .amount(transaction.getAmount().doubleValue())
                .settledAt(transaction.getSettledAt().format(Formatter.DATETIME_FORMATTER))
                .description(transaction.getDescription())
                .categoryName(category.getName())
                .categoryDescription(category.getDescription())
                .type(category.getType())
                .build();
    }
}
