package com.expensify.model.factories;
import com.expensify.database.IDatabase;
import com.expensify.model.IPaymentCategory;
import com.expensify.persistenceLayer.IPaymentCategoriesDAOService;


public interface IPaymentCategoryFactory {

    IPaymentCategory createPaymentCategory();
//    IPaymentCategoryFactory createPaymentCategory();
    IPaymentCategoriesDAOService createPaymentCategoriesDAOService(IDatabase database);
    IPaymentCategory createPaymentCategory(int p_id, String paymentCategory);
}
