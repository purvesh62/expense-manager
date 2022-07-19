package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.Budget;

import java.util.List;

public class BudgetDAOServiceFactory implements IBudgetDAOServiceFactory {
    public BudgetDAOServiceFactory(){

    }

    @Override
    public IBudgetDAOService createBudgetDAOService(IDatabase database) {
        return new BudgetDAOService(database);
    }
}
