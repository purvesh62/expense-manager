package com.expensify.factories;

import com.expensify.database.IDatabase;
import com.expensify.model.IPaymentCategory;
import com.expensify.persistenceLayer.IPaymentCategoriesDAOService;


public interface IPaymentCategoryFactory {

    IPaymentCategory createPaymentCategory();

    IPaymentCategoriesDAOService createPaymentCategoriesDAOService(IDatabase database);

    IPaymentCategory createPaymentCategory(int p_id, String paymentCategory);
}
