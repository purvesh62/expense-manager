package com.expensify.model;


public class BudgetValidator {

    public String validate(Budget budget) {

        if (budget.getBudgetLimit() == 0) {
            return "Budget Limit cannot be zero";
        }
        if (budget.checkIfBudgetExists(budget.getBudgetId(), budget.getUserId(), budget.getWalletId(), budget.getMonth())) {
            return "Budget already exists for this month and wallet";
        }

        return null;
    }
}
