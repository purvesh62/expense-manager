package com.expensify.model.factories;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.Budget;
import com.expensify.model.IBudget;
import com.expensify.persistenceLayer.BudgetDAOService;
import com.expensify.persistenceLayer.IBudgetDAOService;

public class BudgetFactory implements IBudgetFactory {

    private static IBudgetFactory budgetFactory = null;
    private BudgetFactory() {

    }
    public static IBudgetFactory instance(){
        if(budgetFactory == null){
            budgetFactory = new BudgetFactory();
        }
        return budgetFactory;
    }

    @Override
    public IBudget createBudget() {
        IDatabase database = MySqlDatabase.instance();
        return new Budget(createBudgetDAOService(database));
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
