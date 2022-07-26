package com.expensify.persistenceLayer;

import com.expensify.model.IBudget;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface IBudgetDAOService {
    List<IBudget> getAllBudgetDetails(int userId, String startDate, String endDate) throws SQLException;

    boolean addNewBudget(int walletId, int userId, float budgetLimit, String month) throws SQLException;

    boolean updateBudget(int budgetId, int walletId, float budgetLimit) throws SQLException;

    boolean deleteBudget(int budgetId) throws SQLException;

    IBudget getBudgetById(int budgetId) throws SQLException;

    int checkIfBudgetLimitExceeds(int userId,int walletId, String expenseDate) throws ParseException, SQLException;

    boolean checkIfBudgetExists(int budgetId, int userId, int walletId, String month);
}
