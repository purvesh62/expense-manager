package com.expensify.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateUtilTest {

    @Test
    public void getFirstDayOfMonthSuccessTest() {
        LocalDate testDate = LocalDate.parse("2022-07-25");
        String outputDate = DateUtil.getFirstDayOfMonth(testDate);
        assertEquals(outputDate, "2022-07-01");
    }

    @Test
    public void getFirstDayOfMonthFailureTest() {
        LocalDate testDate = LocalDate.parse("2022-07-25");
        String outputDate = DateUtil.getFirstDayOfMonth(testDate);
        assertNotEquals(outputDate, "2022-07-02");
    }

    @Test
    public void getLastDayOfMonthSuccessTest() {
        LocalDate testDate = LocalDate.parse("2022-07-25");
        String outputDate = DateUtil.getLastDayOfMonth(testDate);
        assertEquals(outputDate, "2022-07-31");
    }

    @Test
    public void getLastDayOfMonthFailureTest() {
        LocalDate testDate = LocalDate.parse("2022-07-25");
        String outputDate = DateUtil.getLastDayOfMonth(testDate);
        assertNotEquals(outputDate, "2022-07-02");
    }

    @Test
    public void getFirstDayOfYearSuccessTest() {
        LocalDate testDate = LocalDate.parse("2022-07-25");
        String outputDate = DateUtil.getFirstDayOfYear(testDate);
        assertEquals(outputDate, "2022-01-01");
    }

    @Test
    public void getFirstDayOfYearFailureTest() {
        LocalDate testDate = LocalDate.parse("2022-07-25");
        String outputDate = DateUtil.getFirstDayOfYear(testDate);
        assertNotEquals(outputDate, "2022-07-02");
    }

    @Test
    public void getLastDayOfYearSuccessTest() {
        LocalDate testDate = LocalDate.parse("2022-07-25");
        String outputDate = DateUtil.getLastDayOfYear(testDate);
        assertEquals(outputDate, "2022-12-31");
    }

    @Test
    public void getLastDayOfYearFailureTest() {
        LocalDate testDate = LocalDate.parse("2022-07-25");
        String outputDate = DateUtil.getLastDayOfYear(testDate);
        assertNotEquals(outputDate, "2022-07-02");
    }

    @Test
    public void formatDateSuccessTest() {
        String formattedDate = DateUtil.formatDate("25/07/2022");
        assertEquals(formattedDate, "2022-07-25");
    }

    @Test
    public void formatDateFailureTest() {
        String formattedDate = DateUtil.formatDate("25/07/2022");
        assertNotEquals(formattedDate, "2022/07/25");
    }

    @Test
    public void parseDateSuccessTest() {
        Date parsedDate = DateUtil.parseDate("2022-07-25");
        Date expectedDate = null;
        try {
            expectedDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-07-25");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        assertEquals(parsedDate, expectedDate);
    }

    @Test
    public void parseDateFailureTest() {
        Date parsedDate = DateUtil.parseDate("2022-07-27");
        Date expectedDate = null;
        try {
            expectedDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-07-25");
        } catch (ParseException e) {
        }
        assertNotEquals(parsedDate, expectedDate);
    }

    @Test
    public void getStartDateFromMonthSuccessTest() {
        String outputDate = DateUtil.getStartDateFromMonth("07");
        assertEquals(outputDate, "2022-07-01");
    }

    @Test
    public void getStartDateFromMonthFailureTest() {
        String outputDate = DateUtil.getStartDateFromMonth("07");
        assertNotEquals(outputDate, "2022-08-01");
    }

    @Test
    public void getLastDateFromMonthSuccessTest() {
        String outputDate = DateUtil.getLastDateFromMonth("07");
        assertEquals(outputDate, "2022-07-31");
    }

    @Test
    public void getLastDateFromMonthFailureTest() {
        String outputDate = DateUtil.getLastDateFromMonth("07");
        assertNotEquals(outputDate, "2022-08-01");
    }
}
