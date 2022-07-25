package com.expensify.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface IAnalytics {
    HashMap<Integer, Float> getMonthlyAnalytics(int userId, LocalDate date);

    HashMap<String, Float> getMonthlyAnalyticsByCategories(int userId, String startDate, String endDate);
}
