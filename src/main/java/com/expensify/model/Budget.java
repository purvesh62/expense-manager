package com.expensify.model;

public class Budget {
    private int budgetId;
    private int walletId;

    private String walletName;
    private int userId;
    private float budgetLimit;

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
}
