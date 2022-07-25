package com.expensify.model;

import com.expensify.persistenceLayer.IExpenseDOAService;
import com.expensify.persistenceLayerMock.ExpenseDAOServiceMock;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ExpenseAnalyticsTest {
    @Test
    public void getMonthlyAnalyticsSuccessTest() {
        IExpenseDOAService expenseDOAService = new ExpenseDAOServiceMock();
        HashMap<Integer, Float> expenseData = expenseDOAService.getMonthlyExpense(1, "2022-07-01", "2022-06-31");
        assertEquals(expenseData.size(), 2);
    }

    @Test
    public void getMonthlyAnalyticsFailureTest() {
        ExpenseDAOServiceMock expenseDOAService = new ExpenseDAOServiceMock();
        HashMap<Integer, Float> expenseData = expenseDOAService.getNullMonthlyExpense(1, "2022-07-01", "2022-06-31");
        assertNull(expenseData);
    }

    @Test
    public void getMonthlyAnalyticsByCategoriesSuccessTest() {
        IExpenseDOAService expenseDOAService = new ExpenseDAOServiceMock();
        HashMap<String, Float> expenseData = expenseDOAService.getMonthlyAnalyticsByCategories(1, "2022-07-01", "2022-06-31");
        assertEquals(expenseData.size(), 2);
    }

    @Test
    public void getMonthlyAnalyticsByCategoriesErrorTest() {
        ExpenseDAOServiceMock expenseDOAService = new ExpenseDAOServiceMock();
        HashMap<String, Float> expenseData = expenseDOAService.getNullMonthlyAnalyticsByCategories(1, "2022-07-01", "2022-06-31");
        assertNull(expenseData);
    }
}
