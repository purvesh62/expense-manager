package com.expensify.persistenceLayerMock;

import com.expensify.model.IExpense;
import com.expensify.model.factories.ExpenseFactory;
import com.expensify.persistenceLayer.IExpenseDOAService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseDAOServiceMock implements IExpenseDOAService {
    @Override
    public List<IExpense> getAllUserExpenses(int userId, String startDate, String endDate) {
        List<IExpense> expenseMockList = new ArrayList<>();
        IExpense expenseMock1 = ExpenseFactory.instance().createExpense();
        IExpense expenseMock2 = ExpenseFactory.instance().createExpense();
        expenseMockList.add(expenseMock1);
        expenseMockList.add(expenseMock2);
        return expenseMockList;
    }

    @Override
    public boolean addUserExpenses(int expenseId, int userId, String expenseTitle, String description, Float amount, int expenseCategory, int walletId, String expenseDate) {
        return true;
    }

    @Override
    public boolean deleteUserExpense(int expenseId) {
        return true;
    }

    @Override
    public HashMap<Integer, Float> getMonthlyExpense(int userId, String startDate, String endDate) {
        return null;
    }

    @Override
    public HashMap<String, Float> getMonthlyAnalyticsByCategories(int userId, String startDate, String endDate) {
        return null;
    }
}
