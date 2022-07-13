package com.expensify.model;

public class BudgetFactory implements IBudgetFactory {

    public BudgetFactory(){

    }
    @Override
    public Budget createBudget() {
        return new Budget();
    }
}
