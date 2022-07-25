package com.expensify.model;

import com.expensify.persistenceLayer.IBudgetDAOService;

import java.time.LocalDate;
import java.util.HashMap;

public class BudgetAnalytics implements IAnalytics{

    private IBudgetDAOService budgetDAOService;

    public BudgetAnalytics(IBudgetDAOService budgetDAOService) {
        this.budgetDAOService = budgetDAOService;
    }


    @Override
    public HashMap<Integer, Float> getMonthlyAnalytics(int userId, LocalDate date) {
        String startDate = DateUtil.getFirstDayOfYear(date);
        String endDate = DateUtil.getLastDayOfYear(date);
        return this.budgetDAOService.getMonthlyBudget(userId, startDate, endDate);
    }

    @Override
    public HashMap<String, Float> getMonthlyAnalyticsByCategories(int userId, String startDate, String endDate) {
        return null;
    }
}
