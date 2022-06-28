package com.expensify.services;

import com.expensify.model.Budget;
import com.expensify.persistenceLayer.BudgetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BudgetService {
    private final BudgetDAO budgetDAO;

    @Autowired
    public BudgetService(BudgetDAO budgetDAO) {
        this.budgetDAO = budgetDAO;
    }

    public List<Budget> getAllBudgetDetailsService(int user_id) throws SQLException {
        return budgetDAO.getAllBudgetDetails(user_id);
    }
}
