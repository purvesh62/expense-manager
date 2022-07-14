package com.expensify.model;

import com.expensify.persistenceLayer.ExpenseCategoriesDAOService;

import java.util.List;

public class ExpenseCategory {
    private int categoryID;
    private String categoryName;

    private ExpenseCategoriesDAOService expenseCategoriesDAOService;
    public ExpenseCategory(){
        expenseCategoriesDAOService = new ExpenseCategoriesDAOService();
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
    public List<ExpenseCategory> getAllExpenseCategories(){
        return expenseCategoriesDAOService.getAllExpenseCategoriesList();
    }

}
