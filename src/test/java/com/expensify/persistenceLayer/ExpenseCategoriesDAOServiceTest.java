package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.IExpenseCategory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

@SpringBootTest
public class ExpenseCategoriesDAOServiceTest {
    private ExpenseCategoriesDAOService service;
    @Mock
    IDatabase database;

    @Test
    public void testGetAllExpenseCategoriesDetails_ThrowsException() throws SQLException {
        service = new ExpenseCategoriesDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        Mockito.when(database.executeProcedure("CALL get_all_expense_categories()",parameterList)).thenThrow(new SQLException());
        List<IExpenseCategory> categories = service.getAllExpenseCategories();
        Assertions.assertEquals(0, categories.size());
    }

    @Test
    public void testGetAllExpenseCategoriesDetails_ReturnsResultSet() throws SQLException {
        service = new ExpenseCategoriesDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        when(mockResult.getInt("c_id")).thenReturn(123);
        when(mockResult.getString("expense_category")).thenReturn("123");
        when(mockResult.next()).thenReturn(true).thenReturn(false);
        Mockito.when(database.executeProcedure("CALL get_all_expense_categories()",parameterList))
                .thenReturn(mockResult);

        List<IExpenseCategory> categories = service.getAllExpenseCategories();
        Assertions.assertEquals(1, categories.size());
    }

}
