package com.expensify.model;

import com.expensify.persistenceLayer.ExpenseDAOService;

import java.sql.SQLException;
import java.util.List;

public class Expense {
    private int expenseID;
    private int userID;
    private String expenseTitle;
    private String description;

    private Float amount;
    private int expenseCategory;
    private int walletId;
    private String expenseDate;

    private ExpenseDAOService expenseDAO;

    public Expense() {
        expenseDAO = new ExpenseDAOService();
    }

    public Expense(int expenseID, int userID, String title, String description, Float amount, int expenseCategory, int walletID, String expenseDate) {
        this.expenseID = expenseID;
        this.userID = userID;
        this.expenseTitle = title;
        this.description = description;
        this.amount = amount;
        this.expenseCategory = expenseCategory;
        this.walletId = walletID;
        this.expenseDate = expenseDate;
    }

    @Override
    public String toString() {
        return "Expense{" + "expenseID=" + expenseID + ", userID=" + userID + ", title='" + expenseTitle + '\'' + ", description='" + description + '\'' + ", amount=" + amount + ", expenseCategory=" + expenseCategory + ", walletID=" + walletId + ", expenseDate=" + expenseDate + '}';
    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getExpenseTitle() {
        return expenseTitle;
    }

    public void setExpenseTitle(String title) {
        this.expenseTitle = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public int getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(int expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletID) {
        this.walletId = walletID;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public List<Expense> getAllUserExpenses(int userID, String startDate, String endDate) throws SQLException {
        return expenseDAO.getAllUserExpenses(userID, startDate, endDate);
    }

    public Expense addUserExpense() {

        return expenseDAO.addUserExpenses(this);


    }

    public Expense deleteExpense(){
        return expenseDAO.deleteUserExpense(this);
    }
}
