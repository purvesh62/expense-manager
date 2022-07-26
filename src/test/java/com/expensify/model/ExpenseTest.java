package com.expensify.model;

import com.expensify.persistenceLayer.IExpenseDOAService;
import com.expensify.persistenceLayerMock.ExpenseDAOServiceMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {

    @Test
    public void getAllUserExpenseSuccessTest() {
        IExpenseDOAService expenseDOAService = new ExpenseDAOServiceMock();
        List<IExpense> expenseList = expenseDOAService.getAllUserExpenses(1, "2022-07-01", "2022-07-31");
        assertEquals(2, expenseList.size());
    }

    @Test
    public void getAllUserExpenseFailureTest() {
        ExpenseDAOServiceMock expenseDOAService = new ExpenseDAOServiceMock();
        List<IExpense> expenseList = expenseDOAService.getNullUserExpenses();
        assertNull(expenseList);
    }

    @Test
    public void addUserExpenseSuccessTest() {
        IExpenseDOAService expenseDOAService = new ExpenseDAOServiceMock();
        boolean status = expenseDOAService.addUserExpenses(1, "Test expense title", "Test expense description", (float) 10.00, 1, 1, "2022-07-20");
        assertTrue(status);
    }

    @Test
    public void addUserExpenseFailureTest() {
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

}
