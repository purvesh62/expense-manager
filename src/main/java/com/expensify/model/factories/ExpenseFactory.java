package com.expensify.model.factories;

import com.expensify.database.IDatabase;
import com.expensify.model.Expense;
import com.expensify.model.IExpense;
import com.expensify.persistenceLayer.ExpenseDAOService;
import com.expensify.persistenceLayer.IExpenseDOAService;

public class ExpenseFactory implements IExpenseFactory {
    @Override
    public IExpense createExpense(IDatabase database) {
        return new Expense(createExpenseDAOService(database));
    }

    @Override
    public IExpense createExpense(int expenseID, int userID, String title, String description, Float amount, int expenseCategory, int walletID, String expenseDate, String expenseCategoryName) {
        return new Expense(expenseID, userID, title, description, amount, expenseCategory, walletID, expenseDate, expenseCategoryName);
    }

    @Override
    public IExpenseDOAService createExpenseDAOService(IDatabase database) {
        return new ExpenseDAOService(database);
    }
}
