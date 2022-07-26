package com.expensify.model;

import com.expensify.persistenceLayer.ExpenseCategoriesDAOService;
import com.expensify.persistenceLayer.IExpenseCategoriesDAOService;
import com.expensify.persistenceLayer.IPaymentCategoriesDAOService;

import java.sql.SQLException;
import java.util.List;

public class ExpenseCategory implements IExpenseCategory {

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
    public List<IExpenseCategory> getAllExpenseCategoriesList() {
        try {
            return expenseCategoriesDAOService.getAllExpenseCategories();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
