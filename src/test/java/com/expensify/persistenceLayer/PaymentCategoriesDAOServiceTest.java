package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.IPaymentCategory;
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
public class PaymentCategoriesDAOServiceTest {

    private PaymentCategoriesDAOService service;

    @Mock
    IDatabase database;

    @Test
    public void testGetAllPaymentCategoriesDetails_ThrowsException() throws SQLException {
        service = new PaymentCategoriesDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        Mockito.when(database.executeProcedure("CALL get_all_payment_categories()",parameterList)).thenThrow(new SQLException());
        List<IPaymentCategory> categories = service.getAllPaymentCategories();
        Assertions.assertEquals(0, categories.size());
    }

    @Test
    public void testGetAllPaymentCategoriesDetails_ReturnsResultSet() throws SQLException {
        service = new PaymentCategoriesDAOService(database);
        List<Object> parameterList = new ArrayList<>();
        ResultSet mockResult = Mockito.mock(ResultSet.class);
        when(mockResult.getInt("p_id")).thenReturn(123);
        when(mockResult.getString("payment_category")).thenReturn("123");
        when(mockResult.next()).thenReturn(true).thenReturn(false);
        Mockito.when(database.executeProcedure("CALL get_all_payment_categories()",parameterList))
                .thenReturn(mockResult);

        List<IPaymentCategory> categories = service.getAllPaymentCategories();
        Assertions.assertEquals(1, categories.size());
    }

}
