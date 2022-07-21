package com.expensify.model;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.factories.IExpenseCategoryFactory;
import com.expensify.persistenceLayer.ExpenseCategoriesDAOService;
import com.expensify.persistenceLayer.IExpenseCategoriesDAOService;
import com.expensify.persistenceLayer.IPaymentCategoriesDAOService;
import com.expensify.persistenceLayer.PaymentCategoriesDAOService;

public class ExpenseCategoryFactory implements IExpenseCategoryFactory {

    private static ExpenseCategoryFactory expenseCategoryFactory;

    public ExpenseCategoryFactory(){

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
