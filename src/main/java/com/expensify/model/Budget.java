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

    private float totalExpenses;

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

    public float getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(float totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public  List<Budget> getAllBudgetDetailsService(int user_id, String startDate, String endDate) throws SQLException {
        return budgetDAOService.getAllBudgetDetails(user_id,startDate,endDate);
    }

    public Budget saveBudget(Budget newBudget) throws SQLException {
        budgetDAOService.addNewBudget(newBudget);
        return this;
    }

    public Budget updateBudget() throws SQLException {
        budgetDAOService.updateBudget(this);
        return this;
    }

    public void deleteBudget(int budgetId) throws SQLException {
        budgetDAOService.deleteBudget(budgetId);
    }

    public Budget getBudgetById(int budgetId) throws SQLException {
       return budgetDAOService.getBudgetById(budgetId);
    }
}
