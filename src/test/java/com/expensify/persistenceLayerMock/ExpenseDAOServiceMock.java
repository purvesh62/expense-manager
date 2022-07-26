package com.expensify.persistenceLayerMock;

import com.expensify.model.IExpense;
import com.expensify.factories.ExpenseFactory;
import com.expensify.persistenceLayer.IExpenseDOAService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseDAOServiceMock implements IExpenseDOAService {

    List<IExpense> expenseMockList = new ArrayList<>();

    public void getAllExpenses() {
        IExpense expenseMock1 = ExpenseFactory.instance().createExpense();
        IExpense expenseMock2 = ExpenseFactory.instance().createExpense();
        expenseMockList.add(expenseMock1);
        expenseMockList.add(expenseMock2);
    }

    @Override
    public List<IExpense> getAllUserExpenses(int userId, String startDate, String endDate) {
        getAllExpenses();
        return expenseMockList;
    }

    public List<IExpense> getNullUserExpenses() {
        expenseMockList = null;
        return null;
    }

    @Override
    public boolean addUserExpenses(int userId, String expenseTitle, String description, Float amount, int expenseCategory, int walletId, String expenseDate) {
        if (userId == 0 || expenseTitle.equals("") || amount < 0 || expenseCategory < 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUserExpense(int expenseId) {
        if (expenseId == 2) {
            return false;
        }
        return true;
    }

    @Override
    public HashMap<Integer, Float> getMonthlyExpense(int userId, String startDate, String endDate) {
        HashMap<Integer, Float> expenses = new HashMap<>();
        expenses.put(1, (float) 4.10);
        expenses.put(2, (float) 10.10);
        return expenses;
    }

    public HashMap<Integer, Float> getNullMonthlyExpense(int userId, String startDate, String endDate) {
        return null;
    }

    @Override
    public HashMap<String, Float> getMonthlyAnalyticsByCategories(int userId, String startDate, String endDate) {
        HashMap<String, Float> expenses = new HashMap<>();
        expenses.put("Food", (float) 4.10);
        expenses.put("Shopping", (float) 10.10);
        return expenses;
    }

    public HashMap<String, Float> getNullMonthlyAnalyticsByCategories(int userId, String startDate, String endDate) {
        return null;
    }
}
