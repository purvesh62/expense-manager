package com.expensify.services;

import com.expensify.model.Budget;
import com.expensify.persistenceLayer.BudgetDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BudgetService {
    private final BudgetDAOService budgetDAOService;

    @Autowired
    public BudgetService(BudgetDAOService budgetDAOService) {
        this.budgetDAOService = budgetDAOService;
    }

    public List<Budget> getAllBudgetDetailsService(int user_id) throws SQLException {
        return budgetDAOService.getAllBudgetDetails(user_id);
    }

    public void saveBudget(Budget newBudget) throws SQLException {
         budgetDAOService.addNewBudget(newBudget);
    }

    public void updateBudget(Budget budget) throws SQLException {
        budgetDAOService.updateBudget(budget);
    }

    public void deleteBudget(int budgetId) throws SQLException {
        budgetDAOService.deleteBudget(budgetId);
    }
}
