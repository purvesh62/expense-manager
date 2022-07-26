package com.expensify.model;

import com.expensify.persistenceLayer.IBudgetDAOService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IBudget {
    List<IBudget> getAllBudgetDetailsService(int user_id, String startDate, String endDate);

    boolean saveBudget();

    boolean updateBudget();

    boolean deleteBudget(int budgetId);

    IBudget getBudgetById(int budgetId);

    void checkIfBudgetLimitExceeds(int userId,int walletId, String expenseDate) throws SQLException, ParseException;

    IBudgetDAOService getBudgetDAOService();

    boolean checkIfBudgetExists(int budgetId, int userId, int walletId, String month);
}
