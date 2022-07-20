package com.expensify.model;

import com.expensify.persistenceLayer.IBudgetDAOService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class Budget implements IBudget {

    private IBudgetDAOService budgetDAOService;
    private int budgetId;
    private int walletId;
    private String walletName;
    private int userId;
    private float budgetLimit;
    private float totalExpenses;
    private String month;

    public Budget(int budgetId, int walletId, String walletName, int userId, float budgetLimit, float totalExpenses) {
        this.budgetId = budgetId;
        this.walletId = walletId;
        this.walletName = walletName;
        this.userId = userId;
        this.budgetLimit = budgetLimit;
        this.totalExpenses = totalExpenses;

    }
    public Budget(){

    }
    public Budget(IBudgetDAOService database) {
    budgetDAOService = database;
    }
    public IBudgetDAOService getBudgetDAOService() {
        return budgetDAOService;
    }

    public void setBudgetDAOService(IBudgetDAOService budgetDAOService) {
        this.budgetDAOService = budgetDAOService;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getBudgetLimit() {
        return budgetLimit;
    }

    public void setBudgetLimit(float budgetLimit) {
        this.budgetLimit = budgetLimit;
    }

    public float getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(float totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public List<IBudget> getAllBudgetDetailsService(int user_id, String startDate, String endDate) throws SQLException {
        return budgetDAOService.getAllBudgetDetails(user_id, startDate, endDate);
    }

    @Override
    public IBudget saveBudget() throws SQLException {
        budgetDAOService.addNewBudget(walletId,userId,budgetLimit,month);
        return this;
    }

    @Override
    public IBudget updateBudget() throws SQLException {
        budgetDAOService.updateBudget(budgetId,walletId,budgetLimit);
        return this;
    }

    @Override
    public void deleteBudget(int budgetId) throws SQLException {
        budgetDAOService.deleteBudget(budgetId);
    }

    @Override
    public IBudget getBudgetById(int budgetId) throws SQLException {
        return budgetDAOService.getBudgetById(budgetId);
    }

    @Override
    public void checkIfBudgetLimitExceeds(Expense expense) throws SQLException, ParseException {

        int userId = budgetDAOService.checkIfBudgetLimitExceeds(expense);
        if (userId > 0) {
            Subscription subscription = new Subscription();
            subscription.notifyBudgetLimitExceeds(userId);
        }
    }
}
