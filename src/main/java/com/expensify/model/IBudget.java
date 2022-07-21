//package com.expensify.model;
//
//import com.expensify.database.IDatabase;
//import com.expensify.persistenceLayer.IBudgetDAOService;
//import com.expensify.persistenceLayer.IBudgetDAOServiceFactory;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public abstract class IBudget {
//     protected IBudgetDAOService budgetDAOService;
//     protected int budgetId;
//     protected int walletId;
//
//     protected String walletName;
//     protected int userId;
//     protected float budgetLimit;
//
//     protected float totalExpenses;
//
//     protected String month;
//
//     public IBudget() {
//
//     }
//
//     public IBudget(IBudgetDAOServiceFactory budgetDAOServiceFactory , IDatabase database) {
//          budgetDAOService = budgetDAOServiceFactory.createBudgetDAOService(database);
//     }
//
//     public IBudgetDAOService getBudgetDAOService() {
//          return budgetDAOService;
//     }
//
//     public void setBudgetDAOService(IBudgetDAOService budgetDAOService) {
//          this.budgetDAOService = budgetDAOService;
//     }
//
//     public int getBudgetId() {
//          return budgetId;
//     }
//
//     public void setBudgetId(int budgetId) {
//          this.budgetId = budgetId;
//     }
//
//     public int getWalletId() {
//          return walletId;
//     }
//
//     public void setWalletId(int walletId) {
//          this.walletId = walletId;
//     }
//
//     public String getWalletName() {
//          return walletName;
//     }
//
//     public void setWalletName(String walletName) {
//          this.walletName = walletName;
//     }
//
//     public int getUserId() {
//          return userId;
//     }
//
//     public void setUserId(int userId) {
//          this.userId = userId;
//     }
//
//     public float getBudgetLimit() {
//          return budgetLimit;
//     }
//
//     public void setBudgetLimit(float budgetLimit) {
//          this.budgetLimit = budgetLimit;
//     }
//
//     public float getTotalExpenses() {
//          return totalExpenses;
//     }
//
//     public void setTotalExpenses(float totalExpenses) {
//          this.totalExpenses = totalExpenses;
//     }
//
//     public String getMonth() {
//          return month;
//     }
//
//     public void setMonth(String month) {
//          this.month = month;
//     }
//     abstract public List<IBudget> getAllBudgetDetailsService(int user_id, String startDate, String endDate) throws SQLException;
//     abstract public IBudget saveBudget() throws SQLException;
//     abstract public IBudget updateBudget() throws SQLException;
//     abstract public void deleteBudget(int budgetId) throws SQLException;
//     abstract public IBudget getBudgetById(int budgetId) throws SQLException;
//}
