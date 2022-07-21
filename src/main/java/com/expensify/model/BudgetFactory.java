//package com.expensify.model;
//
//import com.expensify.database.IDatabase;
//import com.expensify.persistenceLayer.IBudgetDAOServiceFactory;
//
//public class BudgetFactory implements IBudgetFactory {
//
//    public BudgetFactory() {
//
//    }
//
//    @Override
//    public IBudget createBudget(IBudgetDAOServiceFactory budgetDAOServiceFactory, IDatabase database) {
//        return new Budget(budgetDAOServiceFactory, database);
//    }
//
//    @Override
//    public IBudget createBudget() {
//        return new Budget();
//    }
//}
