package com.expensify.model;

import com.expensify.persistenceLayer.IExpenseDOAService;
import com.expensify.persistenceLayerMock.ExpenseDAOServiceMock;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {

    @Test
    public void getAllUserExpensesSuccessTest() {
        IExpenseDOAService expenseDOAService = new ExpenseDAOServiceMock();
        List<IExpense> expenseList = expenseDOAService.getAllUserExpenses(1, "2022-07-01", "2022-07-31");
        assertEquals(2, expenseList.size());
    }

    @Test
    public void getAllUserExpensesFailureTest() {
        ExpenseDAOServiceMock expenseDOAService = new ExpenseDAOServiceMock();
        List<IExpense> expenseList = expenseDOAService.getNullUserExpenses();
        assertNull(expenseList);
    }

    @Test
    public void addUserExpensesSuccessTest() {
        IExpenseDOAService expenseDOAService = new ExpenseDAOServiceMock();
        boolean status = expenseDOAService.addUserExpenses(1, "Test expense title", "Test expense description", (float) 10.00, 1, 1, "2022-07-20");
        assertTrue(status);
    }

    @Test
    public void addUserExpensesFailureTest() {
        IExpenseDOAService expenseDOAService = new ExpenseDAOServiceMock();
        boolean status = expenseDOAService.addUserExpenses(1, "", "Test expense description", (float) 10.00, 1, 1, "2022-07-20");
        assertFalse(status);
    }

    @Test
    public void deleteUserExpenseSuccessTest() {
        IExpenseDOAService expenseDOAService = new ExpenseDAOServiceMock();
        boolean status = expenseDOAService.deleteUserExpense(1);
        assertTrue(status);
    }

    @Test
    public void deleteUserExpenseFailureTest() {
        IExpenseDOAService expenseDOAService = new ExpenseDAOServiceMock();
        boolean status = expenseDOAService.deleteUserExpense(2);
        assertFalse(status);
    }

    @Test
    public void getMonthlyExpenseSuccessTest() {
        IExpenseDOAService expenseDOAService = new ExpenseDAOServiceMock();
        HashMap<Integer, Float> expenseData = expenseDOAService.getMonthlyExpense(1, "2022-07-01", "2022-06-31");
        assertEquals(expenseData.size(), 2);
    }

    @Test
    public void getMonthlyExpenseFailureTest() {
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
