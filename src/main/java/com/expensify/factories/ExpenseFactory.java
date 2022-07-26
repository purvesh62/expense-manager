package com.expensify.factories;

import com.expensify.Validators.ExpenseValidator;
import com.expensify.database.MySqlDatabase;
import com.expensify.database.IDatabase;
import com.expensify.model.Expense;
import com.expensify.model.ExpenseAnalytics;
import com.expensify.model.IAnalytics;
import com.expensify.model.IExpense;
import com.expensify.persistenceLayer.ExpenseDAOService;
import com.expensify.persistenceLayer.IExpenseDOAService;

public class ExpenseFactory implements IExpenseFactory {

    private static ExpenseFactory expenseFactory;

    private ExpenseFactory() {

    }

    public static ExpenseFactory instance() {
        if (expenseFactory == null) {
            expenseFactory = new ExpenseFactory();
        }
        return expenseFactory;
    }

    @Override
    public IExpense createExpense() {
        IDatabase database = MySqlDatabase.instance();
        return new Expense(createExpenseDAOService(database));
    }

    public IAnalytics createExpenseAnalytics() {
        IDatabase database = MySqlDatabase.instance();
        return new ExpenseAnalytics(createExpenseDAOService(database));
    }

    @Override
    public IExpense createExpense(int expenseID, int userID, String title, String description, Float amount, int expenseCategory, int walletID, String expenseDate, String expenseCategoryName) {
        return new Expense(expenseID, userID, title, description, amount, expenseCategory, walletID, expenseDate, expenseCategoryName);
    }

    @Override
    public ExpenseValidator createExpenseValidator() {
        return new ExpenseValidator();
    }

    @Override
    public IExpenseDOAService createExpenseDAOService(IDatabase database) {
        return new ExpenseDAOService(database);
    }
}
