package com.expensify.model;

import com.expensify.database.IDatabase;
import com.expensify.persistenceLayer.BudgetDAOService;
import com.expensify.persistenceLayer.IBudgetDAOService;

public class BudgetFactory implements IBudgetFactory {

    public BudgetFactory() {

    }

    @Override
    public IBudget createBudget(IDatabase database) {

        return new Budget(createBudgetDAOService(database));
    }

    @Override
    public IBudget createBudget() {
        return new Budget();
    }

    @Override
    public IBudgetDAOService createBudgetDAOService(IDatabase database) {
        return new BudgetDAOService(database);
    }

    @Override
    public IBudget createBudget(int budgetId, int walletId, String walletName, int userId, float budgetLimit, float totalExpenses) {
        return new Budget(budgetId, walletId, walletName, userId, budgetLimit, totalExpenses);
    }
}
