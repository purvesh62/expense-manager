package com.expensify.factories;

import com.expensify.database.IDatabase;
import com.expensify.Validators.BudgetValidator;
import com.expensify.model.IAnalytics;
import com.expensify.model.IBudget;
import com.expensify.persistenceLayer.IBudgetDAOService;

public interface IBudgetFactory {
    IBudget createBudget();

    IBudgetDAOService createBudgetDAOService(IDatabase database);

    IBudget createBudget(int budgetId, int walletId, String walletName, int userId, float budgetLimit, float totalExpenses, String month);
    BudgetValidator createBudgetValidator();
}
