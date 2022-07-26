package com.expensify.factories;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.IPaymentCategory;
import com.expensify.model.PaymentCategory;
import com.expensify.persistenceLayer.IPaymentCategoriesDAOService;
import com.expensify.persistenceLayer.PaymentCategoriesDAOService;

public class PaymentCategoryFactory implements IPaymentCategoryFactory {

    private static PaymentCategoryFactory paymentCategoryFactory;

    private PaymentCategoryFactory() {

    }

    public static PaymentCategoryFactory instance() {
        if (paymentCategoryFactory == null) {
            paymentCategoryFactory = new PaymentCategoryFactory();
        }
        return paymentCategoryFactory;
    }

    @Override
    public IPaymentCategory createPaymentCategory() {
        IDatabase database = MySqlDatabase.instance();
        return new PaymentCategory(createPaymentCategoriesDAOService(database));
    }

    @Override
    public IPaymentCategoriesDAOService createPaymentCategoriesDAOService(IDatabase database) {
        return new PaymentCategoriesDAOService(database);
    }

    @Override
    public IPaymentCategory createPaymentCategory(int p_id, String paymentCategory) {
        return new PaymentCategory(p_id, paymentCategory);
    }
}
