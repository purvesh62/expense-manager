package com.expensify.model;

import com.expensify.persistenceLayer.ExpenseCategoriesDAOService;
import com.expensify.persistenceLayer.PaymentCategoriesDAOService;

public interface IExpenseCategoryFactory {
    ExpenseCategory makeExpenseCategory();

    ExpenseCategoriesDAOService makeExpenseCategoryDAOService();

}
