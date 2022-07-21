package com.expensify.model;

import com.expensify.persistenceLayer.ExpenseCategoriesDAOService;

import java.util.List;

public class ExpenseCategory {
    private int categoryID;
    private String categoryName;

    private ExpenseCategoriesDAOService expenseCategoriesDAOService;

    private final IExpenseCategoryFactory factory;
    public ExpenseCategory(IExpenseCategoryFactory factory){
        this.factory = factory;
        this.expenseCategoriesDAOService = factory.createExpenseCategoryDAOService();
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
