package com.expensify.model;

import com.expensify.persistenceLayerMock.PaymentCategoriesDAOServiceMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest

public class PaymentCategoryTest {
    @Test
    public void getAllPaymentCategoryDetailsServiceSuccessTest() throws SQLException {
        PaymentCategoriesDAOServiceMock paymentCategoriesDAOServiceMock = new PaymentCategoriesDAOServiceMock();
        paymentCategoriesDAOServiceMock.getAllPaymentMock();
        List<IPaymentCategory> allPaymentCategory = paymentCategoriesDAOServiceMock.getAllPaymentCategories();
        Assertions.assertEquals(1, allPaymentCategory.size());
    }

    @Test
    public void getAllPaymentCategoryDetailsServiceFailureTest() throws SQLException {
        PaymentCategoriesDAOServiceMock paymentCategoriesDAOServiceMock = new PaymentCategoriesDAOServiceMock();
        paymentCategoriesDAOServiceMock.getNullPaymentCategoryMock();
        List<IPaymentCategory> allPaymentCategory = paymentCategoriesDAOServiceMock.getAllPaymentCategories();
        Assertions.assertEquals(null, allPaymentCategory);
    }

}

