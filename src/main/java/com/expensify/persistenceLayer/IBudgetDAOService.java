package com.expensify.persistenceLayer;

import com.expensify.model.Budget;
import com.expensify.model.IBudget;

import java.sql.SQLException;
import java.util.List;

public interface IBudgetDAOService {
    List<IBudget> getAllBudgetDetails(int userId, String startDate, String endDate) throws SQLException;
    void addNewBudget(IBudget newBudget) throws SQLException;
    void updateBudget(IBudget budget) throws SQLException;
    void deleteBudget(int budgetId) throws SQLException;
    IBudget getBudgetById(int budgetId) throws SQLException;

}
