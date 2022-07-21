package com.expensify.model;

import com.expensify.persistenceLayer.IBudgetDAOService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IBudget {
    List<IBudget> getAllBudgetDetailsService(int user_id, String startDate, String endDate) throws SQLException;

    boolean saveBudget() throws SQLException;

    boolean updateBudget() throws SQLException;

    boolean deleteBudget(int budgetId) throws SQLException;

    IBudget getBudgetById(int budgetId) throws SQLException;

    void checkIfBudgetLimitExceeds(Expense expense) throws SQLException, ParseException;

    IBudgetDAOService getBudgetDAOService();
}
