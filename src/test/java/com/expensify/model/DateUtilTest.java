package com.expensify.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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
}
