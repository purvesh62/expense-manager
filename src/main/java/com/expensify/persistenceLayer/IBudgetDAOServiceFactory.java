package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.Budget;

import java.util.List;

public interface IBudgetDAOServiceFactory {
    IBudgetDAOService createBudgetDAOService(IDatabase database);
}
