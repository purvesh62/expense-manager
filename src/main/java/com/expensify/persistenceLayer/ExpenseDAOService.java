package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.IExpense;
import com.expensify.model.factories.ExpenseFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class ExpenseDAOService implements IExpenseDOAService {
    private final IDatabase database;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public ExpenseDAOService(IDatabase database) {
        this.database = database;
    }

    @Override
    public List<IExpense> getAllUserExpenses(int userID, String startDate, String endDate) {
        List<IExpense> userExpenseList = new ArrayList<>();
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userID);

            Date start = formatter.parse(startDate);
            java.sql.Date expenseStartDate = new java.sql.Date(start.getTime());

            Date end = formatter.parse(endDate);
            java.sql.Date expenseEndDate = new java.sql.Date(end.getTime());


            parameterList.add(expenseStartDate);
            parameterList.add(expenseEndDate);

            try (ResultSet resultSet = this.database.executeProcedure("CALL get_all_user_expense(?, ?, ?)", parameterList)) {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        IExpense expense = ExpenseFactory.instance().createExpense(
                                resultSet.getInt("expense_id"),
                                resultSet.getInt("user_id"),
                                resultSet.getString("title"),
                                resultSet.getString("description"),
                                resultSet.getFloat("amount"),
                                resultSet.getInt("c_id"),
                                resultSet.getInt("w_id"),
                                String.valueOf(resultSet.getDate("expense_date")),
                                resultSet.getString("expense_category"));
                        userExpenseList.add(expense);
                    }
                }
            }
            return userExpenseList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.database.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userExpenseList;
    }

    @Override
    public boolean addUserExpenses(int expenseId, int userId, String expenseTitle, String description, Float amount, int expenseCategory, int walletId, String expenseDate) {
        List<Object> parameterList = new ArrayList<>();
        try {
            parameterList.add(expenseTitle);
            parameterList.add(description);
            parameterList.add(userId);
            parameterList.add(amount);
            parameterList.add(expenseCategory);
            parameterList.add(walletId);
            Date date = formatter.parse(expenseDate);
            parameterList.add(date);
            try (ResultSet resultSet = this.database.executeProcedure("CALL add_expense(?, ?, ?, ?, ?, ?, ?)", parameterList)) {
                return true;
            }
        } catch (SQLException | ParseException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUserExpense(int expenseId) {
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(expenseId);
        try (ResultSet resultSet = this.database.executeProcedure("CALL delete_expense(?)", parameterList)) {
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public HashMap<Integer, Float> getMonthlyExpense(int userId, String startDate, String endDate) {
        HashMap<Integer, Float> userMonthlyExpense = new HashMap<>();
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userId);

            Date start = formatter.parse(startDate);
            java.sql.Date expenseStartDate = new java.sql.Date(start.getTime());
            parameterList.add(expenseStartDate);

            Date end = formatter.parse(endDate);
            java.sql.Date expenseEndDate = new java.sql.Date(end.getTime());
            parameterList.add(expenseEndDate);

            try (ResultSet resultSet = this.database.executeProcedure("CALL get_user_monthly_expense(?, ?, ?)", parameterList)) {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        userMonthlyExpense.put(resultSet.getInt("expense_month"), resultSet.getFloat("total"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.database.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userMonthlyExpense;
    }

    @Override
    public HashMap<String, Float> getMonthlyAnalyticsByCategories(int userId, String startDate, String endDate) {
        HashMap<String, Float> userMonthlyExpense = new HashMap<>();
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userId);

            Date start = formatter.parse(startDate);
            java.sql.Date expenseStartDate = new java.sql.Date(start.getTime());
            parameterList.add(expenseStartDate);

            Date end = formatter.parse(endDate);
            java.sql.Date expenseEndDate = new java.sql.Date(end.getTime());
            parameterList.add(expenseEndDate);

            try (ResultSet resultSet = this.database.executeProcedure("CALL get_user_expense_with_categories(?, ?, ?)", parameterList)) {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        userMonthlyExpense.put(resultSet.getString("category"), resultSet.getFloat("total"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.database.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userMonthlyExpense;
    }

}
