package com.expensify.factories;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.ExpenseCategory;
import com.expensify.model.IExpenseCategory;
import com.expensify.persistenceLayer.ExpenseCategoriesDAOService;
import com.expensify.persistenceLayer.IExpenseCategoriesDAOService;

public class ExpenseCategoryFactory implements IExpenseCategoryFactory {

    private static ExpenseCategoryFactory expenseCategoryFactory;

    private ExpenseCategoryFactory() {

    }

    public static ExpenseCategoryFactory instance() {
        if (expenseCategoryFactory == null) {
            expenseCategoryFactory = new ExpenseCategoryFactory();
        }
        return expenseCategoryFactory;
    }

    @Override
    public IExpenseCategory createExpenseCategory() {
        IDatabase database = MySqlDatabase.instance();
        return new ExpenseCategory(createExpenseCategoriesDAOService(database));
    }

    @Override
    public IExpenseCategoriesDAOService createExpenseCategoriesDAOService(IDatabase database) {
        return new ExpenseCategoriesDAOService(database);
    }

    @Override
    public IExpenseCategory createExpenseCategory(int c_id, String expenseCategory) {
        return new ExpenseCategory(c_id, expenseCategory);
    }
}
