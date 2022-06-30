package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabaseManager;
import com.expensify.model.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BudgetDAOService {
    private final IDatabase mySqlDatabaseManager;

    public BudgetDAOService(){
        this.mySqlDatabaseManager = new MySqlDatabaseManager();
    }
//    @Autowired
//    public BudgetDAOService(MySqlDatabaseManager mySqlDatabaseManager) {
//        this.mySqlDatabaseManager = mySqlDatabaseManager;
//    }

    public List<Budget> getAllBudgetDetails(int userId) throws SQLException {
        List<Budget> budgetList = new ArrayList<>(10);
        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(userId);
            ResultSet resultSet = mySqlDatabaseManager.executeProcedure("CALL get_all_budget(?)", parameterList);
            if(resultSet!=null){
                while (resultSet.next()) {
                    Budget budget = new Budget();

                    budget.setBudgetId(resultSet.getInt("budget_id"));
                    budget.setUserId(resultSet.getInt("user_id"));
                    budget.setWalletId(resultSet.getInt("wallet_id"));
                    budget.setBudgetLimit(resultSet.getFloat("budget_limit"));
                    budget.setWalletName(resultSet.getString("wallet_label"));

                    budgetList.add(budget);
                }
            }
            return budgetList;
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            mySqlDatabaseManager.closeConnection();
        }
        return budgetList;
    }

    public void addNewBudget(Budget newBudget) throws SQLException {

        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(newBudget.getWalletId());
            parameterList.add(newBudget.getUserId());
            parameterList.add(newBudget.getBudgetLimit());

           mySqlDatabaseManager.executeProcedure("CALL add_budget(?,?,?)", parameterList);

        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            mySqlDatabaseManager.closeConnection();
        }
    }

    public void updateBudget(Budget budget) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(budget.getBudgetId());
            parameterList.add(budget.getWalletId());
            parameterList.add(budget.getBudgetLimit());

            mySqlDatabaseManager.executeProcedure("CALL update_budget(?,?,?)", parameterList);

        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            mySqlDatabaseManager.closeConnection();
        }
    }

    public void deleteBudget(int budgetId) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(budgetId);

            mySqlDatabaseManager.executeProcedure("CALL delete_budget(?)", parameterList);

        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            mySqlDatabaseManager.closeConnection();
        }
    }
}
