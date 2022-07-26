package com.expensify.Validators;

import com.expensify.model.Expense;

public class ExpenseValidator implements IValidator {
    @Override
    public String validate(Object object) {
        Expense expense = (Expense) object;

        if (expense.getAmount() == null) {
            return "Expense amount shouldn't be less than zero.";
        }
        if (expense.getExpenseTitle().equals("")) {
            return "Expense title cannot be empty";
        }
        if (expense.getWalletId() == 0){
            return "Wallet cannot be empty";
        }

        return null;
    }
}
