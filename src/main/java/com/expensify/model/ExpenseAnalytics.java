package com.expensify.model;

import com.expensify.persistenceLayer.IExpenseDOAService;

import java.time.LocalDate;
import java.util.HashMap;

public class ExpenseAnalytics implements IAnalytics {

    private IExpenseDOAService doaService;

    public ExpenseAnalytics(IExpenseDOAService doaService) {
        this.doaService = doaService;
    }


    @Override
    public HashMap<Integer, Float> getMonthlyAnalytics(int userId, LocalDate date) {
        String startDate = DateUtil.getFirstDayOfYear(date);
        String endDate = DateUtil.getLastDayOfYear(date);
        return this.doaService.getMonthlyExpense(userId, startDate, endDate);
    }

    @Override
    public HashMap<String, Float> getMonthlyAnalyticsByCategories(int userId, String startDate, String endDate) {
        return this.doaService.getMonthlyAnalyticsByCategories(userId, startDate, endDate);
    }
}
