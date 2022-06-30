package com.expensify.services;

import com.expensify.model.Expense;
import com.expensify.persistenceLayer.ExpenseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseDAO expenseDAO;

    @Autowired
    public ExpenseService(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    public List<Expense> getAllUserExpenses(int userID, String startDate, String endDate) throws SQLException {
        return expenseDAO.getAllUserExpenses(userID, startDate, endDate);
    }
}

