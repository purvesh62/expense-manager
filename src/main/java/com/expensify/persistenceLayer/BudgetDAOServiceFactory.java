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

//    @Override
//    public List<Budget> getAllBudgetDetails(int user_id, String startDate, String endDate) {
//        return null;
//    }
//
//    @Override
//    public void addNewBudget(Budget budget) {
//
//    }
//
//    @Override
//    public void updateBudget(Budget budget) {
//
//    }
//
//    @Override
//    public void deleteBudget(int budgetId) {
//
//    }
//
//    @Override
//    public Budget getBudgetById(int budgetId) {
//        return null;
//    }
}
