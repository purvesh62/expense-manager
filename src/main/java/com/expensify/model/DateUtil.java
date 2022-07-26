package com.expensify.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

public class DateUtil {
    private static String dateFormat = "yyyy-MM-dd";
    private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

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

    public static String formatDate(String date) {
        try {
            return new SimpleDateFormat(dateFormat).format(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        } catch (ParseException e) {
            return date;
        }
    }

    public static Date parseDate(String date) {
        Date start = null;
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static java.sql.Date convertDate (String date) {
        Date start = parseDate(date);
        return new java.sql.Date(start.getTime());
    }

    public static String getStartDateFromMonth(String month) {
        LocalDate date = LocalDate.now();
        return getFirstDayOfMonth(date.withMonth(Integer.parseInt(month)));
    }

    public static String getLastDateFromMonth(String month) {
        LocalDate date = LocalDate.now();
        return getLastDayOfMonth(date.withMonth(Integer.parseInt(month)));
    }
}
