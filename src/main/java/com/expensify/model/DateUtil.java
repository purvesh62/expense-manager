package com.expensify.model;

import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

public class DateUtil {
    public static String getFirstDayOfMonth(LocalDate date) {
        String startDay = "01";
        String month;
        int m = date.getMonth().ordinal() + 1;
        if (m < 10) {
            month = "0" + m;
        } else {
            month = String.valueOf(m);
        }
        String year = String.valueOf(date.getYear());
        return String.format("%s-%s-%s", year, month, startDay);
    }

    public static String getLastDayOfMonth(LocalDate date) {
        String lastDay = String.valueOf(date.lengthOfMonth());
        String month;
        int m = date.getMonth().ordinal() + 1;
        if (m < 10) {
            month = "0" + m;
        } else {
            month = String.valueOf(m);
        }
        String year = String.valueOf(date.getYear());
        return String.format("%s-%s-%s", year, month, lastDay);
    }

    public static String getFirstDayOfYear(LocalDate date) {
        return String.valueOf(date.with(firstDayOfYear()));
    }

    public static String getLastDayOfYear(LocalDate date) {
        return String.valueOf(date.with(lastDayOfYear()));
    }
}
