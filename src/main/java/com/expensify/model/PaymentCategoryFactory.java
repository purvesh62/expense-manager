package com.expensify.model;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.factories.IPaymentCategoryFactory;
import com.expensify.model.factories.WalletFactory;
import com.expensify.persistenceLayer.IPaymentCategoriesDAOService;
import com.expensify.persistenceLayer.PaymentCategoriesDAOService;
import com.expensify.persistenceLayer.WalletDAOService;

public class PaymentCategoryFactory implements IPaymentCategoryFactory {

    private static PaymentCategoryFactory paymentCategoryFactory;

    public PaymentCategoryFactory(){

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
