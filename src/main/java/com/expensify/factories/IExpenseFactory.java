package com.expensify.factories;

import com.expensify.Validators.ExpenseValidator;
import com.expensify.database.IDatabase;
import com.expensify.model.IExpense;
import com.expensify.persistenceLayer.IExpenseDOAService;

public interface IExpenseFactory {

    IExpense createExpense();

    IExpenseDOAService createExpenseDAOService(IDatabase database);

    IExpense createExpense(int expenseID, int userID, String title, String description, Float amount, int expenseCategory, int walletID, String expenseDate, String expenseCategoryName);

    ExpenseValidator createExpenseValidator();
}
