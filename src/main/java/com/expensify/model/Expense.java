package com.expensify.model;

import com.expensify.persistenceLayer.IExpenseDOAService;

import java.util.List;

public class Expense implements IExpense {
    private int expenseID;
    private int userID;
    private String expenseTitle;
    private String description;
    private Float amount;
    private int expenseCategory;
    private String expenseCategoryName;
    private int walletId;
    private String expenseDate;
    private IExpenseDOAService expenseDOAService;

    public Expense() {

    }

    public Expense(IExpenseDOAService expenseDOAService) {
        this.expenseDOAService = expenseDOAService;
    }

    public Expense(int expenseID, int userID, String title, String description, Float amount, int expenseCategory, int walletID, String expenseDate, String expenseCategoryName) {
        this.expenseID = expenseID;
        this.userID = userID;
        this.expenseTitle = title;
        this.description = description;
        this.amount = amount;
        this.expenseCategory = expenseCategory;
        this.walletId = walletID;
        this.expenseDate = expenseDate;
        this.expenseCategoryName = expenseCategoryName;
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

    public String getExpenseCategoryName() {
        return expenseCategoryName;
    }

    public void setExpenseCategoryName(String expenseCategoryName) {
        this.expenseCategoryName = expenseCategoryName;
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

    @Override
    public IExpenseDOAService getExpenseDOAService() {
        return this.expenseDOAService;
    }

    public void setExpenseDOAService(IExpense expense) {
        this.expenseDOAService = expense.getExpenseDOAService();
    }

    @Override
    public List<IExpense> getAllUserExpenses(int userId, String startDate, String endDate) {
        return expenseDOAService.getAllUserExpenses(userId, startDate, endDate);
    }

    @Override
    public boolean addUserExpense() {
        return expenseDOAService.addUserExpenses(userID, expenseTitle, description, amount, expenseCategory, walletId, expenseDate);
    }

    @Override
    public boolean deleteUserExpense(int expenseId) {
        return expenseDOAService.deleteUserExpense(expenseId);
    }
}
