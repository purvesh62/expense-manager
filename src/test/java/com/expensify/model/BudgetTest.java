package com.expensify.model;

import com.expensify.persistenceLayerMock.BudgetDAOServiceMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@SpringBootTest
public class BudgetTest {

    @Test
    public void getAllBudgetDetailsServiceSuccessTest() throws SQLException {
        BudgetDAOServiceMock budgetDAOServiceMock = new BudgetDAOServiceMock();
        budgetDAOServiceMock.getAllBudgetMock();
        List<IBudget> allBudgetDetails = budgetDAOServiceMock.getAllBudgetDetails(1, "2022-07-01", "2022-07-31");
        Assertions.assertEquals(2, allBudgetDetails.size());
    }

    @Test
    public void getAllBudgetDetailsServiceFailureTest() throws SQLException {
        BudgetDAOServiceMock budgetDAOServiceMock = new BudgetDAOServiceMock();
        budgetDAOServiceMock.getNullBudgetMock();
        List<IBudget> allBudgetDetails = budgetDAOServiceMock.getAllBudgetDetails(1, "2022-07-01", "2022-07-31");
        Assertions.assertEquals(null, allBudgetDetails);
    }

    @Test
    public void saveBudgetSuccessTest() throws SQLException {
        BudgetDAOServiceMock budgetDAOServiceMock = new BudgetDAOServiceMock();
        boolean result = budgetDAOServiceMock.addNewBudget(1, 1, 1200, "8");
        Assertions.assertEquals(true, result);
    }

    @Test
    public void saveBudgetFailureTest() throws SQLException {
        BudgetDAOServiceMock budgetDAOServiceMock = new BudgetDAOServiceMock();
        boolean result = budgetDAOServiceMock.addNewBudget(0, 1, 1200, "8");
        Assertions.assertEquals(false, result);
    }

    @Test
    public void updateBudgetSuccessTest() throws SQLException {
        BudgetDAOServiceMock budgetDAOServiceMock = new BudgetDAOServiceMock();
        boolean result = budgetDAOServiceMock.updateBudget(1, 1, 1200);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void updateBudgetFailureTest() throws SQLException {
        BudgetDAOServiceMock budgetDAOServiceMock = new BudgetDAOServiceMock();
        boolean result = budgetDAOServiceMock.updateBudget(1, 0, 1200);
        Assertions.assertEquals(false, result);
    }

    @Test
    public void deleteBudgetSuccessTest() throws SQLException {
        BudgetDAOServiceMock budgetDAOServiceMock = new BudgetDAOServiceMock();
        boolean result = budgetDAOServiceMock.deleteBudget(1);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void deleteBudgetFailureTest() throws SQLException {
        BudgetDAOServiceMock budgetDAOServiceMock = new BudgetDAOServiceMock();
        boolean result = budgetDAOServiceMock.deleteBudget(2);
        Assertions.assertEquals(false, result);
    }

    @Test
    public void getBudgetByIdSucessTest() throws SQLException {
        BudgetDAOServiceMock budgetDAOServiceMock = new BudgetDAOServiceMock();
        IBudget budget = budgetDAOServiceMock.getBudgetById(1);
        Assertions.assertNotEquals(null, budget);
    }

    @Test
    public void getBudgetByIdFailureTest() throws SQLException {
        BudgetDAOServiceMock budgetDAOServiceMock = new BudgetDAOServiceMock();
        IBudget budget = budgetDAOServiceMock.getBudgetById(2);
        Assertions.assertEquals(null, budget);
    }

    @Test
    public void checkIfBudgetLimitExceedsSuccessTest() throws SQLException, ParseException {
        BudgetDAOServiceMock budgetDAOServiceMock = new BudgetDAOServiceMock();
        int userId = budgetDAOServiceMock.checkIfBudgetLimitExceeds(1, 1, "2022-07-01");
        Assertions.assertEquals(1, userId);
    }

    @Test
    public void checkIfBudgetLimitExceedsFailureTest() throws SQLException, ParseException {
        BudgetDAOServiceMock budgetDAOServiceMock = new BudgetDAOServiceMock();
        int userId = budgetDAOServiceMock.checkIfBudgetLimitExceeds(1, 0, "2022-07-01");
        Assertions.assertEquals(0, userId);
    }

    @Test
    public void checkIfBudgetExistsSuccessTest() {
        BudgetDAOServiceMock budgetDAOServiceMock = new BudgetDAOServiceMock();
        boolean exists = budgetDAOServiceMock.checkIfBudgetExists(1, 1, 1, "8");
        Assertions.assertEquals(true, exists);
    }

    @Test
    public void checkIfBudgetExistsFailureTest() {
        BudgetDAOServiceMock budgetDAOServiceMock = new BudgetDAOServiceMock();
        boolean exists = budgetDAOServiceMock.checkIfBudgetExists(1, 1, 2, "8");
        Assertions.assertEquals(false, exists);
    }
}
