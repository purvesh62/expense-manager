package com.expensify.model.factories;

import com.expensify.database.IDatabase;
import com.expensify.model.BudgetValidator;
import com.expensify.model.IBudget;
import com.expensify.persistenceLayer.IBudgetDAOService;

public interface IBudgetFactory {
    IBudget createBudget();

    IBudgetDAOService createBudgetDAOService(IDatabase database);

    IBudget createBudget(int budgetId, int walletId, String walletName, int userId, float budgetLimit, float totalExpenses, String month);

    BudgetValidator createBudgetValidator();
}
