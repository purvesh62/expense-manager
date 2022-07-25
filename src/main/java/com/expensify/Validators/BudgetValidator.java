package com.expensify.Validators;

import com.expensify.model.Budget;

public class BudgetValidator implements IValidator {
    @Override
    public String validate(Object object) {
        Budget budget = (Budget) object;
        if (budget.getBudgetLimit() == 0) {
            return "Budget Limit cannot be zero";
        }
        if (budget.checkIfBudgetExists(budget.getBudgetId(), budget.getUserId(), budget.getWalletId(), budget.getMonth())) {
            return "Budget already exists for this month and wallet";
        }
        return null;
    }
}
