package com.expensify.model;

import com.expensify.database.IDatabase;
import com.expensify.persistenceLayer.BudgetDAOService;
import com.expensify.persistenceLayer.IBudgetDAOService;
import com.expensify.persistenceLayer.IBudgetDAOServiceFactory;

import java.sql.SQLException;
import java.time.Month;
import java.util.List;


public class Budget extends IBudget {

    public Budget(){
        super();
    }
    public Budget(IBudgetDAOServiceFactory budgetDAOServiceFactory, IDatabase database){
        super(budgetDAOServiceFactory,database);
    }



    @Override
    public List<IBudget> getAllBudgetDetailsService(int user_id, String startDate, String endDate) throws SQLException {
        return budgetDAOService.getAllBudgetDetails(user_id, startDate, endDate);
    }
    @Override
    public IBudget saveBudget() throws SQLException {
        budgetDAOService.addNewBudget(this);
        return this;
    }
    @Override
    public IBudget updateBudget() throws SQLException {
        budgetDAOService.updateBudget(this);
        return this;
    }
    @Override
    public void deleteBudget(int budgetId) throws SQLException {
        budgetDAOService.deleteBudget(budgetId);
    }
    @Override
    public IBudget getBudgetById(int budgetId) throws SQLException {
        return budgetDAOService.getBudgetById(budgetId);
    }
}
