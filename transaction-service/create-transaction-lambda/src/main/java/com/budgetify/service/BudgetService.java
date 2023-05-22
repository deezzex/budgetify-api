package com.budgetify.service;

import com.budgetify.dao.BaseBudgetCategoryDao;
import com.budgetify.dao.BaseTransactionDao;
import com.budgetify.dao.BudgetDao;
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
    private final BaseBudgetCategoryDao baseBudgetCategoryDao;

    public void updateBudgetSpent(Transaction transaction) {
        List<Budget> budgets = budgetDao.findAllByAccountId(transaction.getAccountId());
        List<BudgetCategory> budgetCategories = baseBudgetCategoryDao.findAllByCategoryId(transaction.getCategoryId());
        log.info("Transaction: {}", transaction);
        budgets.forEach(budget -> {
            log.info("Budget: {}", budget);
            budgetCategories.forEach(budgetCategory -> {
                log.info("Budget category: {}", budgetCategory);
                if (budget.getId().equals(budgetCategory.getBudgetId())) {
                    log.info("Match...");
                    LocalDate transactionSettledDate = transaction.getSettledAt().toLocalDate();
                    if (budget.getStartedAt()
                            .isBefore(transactionSettledDate) &&
                            budget.getClosedAt()
                                    .isAfter(transactionSettledDate)) {
                        log.info("Update...");
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