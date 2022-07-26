package com.expensify.persistenceLayerMock;

import com.expensify.factories.PaymentCategoryFactory;
import com.expensify.model.IPaymentCategory;
import com.expensify.persistenceLayer.IPaymentCategoriesDAOService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentCategoriesDAOServiceMock implements IPaymentCategoriesDAOService {

    List<IPaymentCategory> paymentMockList = new ArrayList<>();

    public void getAllPaymentMock() {
        IPaymentCategory paymentCategoryMock1 = PaymentCategoryFactory.instance().createPaymentCategory();
        paymentMockList.add(paymentCategoryMock1);
    }
    public void getNullPaymentCategoryMock(){
        paymentMockList = null;
    }

    @Override
    public List<IPaymentCategory> getAllPaymentCategories() throws SQLException {
        return paymentMockList;
    }
}
