package com.budgetify.service;

import com.budgetify.dao.BaseBudgetCategoryDao;
import com.budgetify.dao.BudgetDao;
import com.budgetify.entity.Budget;
import com.budgetify.entity.BudgetCategory;
import com.budgetify.entity.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@RequiredArgsConstructor
public class BudgetService {
    private final BudgetDao budgetDao;
    private final BaseBudgetCategoryDao baseBudgetCategoryDao;

    public boolean updateBudgetSpent(Transaction transaction) {
        AtomicBoolean limitReached = new AtomicBoolean(false);
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

                        budget.setSpent(newSpent);

                        if (budget.getSpent().compareTo(budget.getLimit()) > 0) {
                            limitReached.set(true);
                            log.info("Limit reached.");
                        }

                        budgetDao.updateSpent(budget);
                    }
                }
            });
        });

        return limitReached.get();
    }
}