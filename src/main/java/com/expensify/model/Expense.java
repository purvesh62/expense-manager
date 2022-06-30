package com.expensify.model;

import com.expensify.persistenceLayer.ExpenseDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Expense {
    private int expenseID;
    private int userID;
    private String title;
    private String description;

    private Float amount;
    private int expenseCategory;
    private int walletID;
    private String expenseDate;

    private ExpenseDAO expenseDAO;

    public Expense() {
        expenseDAO = new ExpenseDAO();
    }

    public Expense(int expenseID, int userID, String title, String description, Float amount, int expenseCategory, int walletID, String expenseDate) {
        this.expenseID = expenseID;
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.expenseCategory = expenseCategory;
        this.walletID = walletID;
        this.expenseDate = expenseDate;
    }

    @Override
    public String toString() {
        return "Expense{" + "expenseID=" + expenseID + ", userID=" + userID + ", title='" + title + '\'' + ", description='" + description + '\'' + ", amount=" + amount + ", expenseCategory=" + expenseCategory + ", walletID=" + walletID + ", expenseDate=" + expenseDate + '}';
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getWallet() {
        return walletID;
    }

    public void setWallet(int walletID) {
        this.walletID = walletID;
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
        expenseDAO.addUserExpenses(this);
        return this;
    }
}
