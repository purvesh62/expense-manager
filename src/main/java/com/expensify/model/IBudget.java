package com.expensify.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IBudget {
    List<IBudget> getAllBudgetDetailsService(int user_id, String startDate, String endDate) throws SQLException;
    IBudget saveBudget() throws SQLException;
    IBudget updateBudget() throws SQLException;
    void deleteBudget(int budgetId) throws SQLException;
    IBudget getBudgetById(int budgetId) throws SQLException;
    void checkIfBudgetLimitExceeds(Expense expense) throws SQLException, ParseException;
}
