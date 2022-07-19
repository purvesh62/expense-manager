package com.expensify.persistenceLayer;

import com.expensify.database.Database;
import com.expensify.database.IDatabase;
import com.expensify.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class BudgetDAOService implements IBudgetDAOService{
    private IDatabase database;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public BudgetDAOService(IDatabase database) {
        this.database = database;
    }

    @Override
    public List<IBudget> getAllBudgetDetails(int userId, String startDate, String endDate) throws SQLException {
        List<IBudget> budgetList = new ArrayList<>(10);
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
                    IBudgetFactory budgetFactory = new BudgetFactory();
                    IBudget budget = budgetFactory.createBudget();

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

    @Override
    public void addNewBudget(IBudget newBudget) throws SQLException {

        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(newBudget.getWalletId());
            parameterList.add(newBudget.getUserId());
            parameterList.add(newBudget.getBudgetLimit());

            LocalDate currentDate = LocalDate.now();
            LocalDate date = LocalDate.of(currentDate.getYear(), Integer.parseInt(newBudget.getMonth()),01);
            Date start = formatter.parse(String.valueOf(date));
            java.sql.Date startDate = new java.sql.Date(start.getTime());

            Date end = formatter.parse(date.getYear() + "-" + (date.getMonth().ordinal() + 1) + "-" + date.lengthOfMonth());
            java.sql.Date endDate = new java.sql.Date(end.getTime());

            parameterList.add(startDate);
            parameterList.add(endDate);

            database.executeProcedure("CALL add_budget(?,?,?,?,?)", parameterList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public void updateBudget(IBudget budget) throws SQLException {
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

    @Override
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

    @Override
    public IBudget getBudgetById(int budgetId) throws SQLException {
        IBudgetFactory budgetFactory = new BudgetFactory();
        IBudget budget = budgetFactory.createBudget();
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

    public int checkIfBudgetLimitExceeds(Expense expense) throws ParseException, SQLException {

        int userId = 0;

        try {
            List<Object> parameterList = new ArrayList<>();

            parameterList.add(expense.getUserID());
            parameterList.add(expense.getWalletId());

            Date date = formatter.parse(expense.getExpenseDate());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            LocalDate startDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 01);
            LocalDate endDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, startDate.lengthOfMonth());

            Date start = formatter.parse(String.valueOf(startDate));
            java.sql.Date budgetStartDate = new java.sql.Date(start.getTime());

            Date end = formatter.parse(String.valueOf(endDate));
            java.sql.Date budgetEndDate = new java.sql.Date(end.getTime());

            parameterList.add(budgetStartDate);
            parameterList.add(budgetEndDate);

            ResultSet resultSet = database.executeProcedure("CALL budget_limit_exceeds(?,?,?,?)", parameterList);

            if (resultSet != null) {
                while (resultSet.next()) {
                    userId = resultSet.getInt("user_id");
                }
            }
            return userId;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return userId;
    }
}
