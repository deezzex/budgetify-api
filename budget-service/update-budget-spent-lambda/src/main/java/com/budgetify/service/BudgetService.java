package com.budgetify.service;

import com.budgetify.dao.BaseBudgetCategoryDao;
import com.budgetify.dao.BaseTransactionDao;
import com.budgetify.dao.BudgetDao;
import com.budgetify.dto.TransactionDto;
import com.budgetify.entity.Budget;
import com.budgetify.entity.BudgetCategory;
import com.budgetify.entity.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class BudgetService {
    private final BudgetDao budgetDao;
    private final BaseTransactionDao transactionDao;
    private final BaseBudgetCategoryDao baseBudgetCategoryDao;

    public void updateBudgetSpent(TransactionDto transactionDto) {
        log.info("Transaction: {}", transactionDto);

        Transaction transaction = transactionDao.findById(transactionDto.getId());
        List<Budget> budgets = budgetDao.findAllByAccountId(transaction.getAccountId());
        List<BudgetCategory> budgetCategories = baseBudgetCategoryDao.findAllByCategoryId(transaction.getCategoryId());

        budgets.forEach(budget -> {
            budgetCategories.forEach(budgetCategory -> {
                if (budget.getId().equals(budgetCategory.getBudgetId())) {
                    LocalDate transactionSettledDate = transaction.getSettledAt().toLocalDate();
                    if (budget.getStartedAt()
                            .isAfter(transactionSettledDate) &&
                            budget.getClosedAt()
                                    .isBefore(transactionSettledDate)){

                        BigDecimal newSpent = budget.getSpent().add(transaction.getAmount());

                        if (newSpent.compareTo(budget.getLimit()) > 0) {
                            //TODO: SEND NOTIFICATION
                            log.info("Limit reached.");
                        }

                        budgetDao.updateSpent(budget, newSpent);
                    }
                }
            });
        });
    }
}