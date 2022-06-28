package com.expensify.model;

import java.util.Date;

public class Expense {
    private int expenseID;
    private int userID;
    private String title;
    private String description;
    private Float amount;
    private int expenseCategory;
    private int walletID;
    private Date expenseDate;

    public Expense() {

    }

    public Expense(int expenseID, int userID, String title, String description, Float amount, int expenseCategory, int walletID, Date expenseDate) {
        this.expenseID = expenseID;
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.expenseCategory = expenseCategory;
        this.walletID = walletID;
        this.expenseDate = expenseDate;
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

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }
}
