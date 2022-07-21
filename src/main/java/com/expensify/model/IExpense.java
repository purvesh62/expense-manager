package com.expensify.model;

import com.expensify.persistenceLayer.IExpenseDOAService;

import java.util.List;

public interface IExpense {

    IExpenseDOAService getExpenseDOAService();

    List<IExpense> getAllUserExpenses(int userId, String startDate, String endDate);

    boolean addUserExpense();

    boolean deleteUserExpense(int expenseID);
}
