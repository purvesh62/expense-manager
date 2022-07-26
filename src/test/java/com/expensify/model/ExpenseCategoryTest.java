package com.expensify.model;

import com.expensify.persistenceLayerMock.ExpenseCategoriesDAOServiceMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest

public class ExpenseCategoryTest {
    @Test
    public void getAllExpenseCategoryDetailsServiceSuccessTest() throws SQLException {
        ExpenseCategoriesDAOServiceMock expenseCategoriesDAOServiceMock = new ExpenseCategoriesDAOServiceMock();
        expenseCategoriesDAOServiceMock.getAllExpenseCategoryMock();
        List<IExpenseCategory> allExpenseCategory = expenseCategoriesDAOServiceMock.getAllExpenseCategories();
        Assertions.assertEquals(1, allExpenseCategory.size());
    }

    @Test
    public void getAllExpenseCategoryDetailsServiceFailureTest() throws SQLException {
        ExpenseCategoriesDAOServiceMock expenseCategoriesDAOServiceMock = new ExpenseCategoriesDAOServiceMock();
        expenseCategoriesDAOServiceMock.getNullExpenseCategoryMock();
        List<IExpenseCategory> allExpenseCategory = expenseCategoriesDAOServiceMock.getAllExpenseCategories();
        Assertions.assertEquals(null, allExpenseCategory);
    }
}
