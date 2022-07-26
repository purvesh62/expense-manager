package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.DateUtil;
import com.expensify.model.IExpense;
import com.expensify.factories.ExpenseFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ExpenseDAOService implements IExpenseDOAService {

    private final IDatabase database;

    public ExpenseDAOService(IDatabase database) {
        this.database = database;
    }

    @Override
    public List<IExpense> getAllUserExpenses(int userID, String startDate, String endDate) {
        List<IExpense> userExpenseList = new ArrayList<>();
        try {
            List<Object> parameterList = new ArrayList<>();

            java.sql.Date expenseStartDate = DateUtil.convertDate(startDate);
            java.sql.Date expenseEndDate = DateUtil.convertDate(endDate);

            parameterList.add(userID);
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
    public boolean addUserExpenses(int userId, String expenseTitle, String description, Float amount, int expenseCategory, int walletId, String expenseDate) {
        List<Object> parameterList = new ArrayList<>();
        try {
            parameterList.add(expenseTitle);
            parameterList.add(description);
            parameterList.add(userId);
            parameterList.add(amount);
            parameterList.add(expenseCategory);
            parameterList.add(walletId);

            java.sql.Date expenseStartDate = DateUtil.convertDate(expenseDate);
            parameterList.add(expenseStartDate);

            try (ResultSet resultSet = this.database.executeProcedure("CALL add_expense(?, ?, ?, ?, ?, ?, ?)", parameterList)) {
                return true;
            }
        } catch (SQLException exception) {
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

            java.sql.Date expenseStartDate = DateUtil.convertDate(startDate);
            parameterList.add(expenseStartDate);

            java.sql.Date expenseEndDate = DateUtil.convertDate(endDate);
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

            java.sql.Date expenseStartDate = DateUtil.convertDate(startDate);
            parameterList.add(expenseStartDate);

            java.sql.Date expenseEndDate = DateUtil.convertDate(endDate);
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
