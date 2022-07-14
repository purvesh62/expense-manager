package com.expensify.persistenceLayer;

import com.expensify.database.Database;
import com.expensify.database.IDatabase;
import com.expensify.model.Budget;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BudgetDAOService {
    private final IDatabase database;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public BudgetDAOService() {
        this.database = Database.getInstance();
    }

    public List<Budget> getAllBudgetDetails(int userId, String startDate, String endDate) throws SQLException {
        List<Budget> budgetList = new ArrayList<>(10);
        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(userId);
            Date start = formatter.parse(startDate);
            java.sql.Date budgetStartDate = new java.sql.Date(start.getTime());

            Date end = formatter.parse(endDate);
            java.sql.Date budgetEndDate = new java.sql.Date(end.getTime());


            parameterList.add(budgetStartDate);
            parameterList.add(budgetEndDate);

            ResultSet resultSet = database.executeProcedure("CALL get_all_budget(?,?,?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Budget budget = new Budget();

                    budget.setBudgetId(resultSet.getInt("budget_id"));
                    budget.setUserId(resultSet.getInt("user_id"));
                    budget.setWalletId(resultSet.getInt("wallet_id"));
                    budget.setBudgetLimit(resultSet.getFloat("budget_limit"));
                    budget.setWalletName(resultSet.getString("wallet_label"));
                    budget.setTotalExpenses(resultSet.getFloat("total_expenses"));

                    budgetList.add(budget);
                }
            }
            return budgetList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return budgetList;
    }

    public void addNewBudget(Budget newBudget) throws SQLException {

        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(newBudget.getWalletId());
            parameterList.add(newBudget.getUserId());
            parameterList.add(newBudget.getBudgetLimit());

            database.executeProcedure("CALL add_budget(?,?,?)", parameterList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
    }

    public void updateBudget(Budget budget) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>(10);
            System.out.println(budget.getBudgetId() + budget.getWalletId() + budget.getBudgetLimit());
            parameterList.add(budget.getBudgetId());
            parameterList.add(budget.getWalletId());
            parameterList.add(budget.getBudgetLimit());

            database.executeProcedure("CALL update_budget(?,?,?)", parameterList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
    }

    public void deleteBudget(int budgetId) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(budgetId);

            database.executeProcedure("CALL delete_budget(?)", parameterList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
    }

    public Budget getBudgetById(int budgetId) throws SQLException {
        Budget budget = new Budget();
        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(budgetId);


             ResultSet resultSet =  database.executeProcedure("CALL get_budget_by_id(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {

                    budget.setBudgetId(resultSet.getInt("budget_id"));
                    budget.setUserId(resultSet.getInt("user_id"));
                    budget.setWalletId(resultSet.getInt("wallet_id"));
                    budget.setBudgetLimit(resultSet.getFloat("budget_limit"));
                    budget.setWalletName(resultSet.getString("wallet_label"));
                }
            }
            return budget;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return budget;
    }
}
