package com.expensify.services;

import com.expensify.model.Expense;
import com.expensify.persistenceLayer.ExpenseDAO;

import java.sql.SQLException;
import java.util.List;

public class ExpenseService {
    private final ExpenseDAO expenseDAO;

    public ExpenseService(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    public List<Expense> getAllUserExpenses(int userID, String startDate, String endDate) throws SQLException {
        return expenseDAO.getAllUserExpenses(userID, startDate, endDate);
    }
}

