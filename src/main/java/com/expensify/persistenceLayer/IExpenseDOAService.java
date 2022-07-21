package com.expensify.persistenceLayer;

import com.expensify.model.IExpense;

import java.util.List;

public interface IExpenseDOAService {
    List<IExpense> getAllUserExpenses(int userId, String startDate, String endDate);

    boolean addUserExpenses(int expenseId, int userId, String expenseTitle, String description, Float amount, int expenseCategory, int walletId, String expenseDate);

    boolean deleteUserExpense(int expenseId);
}