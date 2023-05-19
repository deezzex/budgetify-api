package com.budgetify.service;

import com.budgetify.dao.TransactionDao;
import com.budgetify.dto.TransactionResponseDto;
import com.budgetify.entity.Transaction;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TransactionService {
    private final TransactionDao transactionDao;

    public List<TransactionResponseDto> getAllTransactions() {
        List<Transaction> transactions = transactionDao.findAll();

        return transactions.stream()
                .map(TransactionResponseDto::mapFrom)
                .collect(Collectors.toList());
    }

    public List<TransactionResponseDto> getAllTransactionsByAccountId(Integer accountId) {
        List<Transaction> transactions = transactionDao.findAllByAccountId(accountId);

        return transactions.stream()
                .map(TransactionResponseDto::mapFrom)
                .collect(Collectors.toList());
    }
}
