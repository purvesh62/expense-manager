package com.expensify.persistenceLayer;

import com.expensify.model.IExpense;

import java.util.HashMap;
import java.util.List;

public interface IExpenseDOAService {
    List<IExpense> getAllUserExpenses(int userId, String startDate, String endDate);

    boolean addUserExpenses(int userId, String expenseTitle, String description, Float amount, int expenseCategory, int walletId, String expenseDate);

    boolean deleteUserExpense(int expenseId);

    HashMap<Integer, Float> getMonthlyExpense(int userId, String startDate, String endDate);

    HashMap<String, Float> getMonthlyAnalyticsByCategories(int userId, String startDate, String endDate);
}
