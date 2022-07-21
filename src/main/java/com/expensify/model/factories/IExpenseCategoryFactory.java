package com.expensify.model.factories;
import com.expensify.database.IDatabase;
import com.expensify.model.IExpenseCategory;
import com.expensify.persistenceLayer.IExpenseCategoriesDAOService;


public interface IExpenseCategoryFactory {

    IExpenseCategory createExpenseCategory();
    //     IExpenseCategory createExpenseCategory();
    IExpenseCategoriesDAOService createExpenseCategoriesDAOService(IDatabase database);

    IExpenseCategory createExpenseCategory(int c_id, String expenseCategory);
}
