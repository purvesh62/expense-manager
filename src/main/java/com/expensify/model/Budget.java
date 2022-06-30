package com.expensify.model;
import com.expensify.persistenceLayer.BudgetDAOService;
import java.sql.SQLException;
import java.util.List;


public class Budget {
    private final BudgetDAOService budgetDAOService;

    private int budgetId;
    private int walletId;

    private String walletName;
    private int userId;
    private float budgetLimit;

    public Budget() {

        budgetDAOService = new BudgetDAOService();
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

    public  List<Budget> getAllBudgetDetailsService(int user_id) throws SQLException {
        return budgetDAOService.getAllBudgetDetails(user_id);
    }

    public void saveBudget(Budget newBudget) throws SQLException {
        budgetDAOService.addNewBudget(newBudget);
    }

    public void updateBudget(Budget budget) throws SQLException {
        budgetDAOService.updateBudget(budget);
    }

    public void deleteBudget(int budgetId) throws SQLException {
        budgetDAOService.deleteBudget(budgetId);
    }
}
