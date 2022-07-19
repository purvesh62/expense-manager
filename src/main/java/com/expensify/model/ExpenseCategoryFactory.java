package com.expensify.model;

import com.expensify.persistenceLayer.ExpenseCategoriesDAOService;
import com.expensify.persistenceLayer.PaymentCategoriesDAOService;
import org.springframework.stereotype.Service;

@Service
public class ExpenseCategoryFactory implements IExpenseCategoryFactory{
    @Override
    public ExpenseCategory makeExpenseCategory() {
        return new ExpenseCategory(this);
    }

    @Override
    public ExpenseCategoriesDAOService makeExpenseCategoryDAOService() {
        return new ExpenseCategoriesDAOService(this);
    }
}
