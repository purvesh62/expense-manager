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

@Component
public class BudgetDAO {
    private final IDatabase mySqlDatabaseManager;

    @Autowired
    public BudgetDAO(MySqlDatabaseManager mySqlDatabaseManager) {
        this.mySqlDatabaseManager = mySqlDatabaseManager;
    }

    public List<Budget> getAllBudgetDetails(int user_id) throws SQLException {
        List<Budget> budgetList = new ArrayList<>(10);
        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(user_id);
            ResultSet resultSet = mySqlDatabaseManager.executeProcedure("CALL get_all_budget(?)", parameterList);
            if(resultSet!=null){
                while (resultSet.next()) {
                    Budget budget = new Budget();

                    budget.setBudgetId(resultSet.getInt("budget_id"));
                    budget.setUserId(resultSet.getInt("user_id"));
                    budget.setWalletId(resultSet.getInt("wallet_id"));
                    budget.setBudgetLimit(resultSet.getFloat("budget_limit"));

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
}
