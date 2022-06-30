package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabaseManager;
import com.expensify.model.Expense;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExpenseDAO {
    private final IDatabase mySqlDatabaseManager;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public ExpenseDAO() {
        this.mySqlDatabaseManager = new MySqlDatabaseManager();
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

            ResultSet resultSet = mySqlDatabaseManager.executeProcedure("CALL get_all_user_expense(?, ?, ?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Expense expense = new Expense();
                    expense.setExpenseID(resultSet.getInt("expense_id"));
                    expense.setTitle(resultSet.getString("title"));
                    expense.setDescription(resultSet.getString("description"));
                    expense.setUserID(resultSet.getInt("user_id"));
                    expense.setAmount(resultSet.getFloat("amount"));
                    expense.setExpenseCategory(resultSet.getInt("c_id"));
                    expense.setWallet(resultSet.getInt("w_id"));
                    expense.setExpenseDate(String.valueOf(resultSet.getDate("expense_date")));

                    userExpenseList.add(expense);
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

    public void addUserExpenses(Expense expense) {
        List<Object> parameterList = new ArrayList<>();
        try {
            parameterList.add(expense.getTitle());
            parameterList.add(expense.getDescription());
            parameterList.add(expense.getUserID());
            parameterList.add(expense.getAmount());
            parameterList.add(expense.getExpenseCategory());
            parameterList.add(expense.getWallet());

            Date expenseDate = formatter.parse(expense.getExpenseDate());
            parameterList.add(expenseDate);

            ResultSet resultSet = mySqlDatabaseManager.executeProcedure("CALL add_expense(?, ?, ?, ?, ?, ?, ?)", parameterList);

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
