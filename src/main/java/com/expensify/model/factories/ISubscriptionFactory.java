package com.expensify.model.factories;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.IExpenseCategory;
import com.expensify.model.ISubscription;
import com.expensify.persistenceLayer.ExpenseCategoriesDAOService;
import com.expensify.persistenceLayer.IExpenseCategoriesDAOService;
import com.expensify.persistenceLayer.ISubscriptionDAOService;

import java.sql.SQLException;
import java.util.List;

public interface ISubscriptionFactory {
    ISubscription createSubscription();

    //       ISubscription createSubscription();
    ISubscriptionDAOService createSubscriptionDAOService(IDatabase database);

    ISubscription createSubscription(int subscriptionId, String subscriptionName, int userId, String expiryDate);

    class ExpenseCategoryFactory implements IExpenseCategoryFactory {

        private static ExpenseCategoryFactory expenseCategoryFactory;

        public ExpenseCategoryFactory(){

        }
        public static ExpenseCategoryFactory instance() {
            if (expenseCategoryFactory == null) {
                expenseCategoryFactory = new ExpenseCategoryFactory();
            }
            return expenseCategoryFactory;
        }
        @Override
        public IExpenseCategory createExpenseCategory() {
            IDatabase database = MySqlDatabase.instance();
            return new ExpenseCategory(createExpenseCategoriesDAOService(database));

        }

        @Override
        public IExpenseCategoriesDAOService createExpenseCategoriesDAOService(IDatabase database) {
            return new ExpenseCategoriesDAOService(database);
        }

        @Override
        public IExpenseCategory createExpenseCategory(int c_id, String expenseCategory) {
            return new ExpenseCategory(c_id, expenseCategory);
        }

        public static class ExpenseCategory implements IExpenseCategory {

            private IExpenseCategoriesDAOService expenseCategoriesDAOService;
            private int categoryID;
            private String categoryName;

            public ExpenseCategory(){

            }
            public ExpenseCategory(int categoryID, String categoryName) {
                this.categoryID = categoryID;
                this.categoryName = categoryName;
            }
            public ExpenseCategory(IExpenseCategoriesDAOService database){
                expenseCategoriesDAOService = database;
            }



            public IExpenseCategoriesDAOService getExpenseCategoriesDAOService(){
                return expenseCategoriesDAOService;
            }

            public void setExpenseCategoriesDAOService(IExpenseCategoriesDAOService expenseCategoriesDAOService){
                this.expenseCategoriesDAOService = expenseCategoriesDAOService;
            }
            public int getCategoryID() {
                return categoryID;
            }

            public void setCategoryID(int categoryID) {
                this.categoryID = categoryID;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;

            }
            @Override
            public List<IExpenseCategory> getAllExpenseCategoriesList() throws SQLException {
                return expenseCategoriesDAOService.getAllExpenseCategories();
            }
        }
    }
}

