package com.expensify.model;

import com.expensify.factories.NotificationFactory;
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

    public Budget(int budgetId, int walletId, String walletName, int userId, float budgetLimit, float totalExpenses, String month) {
        this.budgetId = budgetId;
        this.walletId = walletId;
        this.walletName = walletName;
        this.userId = userId;
        this.budgetLimit = budgetLimit;
        this.totalExpenses = totalExpenses;
        this.month = month;
    }

    public Budget() {

    }

    public Budget(IBudgetDAOService database) {
        budgetDAOService = database;
    }

    @Override
    public IBudgetDAOService getBudgetDAOService() {
        return budgetDAOService;
    }

    public void setBudgetDAOService(IBudget budget) {
        this.budgetDAOService = budget.getBudgetDAOService();
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
    public List<IBudget> getAllBudgetDetailsService(int user_id, String startDate, String endDate) {
        try {
            return budgetDAOService.getAllBudgetDetails(user_id, startDate, endDate);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean saveBudget() {
        try {
            return budgetDAOService.addNewBudget(walletId, userId, budgetLimit, month);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean updateBudget() {
        try {
            return budgetDAOService.updateBudget(budgetId, walletId, budgetLimit);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteBudget(int budgetId) {
        try {
            return budgetDAOService.deleteBudget(budgetId);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public IBudget getBudgetById(int budgetId) {
        try {
            return budgetDAOService.getBudgetById(budgetId);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void checkIfBudgetLimitExceeds(int user, int walletId, String expenseDate) throws SQLException, ParseException {

        int userId = budgetDAOService.checkIfBudgetLimitExceeds(user, walletId, expenseDate);
        if (userId > 0) {
            INotification notification = NotificationFactory.instance().createNotification();
            notification.notifyBudgetLimitExceeds(userId);
        }
    }

    @Override
    public boolean checkIfBudgetExists(int budgetId, int userId, int walletId, String month) {
        return budgetDAOService.checkIfBudgetExists(budgetId, userId, walletId, month);
    }
}
