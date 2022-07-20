package com.expensify.persistenceLayer;

import com.expensify.database.Database;
import com.expensify.database.IDatabase;
import com.expensify.model.Expense;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExpenseDAOService {
    private final IDatabase mySqlDatabaseManager;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public ExpenseDAOService() {
        this.mySqlDatabaseManager = Database.instance();
    }

    public List<Expense> getAllUserExpenses(int userID, String startDate, String endDate) throws SQLException {
        List<Expense> userExpenseList = new ArrayList<>();
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userID);

            Date start = formatter.parse(startDate);
            java.sql.Date expenseStartDate = new java.sql.Date(start.getTime());

            Date end = formatter.parse(endDate);
            java.sql.Date expenseEndDate = new java.sql.Date(end.getTime());


            parameterList.add(expenseStartDate);
            parameterList.add(expenseEndDate);
            // TODO: Convert to iterator
            try (ResultSet resultSet = mySqlDatabaseManager.executeProcedure("CALL get_all_user_expense(?, ?, ?)", parameterList)) {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        Expense expense = new Expense();
                        expense.setExpenseID(resultSet.getInt("expense_id"));
                        expense.setExpenseTitle(resultSet.getString("title"));
                        expense.setDescription(resultSet.getString("description"));
                        expense.setUserID(resultSet.getInt("user_id"));
                        expense.setAmount(resultSet.getFloat("amount"));
                        expense.setExpenseCategory(resultSet.getInt("c_id"));
                        expense.setWalletId(resultSet.getInt("w_id"));
                        expense.setExpenseDate(String.valueOf(resultSet.getDate("expense_date")));
                        expense.setExpenseTitle(resultSet.getString("expense_category"));
                        userExpenseList.add(expense);
                    }
                }
            }
            return userExpenseList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mySqlDatabaseManager.closeConnection();
        }
        return userExpenseList;
    }

    public Expense addUserExpenses(Expense expense) {
        List<Object> parameterList = new ArrayList<>();
        try {
            parameterList.add(expense.getExpenseTitle());
            parameterList.add(expense.getDescription());
            parameterList.add(expense.getUserID());
            parameterList.add(expense.getAmount());
            parameterList.add(expense.getExpenseCategory());
            parameterList.add(expense.getWalletId());

            Date expenseDate = formatter.parse(expense.getExpenseDate());
            parameterList.add(expenseDate);

            try (ResultSet resultSet = mySqlDatabaseManager.executeProcedure("CALL add_expense(?, ?, ?, ?, ?, ?, ?)", parameterList)) {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        expense.setExpenseID(resultSet.getInt("expense_id"));
                        expense.setExpenseTitle(resultSet.getString("title"));
                        expense.setDescription(resultSet.getString("description"));
                        expense.setUserID(resultSet.getInt("user_id"));
                        expense.setAmount(resultSet.getFloat("amount"));
                        expense.setExpenseCategory(resultSet.getInt("c_id"));
                        expense.setWalletId(resultSet.getInt("w_id"));
                        expense.setExpenseDate(String.valueOf(resultSet.getDate("expense_date")));
                        expense.setExpenseCategoryName(String.valueOf(resultSet.getDate("expense_category")));
                    }
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return expense;
    }

    public Expense deleteUserExpense(Expense expense) {
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(expense.getExpenseID());
        try (ResultSet resultSet = mySqlDatabaseManager.executeProcedure("CALL delete_expense(?)", parameterList)) {
            if (resultSet != null) {
                while (resultSet.next()) {
                    expense.setExpenseID(resultSet.getInt("expense_id"));
                    expense.setExpenseTitle(resultSet.getString("title"));
                    expense.setDescription(resultSet.getString("description"));
                    expense.setUserID(resultSet.getInt("user_id"));
                    expense.setAmount(resultSet.getFloat("amount"));
                    expense.setExpenseCategory(resultSet.getInt("c_id"));
                    expense.setWalletId(resultSet.getInt("w_id"));
                    expense.setExpenseDate(String.valueOf(resultSet.getDate("expense_date")));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return expense;
    }
}
