package com.expensify.model;

import com.expensify.database.IDatabase;
import com.expensify.persistenceLayer.IBudgetDAOService;

public interface IBudgetFactory {
     IBudget createBudget(IDatabase database);
     IBudget createBudget();
     IBudgetDAOService createBudgetDAOService(IDatabase database);
     IBudget createBudget(int budgetId, int walletId, String walletName, int userId, float budgetLimit, float totalExpenses);
}
