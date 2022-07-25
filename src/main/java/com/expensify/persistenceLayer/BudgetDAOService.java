package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.*;
import com.expensify.factories.BudgetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


public class BudgetDAOService implements IBudgetDAOService {
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
            while (resultSet.next()) {
                IBudget budget = BudgetFactory.instance().createBudget(
                        resultSet.getInt("budget_id"),
                        resultSet.getInt("wallet_id"),
                        resultSet.getString("wallet_label"),
                        resultSet.getInt("user_id"),
                        resultSet.getFloat("budget_limit"),
                        resultSet.getFloat("total_expenses"),
                        String.valueOf(LocalDate.parse(resultSet.getDate("start_date").toString()).getMonthValue())
                );
                budgetList.add(budget);
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
    public boolean addNewBudget(int walletId, int userId, float budgetLimit, String month) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(walletId);
            parameterList.add(userId);
            parameterList.add(budgetLimit);

            LocalDate currentDate = LocalDate.now();
            LocalDate date = LocalDate.of(currentDate.getYear(), Integer.parseInt(month), 01);
            Date start = formatter.parse(String.valueOf(date));
            java.sql.Date startDate = new java.sql.Date(start.getTime());

            Date end = formatter.parse(date.getYear() + "-" + (date.getMonth().ordinal() + 1) + "-" + date.lengthOfMonth());
            java.sql.Date endDate = new java.sql.Date(end.getTime());

            parameterList.add(startDate);
            parameterList.add(endDate);

            try (ResultSet resultSet = database.executeProcedure("CALL add_budget(?,?,?,?,?)", parameterList)) {
                return true;
            }
        } catch (SQLException | ParseException exception) {
            exception.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return false;
    }

    @Override
    public boolean updateBudget(int budgetId, int walletId, float budgetLimit) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(budgetId);
            parameterList.add(walletId);
            parameterList.add(budgetLimit);

            try (ResultSet resultSet = database.executeProcedure("CALL update_budget(?,?,?)", parameterList)) {
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return false;
    }

    @Override
    public boolean deleteBudget(int budgetId) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(budgetId);

            try (ResultSet resultSet = database.executeProcedure("CALL delete_budget(?)", parameterList)) {
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return false;
    }

    @Override
    public IBudget getBudgetById(int budgetId) throws SQLException {
        IBudget budget = BudgetFactory.instance().createBudget();
        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(budgetId);

            ResultSet resultSet = database.executeProcedure("CALL get_budget_by_id(?)", parameterList);
            while (resultSet.next()) {
                budget = BudgetFactory.instance().createBudget(
                        resultSet.getInt("budget_id"),
                        resultSet.getInt("wallet_id"),
                        resultSet.getString("wallet_label"),
                        resultSet.getInt("user_id"),
                        resultSet.getFloat("budget_limit"),
                        resultSet.getFloat("total_expenses"),
                        String.valueOf(LocalDate.parse(resultSet.getDate("start_date").toString()).getMonthValue())
                );
            }
            return budget;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return budget;
    }

    public int checkIfBudgetLimitExceeds(int userId, int walletId, String expenseDate) throws SQLException {

        int userIdExists = 0;

        try {
            List<Object> parameterList = new ArrayList<>();

            parameterList.add(userId);
            parameterList.add(walletId);

            Date date = formatter.parse(expenseDate);
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

            while (resultSet.next()) {
                userIdExists = resultSet.getInt("user_id");
            }
            return userIdExists;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return userIdExists;
    }

    @Override
    public boolean checkIfBudgetExists(int budgetId, int userId, int walletId, String month) {
        try {
            List<Object> parameterList = new ArrayList<>(10);
            parameterList.add(userId);
            parameterList.add(walletId);

            LocalDate currentDate = LocalDate.now();
            LocalDate date = LocalDate.of(currentDate.getYear(), Integer.parseInt(month), 01);
            Date start = formatter.parse(String.valueOf(date));
            java.sql.Date startDate = new java.sql.Date(start.getTime());
            parameterList.add(startDate);

            try (ResultSet resultSet = database.executeProcedure("CALL check_if_budget_exists(?,?,?)", parameterList)) {
                while (resultSet.next()) {
                    if (resultSet.getInt("budget_id") == budgetId) {
                        return false;
                    }
                    return true;
                }
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                database.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
