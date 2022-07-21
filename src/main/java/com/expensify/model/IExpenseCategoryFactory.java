package com.expensify.model;

import com.expensify.persistenceLayer.ExpenseCategoriesDAOService;

public interface IExpenseCategoryFactory {
    ExpenseCategory createExpenseCategory();

    ExpenseCategoriesDAOService createExpenseCategoryDAOService();

}
