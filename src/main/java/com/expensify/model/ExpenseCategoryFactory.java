package com.expensify.model;

import com.expensify.persistenceLayer.ExpenseCategoriesDAOService;
import com.expensify.persistenceLayer.PaymentCategoriesDAOService;
import org.springframework.stereotype.Service;

@Service
public class ExpenseCategoryFactory implements IExpenseCategoryFactory{
    @Override
    public ExpenseCategory createExpenseCategory() {
        return new ExpenseCategory(this);
    }

    @Override
    public ExpenseCategoriesDAOService createExpenseCategoryDAOService() {
        return new ExpenseCategoriesDAOService(this);
    }
}
