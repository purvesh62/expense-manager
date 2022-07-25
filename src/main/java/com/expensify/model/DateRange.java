package com.expensify.model;


import java.time.LocalDate;

public class DateRange {
    private String dateFrom;
    private String dateTo;

    public DateRange() {
        dateFrom = DateUtil.getFirstDayOfMonth(LocalDate.now());
        dateTo = DateUtil.getLastDayOfMonth(LocalDate.now());
    }
    public DateRange(String dateFrom, String dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
